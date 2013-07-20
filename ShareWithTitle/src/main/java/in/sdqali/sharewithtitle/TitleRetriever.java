package in.sdqali.sharewithtitle;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sdqali on 7/20/13.
 */
public class TitleRetriever {
    private String urlText;

    public TitleRetriever(String url) {
        this.urlText = url;
    }

    public void retrieve(TitleGrabCallback callback) {
        if(isValidUrl(urlText)) {
            new DownloadTaskNew(callback).execute(urlText);
        } else {
            callback.showError("Not a valid url. Could not load title!");
        }
    }

    private boolean isValidUrl(String urlText) {
        return (URLUtil.isHttpsUrl(urlText) || URLUtil.isHttpUrl(urlText));
    }

    private class DownloadTaskNew extends AsyncTask<String, Void, String> {
        private TitleGrabCallback callback;
        private final PageDownloader pageDownloader = new PageDownloader();

        public DownloadTaskNew(TitleGrabCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... urls) {
            callback.onProgress();
            return pageDownloader.downloadUrl(urls[0]);
        }

        @Override
        protected void onPostExecute(String rawHtml) {
            Log.d("Share With Title", "On post: " + rawHtml);
            Pattern p = Pattern.compile("<head>.*?<title>(.*?)</title>.*?</head>", Pattern.DOTALL);
            Matcher m = p.matcher(rawHtml);
            String title;
            String output = urlText;
            while (m.find()) {
                title = m.group(1);
                Log.d("Share With Title", "Title: " + title);
                output = title + " " + urlText;
            }
            callback.onSuccess(output);
            callback.cleanUp();
        }
    }
}

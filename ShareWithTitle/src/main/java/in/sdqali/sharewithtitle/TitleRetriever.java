package in.sdqali.sharewithtitle;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

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

    public void retrieve(TitleViewUpdater viewUpdater) {
        if(isValidUrl(urlText)) {
            new DownloadTaskNew(viewUpdater).execute(urlText);
        } else {
            viewUpdater.showError("Not a valid url. Could not load title!");
        }
    }

    private boolean isValidUrl(String urlText) {
        return (URLUtil.isHttpsUrl(urlText) || URLUtil.isHttpUrl(urlText));
    }

    private class DownloadTaskNew extends AsyncTask<String, Void, String> {
        private TitleViewUpdater viewUpdater;
        private final PageDownloader pageDownloader = new PageDownloader();

        public DownloadTaskNew(TitleViewUpdater viewUpdater) {
            this.viewUpdater = viewUpdater;
        }

        @Override
        protected String doInBackground(String... urls) {
            viewUpdater.showProgress();
            return pageDownloader.downloadUrl(urls[0]);
        }

        @Override
        protected void onPostExecute(String rawHtml) {
            Log.d("Share With Title", "On post: " + rawHtml);
            Pattern p = Pattern.compile("<head>.*?<title>(.*?)</title>.*?</head>", Pattern.DOTALL);
            Matcher m = p.matcher(rawHtml);
            String title;
            viewUpdater.update(urlText);
            while (m.find()) {
                title = m.group(1);
                Log.d("Share With Title", "Title: " + title);
                viewUpdater.update(title);
            }
            viewUpdater.finish();
        }
    }
}

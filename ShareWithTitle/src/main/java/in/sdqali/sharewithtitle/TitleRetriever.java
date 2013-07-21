package in.sdqali.sharewithtitle;

import android.os.AsyncTask;
import android.webkit.URLUtil;

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
            callback.showError("Not a valid url. Could not load title!", urlText);
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
            String title = TitleParser.retrieveTitle(rawHtml);
            String output = urlText;
            if (title != null) {
                output = title + " " + urlText;
            }
            callback.onSuccess(output);
        }
    }
}

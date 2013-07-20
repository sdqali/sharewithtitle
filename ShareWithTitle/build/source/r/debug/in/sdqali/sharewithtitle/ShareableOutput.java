package in.sdqali.sharewithtitle;

import android.net.Uri;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

/**
 * Created by sdqali on 7/20/13.
 */
public class ShareableOutput {
    private String input;

    public ShareableOutput(String input) {
        this.input = input;
    }

    public boolean isValidUrl() {
        if(URLUtil.isHttpsUrl(input) || URLUtil.isHttpUrl(input)) {
            return true;
        } else {
            return false;
        }
    }

    public String output() {
        if (isValidUrl()) {
            Log.d("Share With Title", "$$$$$$$$$$$ VALID URL");

            return new TitleRetriever(input).retrieve();
        } else {
            Log.d("Share With Title", "$$$$$$$$$$$ Invalid URL");

            return input;
        }
    }
}

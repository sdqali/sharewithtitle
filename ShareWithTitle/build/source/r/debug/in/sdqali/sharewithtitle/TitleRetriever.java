package in.sdqali.sharewithtitle;

import android.net.http.AndroidHttpClient;
import android.text.AndroidCharacter;
import android.util.Log;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sdqali on 7/20/13.
 */
public class TitleRetriever {
    private String urlText;

    public TitleRetriever(String url) {
        this.urlText = url;
    }

    public String retrieve() {
        try {
            URL url = new URL(urlText);
            HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
            String content = readStream(con.getInputStream());
            Log.d("Share With Title", "content: " + content);

            return content;
        } catch (Exception e) {
            Log.d("Share With Title", e.getStackTrace().toString());

            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            return urlText;
        }
    }

    private String readStream(InputStream in) {
        String output = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return output;
    }
}

package in.sdqali.sharewithtitle;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageDownloader {
    public PageDownloader() {
    }

    String downloadUrl(String urlText) {
        try {
            URL url = null;
            url = new URL(urlText);
            HttpURLConnection con = null;
            con = (HttpURLConnection) url
                    .openConnection();
            String content = readStream(con.getInputStream());
            Log.d("Share With Title", "content: " + content);
            return content;

        } catch (Exception e) {
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
                if(TitleParser.retrieveTitle(output) != null) {
                    return output;
                }
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
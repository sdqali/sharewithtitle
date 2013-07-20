package in.sdqali.sharewithtitle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();

        final TextView textView = (TextView) findViewById(R.id.greet_text);
        textView.setText("");
        final View progressBar = (ProgressBar)findViewById(R.id.progressBar);


        if(action.equals(Intent.ACTION_SEND)) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            Log.d("Share With Title", "Received shared text: " + sharedText);

            new TitleRetriever(sharedText).retrieve(new TitleGrabCallback() {
                @Override
                public void update(String title) {
                    textView.setText(title);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void showProgress() {
                    progressBar.animate();
                }

                @Override
                public void finish() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void showError(String errorMessage) {
                    showToast(errorMessage);
                }
            });
        }
    }

    private void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

package in.sdqali.sharewithtitle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by sdqali on 7/20/13.
 */
public class ReshareCallback implements TitleGrabCallback{
    private Activity activity;
    private Context applicationContext;
    private ProgressBar progressBar;

    public ReshareCallback(Activity mainActivity, ProgressBar progressBar) {
        this.activity = mainActivity;
        this.progressBar = progressBar;
    }

    @Override
    public void onSuccess(String titleAndLink) {
        progressBar.setVisibility(View.VISIBLE);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, titleAndLink);
        activity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void onProgress() {
        progressBar.animate();
    }

    @Override
    public void cleanUp() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(activity.getApplicationContext(), errorMessage, duration);
        toast.show();
    }
}

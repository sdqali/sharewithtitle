package in.sdqali.sharewithtitle;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
* Created by sdqali on 7/20/13.
*/
class UpdateViewCallback implements TitleGrabCallback {
    private Context mainActivityContext;
    private final TextView textView;
    private final View progressBar;


    public UpdateViewCallback(Context context, TextView textView, View progressBar) {
        mainActivityContext = context;
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    public void onSuccess(String title) {
        textView.setText(title);
        progressBar.setVisibility(View.VISIBLE);
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
        showToast(errorMessage);
    }


    private void showToast(String text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(mainActivityContext, text, duration);
        toast.show();
    }

}

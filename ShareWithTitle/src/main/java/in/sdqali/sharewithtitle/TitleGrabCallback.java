package in.sdqali.sharewithtitle;

/**
 * Created by sdqali on 7/20/13.
 */
public interface TitleGrabCallback {
    public void onSuccess(String title);
    public void onProgress();
    public void cleanUp();
    public void showError(String errorMessage, String urlText);
}

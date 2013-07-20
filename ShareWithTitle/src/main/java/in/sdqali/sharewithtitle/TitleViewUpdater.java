package in.sdqali.sharewithtitle;

/**
 * Created by sdqali on 7/20/13.
 */
public interface TitleViewUpdater {
    public void update(String title);
    public void showProgress();
    public void finish();
    public void showError(String errorMessage);
}

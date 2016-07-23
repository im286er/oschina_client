package inter;

import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2016/5/28.
 */
public interface Presenter
{

    public void visitNet (String url, RequestParams params);

    public void setView (byte[] bytes);
}

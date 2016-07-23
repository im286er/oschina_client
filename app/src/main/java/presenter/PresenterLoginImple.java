package presenter;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import net.LoginHttp;

import org.apache.http.Header;

import inter.PresenterLogin;
import inter.PresenterLoginNet;
import inter.PresenterLoginView;

/**
 * Created by Administrator on 2016/6/7.
 */
public class PresenterLoginImple implements PresenterLogin
{
    private PresenterLoginView mPresenterLoginView;
    private PresenterLoginNet mPresenterLoginNet;

    public PresenterLoginImple (PresenterLoginView presenterLoginView)
    {
        mPresenterLoginView = presenterLoginView;
        mPresenterLoginNet = new LoginHttp(this);
    }

    @Override
    public void visitNet(String url, String username, String password) {
        mPresenterLoginNet.loginToNet(url, username, password);

    }

    @Override
    public void showData(byte[] bytes) {
        mPresenterLoginView.setView(bytes);
    }
}

package net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.protocol.HttpContext;
import org.kymjs.kjframe.http.HttpConfig;

import java.util.Locale;

import config.ApiHttpClient;
import config.PhoneInfo;
import inter.PresenterLogin;
import inter.PresenterLoginNet;
import myapplication.AppContext;

/**
 * Created by Administrator on 2016/6/7.
 */
public class LoginHttp implements PresenterLoginNet {
    private AsyncHttpClient mAsyncHttpClient;
    private PresenterLogin mPresenterLogin;

    public LoginHttp(PresenterLogin presenterLogin) {
        mAsyncHttpClient = ApiHttpClient.getmAsyncHttpClient();
        mPresenterLogin = presenterLogin;
    }


    @Override
    public void loginToNet(final String url, String username, String password) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("pwd", password);
        params.put("keep_login", 1);

        AsyncHttpResponseHandler asyncHttpResponseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                mPresenterLogin.showData(bytes);
                HttpContext httpContext = mAsyncHttpClient.getHttpContext();
                CookieStore cookies = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
                if (cookies != null) {
                    String tmpcookies = "";
                    for (Cookie c : cookies.getCookies()) {
                        tmpcookies += (c.getName() + "=" + c.getValue()) + ";";
                        HttpConfig.sCookie = tmpcookies;
                    }
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                System.out.println("访问失败");
            }
        };

        mAsyncHttpClient.post(url, params, asyncHttpResponseHandler);
    }

}

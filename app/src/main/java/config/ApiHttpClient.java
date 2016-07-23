package config;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.client.params.ClientPNames;

import java.util.Locale;

import myapplication.AppContext;

/**
 * Created by Administrator on 2016/6/11.
 */
public class ApiHttpClient {
    private static AsyncHttpClient mAsyncHttpClient;

    public static void setAsyncHttpClient(AsyncHttpClient asyncHttpClient) {
        mAsyncHttpClient = asyncHttpClient;
        PersistentCookieStore myCookieStore = new PersistentCookieStore(AppContext.context);
        mAsyncHttpClient.setCookieStore(myCookieStore);
        mAsyncHttpClient.addHeader("Accept-Language", Locale.getDefault().toString());
        mAsyncHttpClient.addHeader("Host", "www.oschina.net");
        mAsyncHttpClient.addHeader("Connection", "Keep-Alive");
        mAsyncHttpClient.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        mAsyncHttpClient.setUserAgent(PhoneInfo.getUserAgent());
    }

    public static AsyncHttpClient getmAsyncHttpClient() {
        return mAsyncHttpClient;
    }

}

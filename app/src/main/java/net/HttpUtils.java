package net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import config.ApiHttpClient;
import inter.Presenter;
import inter.PresentrModel;

/**
 * Created by Administrator on 2016/5/23.
 */
public class HttpUtils implements PresentrModel {

    private AsyncHttpClient asyncHttpClient;
    private Presenter presenter;

    public HttpUtils(Presenter presenter) {
        asyncHttpClient = ApiHttpClient.getmAsyncHttpClient();
        this.presenter = presenter;
    }

    @Override
    public void getDataFromNet(String url, final RequestParams params) {

        AsyncHttpResponseHandler asyncHttpResponseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                presenter.setView(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                System.out.println("访问失败");
            }
        };
        asyncHttpClient.post(url, params, asyncHttpResponseHandler);
    }
}

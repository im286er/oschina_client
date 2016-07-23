package ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import adapter.MyViewPageAdapter;
import base.BasePageItemFragment;
import config.ApiHttpClient;
import config.AppConfig;
import fragment.FollowFragment;
import myapplication.AppContext;
import test.oschina_client.R;
import widget.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2016/6/12.
 */
public class FollowActiivty extends AppCompatActivity implements View.OnClickListener {
    private TextView mTitle;
    private PagerSlidingTabStrip mPagersSligingTab;
    private ImageView mBack;
    private String[] strName = AppContext.context.getResources().getStringArray(R.array.friends_viewpage_arrays);
    private ViewPager mViewPager;
    private List<BasePageItemFragment> basePages;
    private AsyncHttpClient mAsyncHttpClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_fragment);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTitle = (TextView) findViewById(R.id.activity_title);
        mBack = (ImageView) findViewById(R.id.drawer);
        mPagersSligingTab = (PagerSlidingTabStrip) findViewById(R.id.sliding_menu);
        mAsyncHttpClient = ApiHttpClient.getmAsyncHttpClient();
        mTitle.setText("关注/粉丝");
        basePages = new ArrayList<>();
        for (int i = 0; i < strName.length; i++) {
            TextView textView = new TextView(AppContext.context);
            textView.setText(strName[i]);
            mPagersSligingTab.addTab(textView);
        }
        mPagersSligingTab.setViewPager(mViewPager);

        AsyncHttpResponseHandler asyncHttpResponseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String temp = new String(bytes);
                System.out.println ("获取的数据 : " + temp);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        };
        String url = "http://www.oschina.net/action/api/friends_list?";
        RequestParams requestParams = new RequestParams();
        requestParams.add("uid", String.valueOf(AppConfig.user.getId()));
        requestParams.add("pageIndex", String.valueOf(0));
        requestParams.add("pageSize", String.valueOf(20));
        requestParams.add("relation", String.valueOf(1));
        mAsyncHttpClient.post(url, requestParams, asyncHttpResponseHandler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer:
                break;
        }
    }
}

package fragment;

import android.content.Intent;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;

import base.BaseFragment;
import bean.Event;
import bean.Post;
import bean.PostDetail;
import test.oschina_client.R;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ActionDetailFragment extends BaseFragment {

    private TextView mTitle;
    private TextView mStartTime;
    private TextView mEndTime;
    private TextView mAddress;
    private Button mApply;
    private WebView mWebView;
    private Event mEvent;

    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_event_detail, null);
    }

    @Override
    public void initView(View view) {
        mTitle = (TextView) view.findViewById(R.id.tv_event_title);
        mStartTime = (TextView) view.findViewById(R.id.tv_event_start_time);
        mEndTime = (TextView) view.findViewById(R.id.tv_event_end_time);
        mAddress = (TextView) view.findViewById(R.id.tv_event_spot);
        mApply = (Button) view.findViewById(R.id.bt_event_apply);
        mWebView = (WebView) view.findViewById(R.id.webview);
        mEvent = (Event) getActivity().getIntent().getSerializableExtra("bean");
        initNet();
    }

    public void initNet() {
        mUrl = "http://www.oschina.net/action/api/post_detail?";
        RequestParams params = new RequestParams();
        params.put("id", mEvent.getId());
        mPresenterImple.visitNet(mUrl, params);
    }


    @Override
    public void setView(byte[] bytes) {
        PostDetail postDetail = XmlUtils.toBean(PostDetail.class, new ByteArrayInputStream(bytes));
        Post post = postDetail.getPost();
        Event event = post.getEvent();
        mTitle.setText(event.getTitle());
        mStartTime.setText("开始时间 : " + event.getStartTime());
        mEndTime.setText("结束时间 : " + event.getEndTime());
        mAddress.setText("地址 : " + event.getSpot());
        mWebView.loadDataWithBaseURL("", post.getBody(), "text/html", "UTF-8", "");
    }

}

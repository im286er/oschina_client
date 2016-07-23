package ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;

import adapter.ActionAdapter;
import base.BaseActivity;
import bean.Event;
import bean.EventList;
import custom.RefreshListView;
import inter.Presenter;
import inter.PresenterView;
import inter.RefreshListListener;
import presenter.PresenterImpl;
import test.oschina_client.R;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ActionActivity extends BaseActivity implements PresenterView, RefreshListListener, AdapterView.OnItemClickListener
{
    private RefreshListView mRefreshListView;
    private ActionAdapter mActionAdapter;
    private Presenter presenter;
    private int mPageIndex = 0;

    @Override
    public int getRootView() {
        return R.layout.action_layout;
    }

    @Override
    public void initAction() {
        presenter = new PresenterImpl(this);
        String url = "http://www.oschina.net/action/api/event_list?pageSize=20";
        RequestParams params = new RequestParams();
        params.put("uid", -1);
        params.put("pageIndex", mPageIndex);
        presenter.visitNet(url, params);
    }

    @Override
    public void initView() {
        mRefreshListView = (RefreshListView) findViewById(R.id.action_list);
        mRefreshListView.setOnItemClickListener(this);
    }

    @Override
    public void setView(byte[] bytes) {
        EventList eventList = XmlUtils.toBean(EventList.class, new ByteArrayInputStream(bytes));
        mActionAdapter = new ActionAdapter(eventList.getList());
        mRefreshListView.setAdapter(mActionAdapter);
    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void refreshData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Event event = (Event) mActionAdapter.getItem(position - 1);
        Intent intent = new Intent (this, DetailActivity.class);
        intent.putExtra("bean", event);
        startActivity(intent);
    }
}

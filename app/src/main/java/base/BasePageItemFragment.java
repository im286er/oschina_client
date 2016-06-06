package base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.loopj.android.http.RequestParams;

import custom.RefreshListView;
import inter.Presenter;
import inter.PresenterView;
import inter.RefreshListListener;
import presenter.PresenterImpl;
import test.oschina_client.R;

/**
 * Created by Administrator on 2016/5/22.
 */
public abstract class BasePageItemFragment extends Fragment implements PresenterView, RefreshListListener{

    private RefreshListView mListView;
    private BaseAdapter mBaseAdapter;
    private String mTag;
    private Presenter presenterImple;
    private String url;
    private int pageIndex = 0;

    public BasePageItemFragment() {

    }

    public BasePageItemFragment(String url) {
        this.url = url;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.tabit_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        mListView = (RefreshListView) view.findViewById(R.id.tabitem_listview);
        mListView.setRefreshListener(this);
        presenterImple = new PresenterImpl(this);
        presenterImple.visitNet(url, pageIndex);
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getSubTag() {
        return mTag;
    }

    @Override
    public void setView(Object bean) {
        mBaseAdapter = getBaseAdapter(bean);
        mListView.setAdapter(mBaseAdapter);
    }

    @Override
    public void loadMoreData() {
        pageIndex++;
        presenterImple.visitNet(url, pageIndex);
    }

    @Override
    public void refreshData() {
        presenterImple.visitNet(url, 0);
    }

    public abstract BaseAdapter getBaseAdapter(Object bean);

}

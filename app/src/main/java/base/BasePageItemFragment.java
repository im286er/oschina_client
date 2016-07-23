package base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.loopj.android.http.RequestParams;

import custom.RefreshListView;
import inter.CacheInter;
import inter.Presenter;
import inter.PresenterView;
import inter.RefreshListListener;
import cache.CacheImpl;
import presenter.PresenterImpl;
import test.oschina_client.R;

/**
 * Created by Administrator on 2016/5/22.
 */
public abstract class BasePageItemFragment extends BaseFragment implements RefreshListListener, AdapterView.OnItemClickListener {

    public RefreshListView mListView;
    private BaseAdapter mBaseAdapter;
    private CacheInter cacheInter;
    private int pageIndex = 0;

    public BasePageItemFragment() {

    }

    public BasePageItemFragment(String url) {
        super(url);
    }


    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.tabit_fragment, null);
    }

    @Override
    public void initView(View view) {
        mListView = (RefreshListView) view.findViewById(R.id.tabitem_listview);
        mListView.setRefreshListener(this);
        mListView.setOnItemClickListener(this);
        cacheInter = new CacheImpl(getContext());
        RequestParams params = new RequestParams();
        params.put("pageIndex", pageIndex);
        mPresenterImple.visitNet(mUrl, params);

        if (cacheInter.containKey(mUrl + pageIndex)) {
            GetCacheAsync getCacheAsync = new GetCacheAsync();
            getCacheAsync.execute();
        } else {
//            RequestParams params = new RequestParams();
            params.put("pageIndex", pageIndex);
            mPresenterImple.visitNet(mUrl, params);
        }
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getSubTag() {
        return mTag;
    }

    @Override
    public void setView(byte[] bytes) {

        String temp = new String(bytes);
        MyCacheAsync myCacheAsync = new MyCacheAsync();
        if (pageIndex > 0) {
            loadMore(bytes);
            myCacheAsync.execute(temp);
        } else {
            if (mBaseAdapter != null && !RefreshListView.refreshing) {
                mListView.setAdapter(mBaseAdapter);
            } else {
                RefreshListView.refreshing = false;
                mBaseAdapter = getBaseAdapter(bytes);
                mListView.setAdapter(mBaseAdapter);
            }
            myCacheAsync.execute(temp);
        }
    }

    @Override
    public void loadMoreData() {
        mListView.setLoadMore();
        pageIndex++;
        if (cacheInter.containKey(mUrl + pageIndex)) {
            GetCacheAsync getCacheAsync = new GetCacheAsync();
            getCacheAsync.execute();
        } else {
            RequestParams params = new RequestParams();
            params.put("pageIndex", pageIndex);
            mPresenterImple.visitNet(mUrl, params);
        }
    }


    @Override
    public void refreshData() {
        pageIndex = 0;
        RequestParams params = new RequestParams();
        params.put("pageIndex", pageIndex);
        mPresenterImple.visitNet(mUrl, params);
    }

    public abstract BaseAdapter getBaseAdapter(byte[] bytes);

    public abstract void loadMore(byte[] bytes);

    class MyCacheAsync extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            cacheInter.saveString(mUrl + pageIndex, params[0]);
            return null;
        }
    }

    class GetCacheAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String s) {
            setView(s.getBytes());
        }

        @Override
        protected String doInBackground(String... params) {
            return cacheInter.getString(mUrl + pageIndex);
        }
    }

}

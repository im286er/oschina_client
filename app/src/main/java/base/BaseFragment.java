package base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;

import inter.Presenter;
import inter.PresenterView;
import presenter.PresenterImpl;

/**
 * Created by Administrator on 2016/5/21.
 */
public abstract class BaseFragment extends Fragment implements PresenterView{

    public String mUrl;
    public String mTag;
    public Presenter mPresenterImple;

    public BaseFragment() {

    }

    public BaseFragment(String url) {
        mUrl = url;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getRootView(inflater);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenterImple = new PresenterImpl(this);
        initView(view);
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getmTag() {
        return mTag;
    }

    protected void visitNet(String url, RequestParams requestParams) {
        mPresenterImple.visitNet(url, requestParams);
    }

    public abstract View getRootView(LayoutInflater layoutInflater);

    public abstract void initView(View view);

}

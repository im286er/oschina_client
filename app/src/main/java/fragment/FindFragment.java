package fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.loopj.android.http.RequestParams;

import base.BaseFragment;
import config.AppConfig;
import test.oschina_client.R;
import ui.ActionActivity;
import ui.DetailActivity;

/**
 * Created by Administrator on 2016/5/21.
 */
public class FindFragment extends BaseFragment implements View.OnClickListener{

    private LinearLayout mFriends;
    private LinearLayout mFind;
    private LinearLayout mAction;
    private LinearLayout mScan;
    private LinearLayout mShake;


    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        View view = layoutInflater.inflate(R.layout.findfragment_layout, null);
        return view;
    }

    @Override
    public void initView(View view) {
        mFriends = (LinearLayout) view.findViewById(R.id.friends);
        mFriends.setOnClickListener(this);
        mFind = (LinearLayout) view.findViewById(R.id.find);
        mFind.setOnClickListener(this);
        mAction = (LinearLayout) view.findViewById(R.id.action);
        mAction.setOnClickListener(this);
        mScan = (LinearLayout) view.findViewById(R.id.scan);
        mScan.setOnClickListener(this);
        mShake = (LinearLayout) view.findViewById(R.id.shake);
        mShake.setOnClickListener(this);
    }

    @Override
    public void setView(byte[] bytes) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.friends:
                break;
            case R.id.find:
                break;
            case R.id.action:
                Intent intent = new Intent (getActivity(), ActionActivity.class);
                startActivity(intent);
                break;
            case R.id.scan:
                break;
            case R.id.shake:
                break;
        }
    }
}

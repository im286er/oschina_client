package fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import base.BaseFragment;
import config.AppConfig;
import test.oschina_client.R;
import ui.FollowActiivty;
import ui.LoginActivity;
import utils.ImageDownload;

/**
 * Created by Administrator on 2016/5/21.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout user_center;
    private ImageView mMale;
    private ImageView mFemale;
    private ImageView mUserImage;
    private TextView mUserName;
    private TextView mInfo_Point;
    private TextView mInfo_Collection;
    private TextView mInfo_Following;
    private TextView mInfo_Follower;
    private LinearLayout blog;
    private LinearLayout note;
    private LinearLayout message;
    private LinearLayout team;
    private LinearLayout mInfoBar;
    private LinearLayout mPoint;
    private LinearLayout mCollection;
    private LinearLayout mFollowing;
    private LinearLayout mFollower;

    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        View view = layoutInflater.inflate(R.layout.self_fragment, null);
        return view;
    }

    @Override
    public void initView(View view) {
        user_center = (RelativeLayout) view.findViewById(R.id.user_center);
        user_center.setOnClickListener(this);
        mInfoBar = (LinearLayout) view.findViewById(R.id.info_bar);
        mUserImage = (ImageView) view.findViewById(R.id.user_image);
        mMale = (ImageView) view.findViewById(R.id.sex_male);
        mFemale = (ImageView) view.findViewById(R.id.sex_female);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserImage = (ImageView) view.findViewById(R.id.user_image);
        mUserImage.setOnClickListener(this);
        mInfo_Point = (TextView) view.findViewById(R.id.point);
        mInfo_Collection = (TextView) view.findViewById(R.id.collection);
        mInfo_Following = (TextView) view.findViewById(R.id.follwing);
        mInfo_Follower = (TextView) view.findViewById(R.id.follower);
        blog = (LinearLayout) view.findViewById(R.id.user_blog);
        blog.setOnClickListener(this);
        note = (LinearLayout) view.findViewById(R.id.user_note);
        note.setOnClickListener(this);
        team = (LinearLayout) view.findViewById(R.id.user_team);
        team.setOnClickListener(this);
        message = (LinearLayout) view.findViewById(R.id.user_message);
        message.setOnClickListener(this);
        mPoint = (LinearLayout) view.findViewById(R.id.linear_point);
        mPoint.setOnClickListener(this);
        mCollection = (LinearLayout) view.findViewById(R.id.linear_collection);
        mFollowing = (LinearLayout) view.findViewById(R.id.linear_following);
        mFollowing.setOnClickListener(this);
        mFollower = (LinearLayout) view.findViewById(R.id.linear_follower);
        mFollower.setOnClickListener(this);
        fillUI();
    }

    public void fillUI() {
        if (AppConfig.user != null) {
            if (AppConfig.user.getGender().equals("1")) {
                mMale.setVisibility(View.VISIBLE);
            } else {
                mFemale.setVisibility(View.VISIBLE);
            }
            mInfoBar.setVisibility(View.VISIBLE);
            mUserName.setText(AppConfig.user.getName());
            mInfo_Point.setText(String.valueOf(AppConfig.user.getScore()));
            mInfo_Collection.setText(String.valueOf(AppConfig.user.getFavoritecount()));
            mInfo_Following.setText(String.valueOf(AppConfig.user.getFollowers()));
            mInfo_Follower.setText(String.valueOf(AppConfig.user.getFans()));
            ImageDownload.getImage(mUserImage, AppConfig.user.getPortrait());
        }
    }


    @Override
    public void onClick(View v) {
        if (AppConfig.user == null) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }

        switch (v.getId()) {
            case R.id.user_center:
                break;
            case R.id.user_image:
                break;
            case R.id.user_message:
                break;
            case R.id.user_blog:
                break;
            case R.id.user_team:
                break;
            case R.id.user_note:
                break;
            case R.id.linear_point:
                break;
            case R.id.linear_collection:
                break;
            case R.id.linear_follower:
                break;
            case R.id.linear_following:
                Intent intent = new Intent(getContext(), FollowActiivty.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void setView(byte[] bytes) {

    }
}

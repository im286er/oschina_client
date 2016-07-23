package fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import base.BasePageItemFragment;
import base.BaseViewPagerFragment;
import config.AppConfig;
import myapplication.AppContext;
import test.oschina_client.R;

/**
 * Created by Administrator on 2016/5/21.
 */
public class TweetFragment extends BaseViewPagerFragment {

    private List<BasePageItemFragment> mBasePageItem;
    private String[] strTitles = AppContext.context.getResources().getStringArray(R.array.tweets_viewpage_arrays);
    private String[] urls = {
            "http://www.oschina.net/action/api/tweet_list?uid=0&pageSize=20",
            "http://www.oschina.net/action/api/tweet_list?uid=-1&pageSize=20",
            "http://www.oschina.net/action/api/tweet_list?uid="+AppConfig.user.getId() +"&pageSize=20"
    };

    @Override
    public List<BasePageItemFragment> getFragments() {

        mBasePageItem = new ArrayList<>();
        for (int i = 0; i < strTitles.length; i++) {
            BasePageItemFragment tweetItFragment = new TweetItFragment(urls[i]);
            tweetItFragment.setTag(strTitles[i]);
            mBasePageItem.add(tweetItFragment);
        }
        return mBasePageItem;

    }
}

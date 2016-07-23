package fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.io.ByteArrayInputStream;
import java.util.List;

import adapter.TweetItemAdpater;
import base.BasePageItemFragment;
import bean.Tweet;
import bean.TweetsList;
import ui.DetailActivity;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/12.
 */
public class TweetItFragment extends BasePageItemFragment {

    private BaseAdapter mListAdapter;
    private List<Tweet> mTweets;

    public TweetItFragment() {
    }

    public TweetItFragment(String url) {
        super(url);
    }

    @Override
    public BaseAdapter getBaseAdapter(byte[] bytes) {
        TweetsList tweetsList = XmlUtils.toBean(TweetsList.class, new ByteArrayInputStream(bytes));
        mTweets = tweetsList.getList();
        mListAdapter = new TweetItemAdpater(mTweets);
        return mListAdapter;
    }

    @Override
    public void loadMore(byte[] bytes) {
        TweetsList tweetsList = XmlUtils.toBean(TweetsList.class, new ByteArrayInputStream(bytes));
        mTweets.addAll(tweetsList.getList());
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet tweet = mTweets.get(position - 1);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("bean", tweet);
        startActivity(intent);
    }
}

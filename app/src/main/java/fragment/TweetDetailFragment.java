package fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.loopj.android.http.RequestParams;

import adapter.TweetCommentListAdapter;
import base.BaseFragment;
import bean.CommentList;
import bean.Tweet;
import custom.RefreshListView;
import inter.RefreshListListener;
import myapplication.AppContext;
import test.oschina_client.R;
import ui.TweetListHead;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/29.
 */
public class TweetDetailFragment extends BaseFragment implements RefreshListListener {

    private RefreshListView mCommnetList;
    private int mPageIndex = 0;
    private TweetCommentListAdapter mTweetCommentlistAdapter;
    private TweetListHead mTweetHead;

    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.tweet_detial_fragment, null);
    }

    @Override
    public void initView(View view) {
        Tweet tweet = (Tweet) getActivity().getIntent().getSerializableExtra("bean");
        mTweetHead = new TweetListHead(getActivity(), tweet);
        mTweetHead.visitInterNet();
        mCommnetList = (RefreshListView) view.findViewById(R.id.comment_list);
        mCommnetList.addHeaderView(mTweetHead.getHeadView());
        mUrl = "http://www.oschina.net/action/api/comment_list?catalog=3&pageSiz=20";
        RequestParams params = new RequestParams();
        params.put("pageIndx", mPageIndex);
        params.put("id", tweet.getId());
        visitNet(mUrl, params);
    }

    @Override
    public void setView(byte[] bytes) {
        CommentList commentList = XmlUtils.toBean(CommentList.class, bytes);
        mTweetCommentlistAdapter = new TweetCommentListAdapter(commentList.getList());
        mCommnetList.setAdapter(mTweetCommentlistAdapter);
    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void refreshData() {

    }
}

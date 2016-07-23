package ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.loopj.android.http.RequestParams;
import java.io.ByteArrayInputStream;
import base.BaseWebViewFragment;
import bean.Tweet;
import bean.TweetDetail;
import inter.Presenter;
import inter.PresenterView;
import myapplication.AppContext;
import presenter.PresenterImpl;
import test.oschina_client.R;
import utils.ImageDownload;
import utils.TimeUtils;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/30.
 */
public class TweetListHead implements PresenterView {

    private Context mContext;
    private Presenter mPresenter;
    private String mUrl;
    private View mHeadView;
    private Tweet mTweet;
    private ImageView mHead;
    private TextView mName;
    private TextView mTime;
    private TextView mPlatForm;
    private WebView mWebView;
    private TextView mLike;
    private TextView mComment;
    private TextView mLikeUsers;

    public TweetListHead(Context context, Tweet tweet) {
        mHeadView = View.inflate(AppContext.context, R.layout.tweet_detail_fragment_head, null);
        mContext = context;
        mTweet = tweet;
        initNetParams();
        initView();
    }

    public void visitInterNet() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("id", mTweet.getId());
        mPresenter.visitNet(mUrl, requestParams);
    }


    @Override
    public void setView(byte[] bytes) {
        TweetDetail tweetDetail = XmlUtils.toBean(TweetDetail.class, new ByteArrayInputStream(bytes));
        Tweet tweet = tweetDetail.getTweet();
        ImageDownload.getImage(mHead, tweet.getPortrait());
        mName.setText(tweet.getAuthor());
        mName.setTextColor(mComment.getResources().getColor(R.color.blue));
        mTime.setText(TimeUtils.getTime(tweet.getPubDate()));
        fillWebViewBody ();
        mPlatForm.setText("Android");
        mComment.setText(tweet.getCommentCount());
        mLike.setText(String.valueOf(tweet.getLikeCount()));
        tweet.setLikeUsers(AppContext.context, mLikeUsers, false);
    }

    public View getHeadView() {
        return mHeadView;
    }

    private void initNetParams() {
        mPresenter = new PresenterImpl(this);
        mUrl = "http://www.oschina.net/action/api/tweet_detail?";
    }

    private void initView() {
        mHead = (ImageView) mHeadView.findViewById(R.id.iv_avatar);
        mName = (TextView) mHeadView.findViewById(R.id.tv_name);
        mTime = (TextView) mHeadView.findViewById(R.id.tv_time);
        mPlatForm = (TextView) mHeadView.findViewById(R.id.tv_from);
        mWebView = (WebView) mHeadView.findViewById(R.id.webview);
        mLike = (TextView) mHeadView.findViewById(R.id.tv_like_state);
        mComment = (TextView) mHeadView.findViewById(R.id.tv_comment_count);
        mLikeUsers = (TextView) mHeadView.findViewById(R.id.tv_likeusers);
    }

    private void fillWebViewBody() {
        StringBuffer body = new StringBuffer();
        body.append("<body class='night'><div class='contentstyle' id='article_body'>");
        body.append(BaseWebViewFragment.linkCss + BaseWebViewFragment.WEB_LOAD_IMAGES);
        StringBuilder tweetbody = new StringBuilder(mTweet.getBody());
        String tweetBody = TextUtils.isEmpty(mTweet.getImgSmall()) ? tweetbody
                .toString() : tweetbody.toString() + "<br/><img src=\""
                + mTweet.getImgSmall() + "\">";
        body.append(setHtmlCotentSupportImagePreview(tweetBody));
        // 封尾
        body.append("</div></body>");
        mWebView.loadDataWithBaseURL(null, body.toString(), "text/html",
                "utf-8", null);
    }

    private String setHtmlCotentSupportImagePreview(String body) {
        // 过滤掉 img标签的width,height属性
        body = body.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
        body = body.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");
        return body.replaceAll("(<img[^>]+src=\")(\\S+)\"",
                "$1$2\" onClick=\"javascript:mWebViewImageListener.showImagePreview('"
                        + mTweet.getImgBig() + "')\"");
    }
}

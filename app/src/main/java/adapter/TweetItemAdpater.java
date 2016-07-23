package adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import base.BaseListAdapter;
import bean.Tweet;
import custom.TweetTextView;
import myapplication.AppContext;
import test.oschina_client.R;
import utils.ImageDownload;
import utils.TimeUtils;

/**
 * Created by Administrator on 2016/6/12.
 */
public class TweetItemAdpater extends BaseListAdapter<Tweet> {

    private List<Tweet> mList;

    public TweetItemAdpater(List<Tweet> list) {
        super(list);
        mList = list;
    }

    @Override
    public View getListItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.tweet_layout, null);
            viewHolder.mTweet_Time = (TextView) convertView.findViewById(R.id.tweet_time);
            viewHolder.mTweet_Name = (TextView) convertView.findViewById(R.id.tweet_name);
            viewHolder.mTweet_Comment_Count = (TextView) convertView.findViewById(R.id.tweet_comment_count);
            viewHolder.mTweet_Image = (ImageView) convertView.findViewById(R.id.tweet_iamge);
            viewHolder.mTweet_Content = (TweetTextView) convertView.findViewById(R.id.tweet_content);
            viewHolder.mTweet_Head = (ImageView) convertView.findViewById(R.id.tweet_head);
            viewHolder.mTweet_Like_Users = (TextView) convertView.findViewById(R.id.tweet_like_users);
            viewHolder.mTweet_Phone = (TextView) convertView.findViewById(R.id.tweet_phone);
            viewHolder.mTweet_Background = (RelativeLayout) convertView.findViewById(R.id.comment_list_like);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTweet_Phone.setText(String.valueOf(mList.get(position).getAppclient()));
        mList.get(position).setLikeUsers(AppContext.context, viewHolder.mTweet_Like_Users, true);
        viewHolder.mTweet_Content.setText(mList.get(position).getBody());
        viewHolder.mTweet_Time.setText(TimeUtils.getTime(mList.get(position).getPubDate()));//修改时间
        viewHolder.mTweet_Name.setText(mList.get(position).getAuthor());

        if (!mList.get(position).getPortrait().equals("")
                && mList.get(position).getPortrait() != null) {
            ImageDownload.getImage(viewHolder.mTweet_Head, mList.get(position).getPortrait());
        }

        if (mList.get(position).getImgSmall() != null && !mList.get(position).getImgSmall().equals("")) {
            viewHolder.mTweet_Image.setVisibility(View.VISIBLE);
            ImageDownload.getImage(viewHolder.mTweet_Image, mList.get(position).getImgSmall());
        }

        if (mList.get(position).getLikeCount() == 0) {
            viewHolder.mTweet_Background.setVisibility(View.GONE);
        } else {
            viewHolder.mTweet_Comment_Count.setText(mList.get(position).getCommentCount());
        }

        return convertView;
    }

    static class ViewHolder {
        public ImageView mTweet_Head;
        public TextView mTweet_Name;
        public TweetTextView mTweet_Content;
        public TextView mTweet_Time;
        public TextView mTweet_Phone;
        public ImageView mTweet_Like;
        public TextView mTweet_Comment_Count;
        public TextView mTweet_Like_Users;
        public ImageView mTweet_Image;
        public RelativeLayout mTweet_Background;
    }
}

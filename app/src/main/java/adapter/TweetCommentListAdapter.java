package adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import base.BaseListAdapter;
import bean.Comment;
import dalvik.system.DexClassLoader;
import test.oschina_client.R;
import utils.ImageDownload;
import utils.TimeUtils;

/**
 * Created by Administrator on 2016/6/30.
 */
public class TweetCommentListAdapter extends BaseListAdapter<Comment> {


    public TweetCommentListAdapter(List<Comment> list) {
        super(list);
    }

    @Override
    public View getListItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;

        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.tweet_comment_item, null);
            mViewHolder.head = (ImageView) convertView.findViewById(R.id.iv_avatar);
            mViewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.phonePlatform = (TextView) convertView.findViewById(R.id.tv_from);
            mViewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            mViewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.name.setText(mList.get(position).getAuthor());
        mViewHolder.name.setTextColor(parent.getResources().getColor(R.color.blue));
        mViewHolder.time.setText(TimeUtils.getTime(mList.get(position).getPubDate()));
        if (!mList.get(position).getPortrait().equals("") && mList.get(position).getPortrait() != null) {
            ImageDownload.getImage(mViewHolder.head, mList.get(position).getPortrait());
        }
        mViewHolder.content.setText(mList.get(position).getContent());
        return convertView;
    }

    static class ViewHolder {
        ImageView head;
        TextView name;
        TextView time;
        TextView phonePlatform;
        TextView content;
    }
}

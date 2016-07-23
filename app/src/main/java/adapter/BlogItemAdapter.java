package adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.BaseListAdapter;
import bean.Blog;
import bean.News;
import inter.RefreshListListener;
import myapplication.AppContext;
import test.oschina_client.R;
import utils.HaveReadList;
import utils.TimeUtils;

/**
 * Created by Administrator on 2016/6/20.
 */
public class BlogItemAdapter extends BaseListAdapter<Blog> {


    public BlogItemAdapter(List<Blog> list) {
        super(list);
    }

    @Override
    public View getListItemView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            System.out.println("convertView为空");
            convertView = View.inflate(parent.getContext(), R.layout.news_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.news_item_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.news_item_content);
            viewHolder.author = (TextView) convertView.findViewById(R.id.news_item_bottom_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.news_item_bottom_time);
            viewHolder.comments = (TextView) convertView.findViewById(R.id.news_item_bottom_comments);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(mList.get(position).getTitle());
        if (HaveReadList.exitsItem(String.valueOf(mList.get(position).getId()))) {
            viewHolder.title.setTextColor(AppContext.context.getResources().getColor(R.color.gray));
        } else {
            viewHolder.title.setTextColor(AppContext.context.getResources().getColor(R.color.day_textColor));
        }
        viewHolder.content.setText(mList.get(position).getBody());
        viewHolder.content.setTextColor(AppContext.context.getResources().getColor(R.color.day_infoTextColor));
        viewHolder.author.setText(mList.get(position).getAuthor());
        viewHolder.time.setText(TimeUtils.getTime(mList.get(position).getPubDate()));
        viewHolder.comments.setText(String.valueOf(mList.get(position).getCommentCount()));
        return convertView;
    }

    static class ViewHolder {
        public TextView title;
        public TextView content;
        public TextView author;
        public TextView time;
        public TextView comments;
    }

}

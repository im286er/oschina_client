package fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

import adapter.BlogItemAdapter;
import adapter.NewsItemAdapter;
import base.BasePageItemFragment;
import bean.Blog;
import bean.BlogList;
import test.oschina_client.R;
import ui.DetailActivity;
import utils.HaveReadList;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/20.
 */
public class BlogItFragment extends BasePageItemFragment {

    private BaseAdapter mListAdapter;
    private List<Blog> mList;

    public BlogItFragment() {

    }

    public BlogItFragment(String url) {
        super (url);
    }


    @Override
    public BaseAdapter getBaseAdapter(byte[] bytes) {
        BlogList newsList = XmlUtils.toBean(BlogList.class, new ByteArrayInputStream(bytes));
        mList = newsList.getList();
        mListAdapter = new BlogItemAdapter(mList);
        return mListAdapter;
    }

    @Override
    public void loadMore(byte[] bytes) {
        BlogList newsList = XmlUtils.toBean(BlogList.class, new ByteArrayInputStream(bytes));
        mList.addAll(newsList.getList());
        mListAdapter.notifyDataSetChanged();
        mListView.footViewDisappear();
        mListView.footViewAppear();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view.findViewById(R.id.news_item_title);
        textView.setTextColor(getResources().getColor(R.color.gray));
        HaveReadList.saveItToReadList(String.valueOf(mList.get(position - 1).getId()), mList.get(position - 1).getTitle());
        Intent intent = new Intent(getContext(), DetailActivity.class);
        Blog blog = mList.get(position - 1);
        intent.putExtra("bean", blog);
        startActivity (intent);
    }
}

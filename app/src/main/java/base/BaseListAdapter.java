package base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import config.AppConfig;

/**
 * Created by Administrator on 2016/5/22.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter
{
    public List<T> mList;

    public BaseListAdapter (List<T> list)
    {
        mList = list;
    }

    @Override
    public int getCount()
    {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getListItemView(position, convertView, parent);
    }

    public List<T> getList ()
    {
        return mList;
    }

    public abstract View getListItemView (int position, View convertView, ViewGroup parent);
}

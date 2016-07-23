package adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import base.BaseListAdapter;

import bean.Event;
import test.oschina_client.R;
import utils.ImageDownload;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ActionAdapter extends BaseListAdapter<Event> {

    private ViewHolder mViewHolder;

    public ActionAdapter(List<Event> list) {
        super(list);
        mViewHolder = new ViewHolder();
    }

    @Override
    public View getListItemView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.action_item, null);
            mViewHolder.mImageView = (ImageView) convertView.findViewById(R.id.fade);
            mViewHolder.mTitle = (TextView) convertView.findViewById(R.id.action_title);
            mViewHolder.mTime = (TextView) convertView.findViewById(R.id.action_time);
            mViewHolder.mAddress = (TextView) convertView.findViewById(R.id.action_address);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        ImageDownload.getImage(mViewHolder.mImageView, mList.get(position).getCover());
        mViewHolder.mTitle.setText(mList.get(position).getTitle());
        mViewHolder.mTime.setText(mList.get(position).getStartTime());
        mViewHolder.mAddress.setText(mList.get(position).getSpot());

        return convertView;
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mTime;
        TextView mAddress;
    }

}

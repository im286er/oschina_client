package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;
import base.BasePageItemFragment;


/**
 * Created by Administrator on 2016/5/21.
 */
public class MyViewPageAdapter extends FragmentStatePagerAdapter
{
    private List<BasePageItemFragment> mFragments;

    public MyViewPageAdapter(FragmentManager fm, List<BasePageItemFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }
}

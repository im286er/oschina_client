package base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import adapter.MyViewPageAdapter;
import test.oschina_client.MainActivity;
import test.oschina_client.R;
import widget.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2016/5/21.
 */
public abstract class BaseViewPagerFragment extends Fragment implements View.OnClickListener
{
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mSlidingMenu;
    private PagerAdapter mPagerAdapter;
    private List<BasePageItemFragment> mFragments;
    private ImageView mDrawer;
    private ImageView mSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.viewpager_fragment, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mSlidingMenu = (PagerSlidingTabStrip) view.findViewById(R.id.sliding_menu);
        mSearch = (ImageView) view.findViewById(R.id.search);
        mSearch.setOnClickListener(this);
        mDrawer = (ImageView) view.findViewById(R.id.drawer);
        mDrawer.setOnClickListener(this);
        mFragments = getFragments();
        mPagerAdapter = new MyViewPageAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        initTab ();
    }

    private void initTab ()
    {
        for (int i = 0; i < mFragments.size(); i++)
        {
            View view = View.inflate(getContext(), R.layout.base_viewpage_fragment_tab_item, null);
            TextView tab = (TextView) view.findViewById(R.id.tab_title);
            tab.setText(mFragments.get(i).getSubTag());
            mSlidingMenu.addTab(view);
        }
        mSlidingMenu.setViewPager(mViewPager);
    }

    public abstract List<BasePageItemFragment> getFragments ();

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.drawer:
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.search:
                break;
        }
    }
}

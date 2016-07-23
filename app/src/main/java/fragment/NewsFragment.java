package fragment;

import java.util.ArrayList;
import java.util.List;

import base.BasePageItemFragment;
import base.BaseViewPagerFragment;
import myapplication.AppContext;
import test.oschina_client.R;

/**
 * Created by Administrator on 2016/5/21.
 */
public class NewsFragment extends BaseViewPagerFragment {

    private List<BasePageItemFragment> mFragments;
    private String[] title = AppContext.context.getResources().getStringArray(R.array.news_viewpage_arrays);
    private String[] urls = {
            "http://www.oschina.net/action/api/news_list?catalog=1&pageSize=20",
            "http://www.oschina.net/action/api/news_list?catalog=4&pageSize=20&show=week",
            "http://www.oschina.net/action/api/blog_list?pageSize=20&type=latest",
            "http://www.oschina.net/action/api/blog_list?pageSize=20&type=recommend"
    };

    @Override
    public List<BasePageItemFragment> getFragments() {
        mFragments = new ArrayList<>();
        int size = urls.length;

        for (int i = 0; i < size / 2; i++) {
            BasePageItemFragment fragment = new NewsItFragment(urls[i]);
            fragment.setTag(title[i]);
            mFragments.add(fragment);
        }

        for (int i = 2; i < size; i++) {
            BasePageItemFragment fragment = new BlogItFragment(urls[i]);
            fragment.setTag(title[i]);
            mFragments.add(fragment);
        }


        return mFragments;
    }
}

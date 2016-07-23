package list;

import bean.Blog;
import bean.Event;
import bean.News;
import bean.Tweet;
import fragment.ActionDetailFragment;
import fragment.BlogDetialFragment;
import fragment.NewsDetailFragment;
import fragment.TweetDetailFragment;

/**
 * Created by Administrator on 2016/6/23.
 */
public class FragmentList
{
    public static Class getFragmentClazz (Object state)
    {
        if (state instanceof Blog)
        {
            return BlogDetialFragment.class;
        }
        else if (state instanceof News)
        {
            return NewsDetailFragment.class;
        }
        else if (state instanceof Tweet)
        {
            return TweetDetailFragment.class;
        }
        else if (state instanceof Event)
        {
            return ActionDetailFragment.class;
        }
        else
        {
            return null;
        }
    }
}

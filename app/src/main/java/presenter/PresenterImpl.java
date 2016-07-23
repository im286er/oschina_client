package presenter;

import com.loopj.android.http.RequestParams;

import net.HttpUtils;

import fragment.NewsItFragment;
import inter.Presenter;
import inter.PresenterView;
import inter.PresentrModel;

/**
 * Created by Administrator on 2016/5/28.
 */
public class PresenterImpl implements Presenter
{

    private PresenterView presenterView;
    private PresentrModel presentrModel;

    public PresenterImpl (PresenterView presenterView)
    {
        this.presenterView = presenterView;
        presentrModel = new HttpUtils(this);
    }


    @Override
    public void visitNet(String url, RequestParams params)
    {
        presentrModel.getDataFromNet(url, params);
    }

    @Override
    public void setView(byte[] bytes)
    {
        presenterView.setView(bytes);
    }
}

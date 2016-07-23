package base;

import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import com.loopj.android.http.RequestParams;

import bean.News;
import config.AppConfig;
import test.oschina_client.R;
import utils.StringUtils;
import utils.TimeUtils;

/**
 * Created by Administrator on 2016/6/24.
 */
public abstract class BaseWebViewFragment<T> extends BaseFragment {

    public static final String linkCss = "<script type=\"text/javascript\" src=\"file:///android_asset/shCore.js\"></script>"
            + "<script type=\"text/javascript\" src=\"file:///android_asset/brush.js\"></script>"
            + "<script type=\"text/javascript\" src=\"file:///android_asset/client.js\"></script>"
            + "<script type=\"text/javascript\" src=\"file:///android_asset/detail_page.js\"></script>"
            + "<script type=\"text/javascript\">SyntaxHighlighter.all();</script>"
            + "<script type=\"text/javascript\">function showImagePreview(var url){window.location.url= url;}</script>"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shThemeDefault.css\">"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shCore.css\">"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/css/common.css\">";

    public static final String WEB_LOAD_IMAGES = "<script type=\"text/javascript\"> " +
            "var allImgUrls = getAllImgSrc(document.body.innerHTML);</script>";

    protected WebView mWebView;


    public BaseWebViewFragment() {
    }

    public BaseWebViewFragment(String url) {
        super (url);
    }

    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.webview_detail_fragment, null);
    }


    @Override
    public void initView(View view) {
        mWebView = (WebView)view.findViewById(R.id.webview);
        AppConfig.initWebView(mWebView);
        initSubData ();
        getDataFromNet ();
    }

    public String setHtmlCotentSupportImagePreview(String body) {
        // 读取用户设置：是否加载文章图片--默认有wifi下始终加载图片
        // 过滤掉 img标签的width,height属性
        body = body.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
        body = body.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");
        // 添加点击图片放大支持
        // 添加点击图片放大支持
        body = body.replaceAll("(<img[^>]+src=\")(\\S+)\"",
                "$1$2\" onClick=\"showImagePreview('$2')\"");
        return body;
    }

    public abstract void initSubData ();

    public abstract void getDataFromNet ();

    protected abstract String getWebViewBody(T detail);

}

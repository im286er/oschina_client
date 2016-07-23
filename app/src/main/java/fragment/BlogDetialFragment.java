package fragment;

import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;

import base.BaseWebViewFragment;
import bean.Blog;
import bean.BlogDetail;
import utils.TimeUtils;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/26.
 */
public class BlogDetialFragment extends BaseWebViewFragment<Blog>
{

    private Blog detail;

    @Override
    public void initSubData() {
        detail = (Blog) getActivity().getIntent().getSerializableExtra("bean");
        mUrl = "http://www.oschina.net/action/api/blog_detail?";
    }

    @Override
    public void getDataFromNet() {
        RequestParams params = new RequestParams();
        params.put("id", detail.getId());
        visitNet(mUrl, params);
    }

    @Override
    public void setView(byte[] bytes) {
        BlogDetail blogDetail = XmlUtils.toBean(BlogDetail.class, new ByteArrayInputStream(bytes));
        mWebView.loadDataWithBaseURL("", getWebViewBody(blogDetail.getBlog()), "text/html", "UTF-8", "");
    }

    @Override
    protected String getWebViewBody(Blog detail) {
        StringBuffer body = new StringBuffer();
        body.append(linkCss).append(WEB_LOAD_IMAGES);
//        body.append(ThemeSwitchUtils.getWebViewBodyString());
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", detail.getTitle()));
        // 添加作者和时间
        String time = TimeUtils.getTime(detail.getPubDate());
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", detail.getAuthorId(), detail.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        body.append(setHtmlCotentSupportImagePreview(detail.getBody()));
        // 封尾
        body.append("</div></body>");
        return body.toString();
    }
}

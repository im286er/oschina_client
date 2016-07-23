package fragment;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;

import base.BaseWebViewFragment;
import bean.News;
import bean.NewsDetail;
import utils.StringUtils;
import utils.TimeUtils;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/23.
 */
public class NewsDetailFragment extends BaseWebViewFragment<News> {

    private News mDetail;

    public NewsDetailFragment() {

    }

    @Override
    public void initSubData() {
        mDetail = (News) getActivity().getIntent().getSerializableExtra("bean");
        mUrl = "http://www.oschina.net/action/api/news_detail?";
    }

    @Override
    public void getDataFromNet() {
        RequestParams params = new RequestParams();
        params.put("id", mDetail.getId());
        visitNet(mUrl, params);
    }

    @Override
    public void setView(byte[] bytes) {
        NewsDetail detail = XmlUtils.toBean(NewsDetail.class, new ByteArrayInputStream(bytes));
        mWebView.loadDataWithBaseURL("", getWebViewBody(detail.getNews()), "text/html", "UTF-8", "");
    }

    protected String getWebViewBody(News detail) {

        StringBuffer body = new StringBuffer();
//        body.append(linkCss).append(WEB_LOAD_IMAGES);
        body.append("<body ><div class='contentstyle' id='article_body'>");
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", detail.getTitle()));
        // 添加作者和时间
        String time = TimeUtils.getTime(detail.getPubDate());
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", detail.getAuthorId(), detail.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        body.append(setHtmlCotentSupportImagePreview(detail.getBody()));
        // 更多关于***软件的信息
        String softwareName = detail.getSoftwareName();
        String softwareLink = detail.getSoftwareLink();
        if (!StringUtils.isEmpty(softwareName)
                && !StringUtils.isEmpty(softwareLink))
            body.append(String
                    .format("<div class='oschina_software' style='margin-top:8px;font-weight:bold'>更多关于:&nbsp;<a href='%s'>%s</a>&nbsp;的详细信息</div>",
                            softwareLink, softwareName));

        // 相关新闻
        if (detail != null && detail.getRelatives() != null
                && detail.getRelatives().size() > 0) {
            String strRelative = "";
            for (News.Relative relative : detail.getRelatives()) {
                strRelative += String.format(
                        "<li><a href='%s' style='text-decoration:none'>%s</a></li>",
                        relative.url, relative.title);
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b><ul class='about'>%s</ul>",
                    strRelative));
        }
        body.append("<br/>");
        // 封尾
        body.append("</div></body>");
        return body.toString();
    }
}

package config;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ZoomButtonsController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import bean.User;
import utils.CloseEz;

/**
 * Created by Administrator on 2016/6/8.
 */
public class AppConfig {

    public static boolean networkOk = false;
    public static boolean Idle = false;
    public static boolean login = false;
    public static User user = null;
    private final static String APP_CONFIG = "config";
    public final static String HOST = "www.oschina.net";
    public final static String CONF_COOKIE = "cookie";
    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
    public final static String KEY_LOAD_IMAGE = "KEY_LOAD_IMAGE";
    public final static String KEY_NOTIFICATION_ACCEPT = "KEY_NOTIFICATION_ACCEPT";
    public final static String KEY_NOTIFICATION_SOUND = "KEY_NOTIFICATION_SOUND";
    public final static String KEY_NOTIFICATION_VIBRATION = "KEY_NOTIFICATION_VIBRATION";
    public final static String KEY_NOTIFICATION_DISABLE_WHEN_EXIT = "KEY_NOTIFICATION_DISABLE_WHEN_EXIT";

    public final static String LAST_QUESTION_CATEGORY_IDX = "LAST_QUESTION_CATEGORY_IDX";
    public final static String KEY_DAILY_ENGLISH = "KEY_DAILY_ENGLISH";
    public final static String KEY_GET_LAST_DAILY_ENG = "KEY_GET_LAST_DAILY_IDX";
    public final static String KEY_NOTE_DRAFT = "KEY_NOTE_DRAFT";
    public final static String KEY_TWEET_DRAFT = "KEY_TWEET_DRAFT";
    public final static String KEY_QUESTION_TITLE_DRAFT = "KEY_QUESTION_TITLE_DRAFT";
    public final static String KEY_QUESTION_CONTENT_DRAFT = "KEY_QUESTION_CONTENT_DRAFT";
    public final static String KEY_QUESTION_TYPE_DRAFT = "KEY_QUESTION_TYPE_DRAFT";
    public final static String KEY_QUESTION_LMK_DRAFT = "KEY_QUESTION_LMK_DRAFT";

    public final static String KEY_FIRST_START = "KEY_FIRST_START";
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory()
            + File.separator + "OSChina" + File.separator + "osc_img" + File.separator;
    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }

        return appConfig;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String get(String key) {
        Properties props = get();
        return (props != null) ? props.getProperty(key) : null;
    }

    private Properties get() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseEz.cloasEz(fis);
        }

        return props;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);
            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseEz.cloasEz(fos);
        }
    }

    public void set(Properties properties) {
        Properties props = get();
        props.putAll(properties);
        setProps(props);
    }

    public void set(String key, String value) {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public void remove(String... key) {
        Properties props = get();
        for (String s : key) {
            props.remove(key);
        }
        setProps(props);
    }

    public static void initWebView(WebView webView)
    {
        WebSettings settings = webView.getSettings();
        settings.setDefaultFontSize(15);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        int sysVersion = Build.VERSION.SDK_INT;
        if (sysVersion >= 11) {
            settings.setDisplayZoomControls(false);
        } else {
            ZoomButtonsController zbc = new ZoomButtonsController(webView);
            zbc.getZoomControls().setVisibility(View.GONE);
        }

        webView.setWebViewClient(getWebViewClient());
    }

    public static WebViewClient getWebViewClient() {

        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                showUrlRedirect(view.getContext(), url);
                return true;
            }
        };
    }
}


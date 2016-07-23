package myapplication;

import android.app.Application;
import android.content.pm.PackageInfo;

import com.loopj.android.http.AsyncHttpClient;

import java.util.UUID;

import config.ApiHttpClient;
import config.AppConfig;
import cache.MemoryCache;
import utils.StringUtils;

/**
 * Created by Administrator on 2016/5/22.
 */
public class AppContext extends Application {
    public static AppContext context;
    private AsyncHttpClient mAsyncHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
//        UserFromDisk userFromDisk = new UserFromDisk();
//        userFromDisk.execute(1);
        initHttpClient();
        MemoryCache.initMemoryCache();
    }



    private void initHttpClient() {
        mAsyncHttpClient = new AsyncHttpClient();
        ApiHttpClient.setAsyncHttpClient(mAsyncHttpClient);
    }


    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmail(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (info == null) {
            info = new PackageInfo();
        }
        return info;
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

}

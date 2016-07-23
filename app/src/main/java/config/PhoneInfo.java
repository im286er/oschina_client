package config;

import myapplication.AppContext;

/**
 * Created by Administrator on 2016/6/10.
 */
public class PhoneInfo
{
    public static String getUserAgent() {
        StringBuilder ua = new StringBuilder("OSChina.NET");
        ua.append('/' + AppContext.context.getPackageInfo().versionName + '_'
                + AppContext.context.getPackageInfo().versionCode);// app版本信息
        ua.append("/Android");// 手机系统平台
        ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
        ua.append("/" + android.os.Build.MODEL); // 手机型号
        ua.append("/" + AppContext.context.getAppId());// 客户端唯一标识
        return ua.toString();
    }
}

package utils;

import android.content.SharedPreferences;

import myapplication.AppContext;

/**
 * Created by Administrator on 2016/6/22.
 */
public class HaveReadList {
    private static SharedPreferences sharedPreferences =
            AppContext.context.getSharedPreferences("readlist", AppContext.context.MODE_PRIVATE);

    private static SharedPreferences.Editor editor = sharedPreferences.edit();

    public static void saveItToReadList(String key, String value) {
        editor.putString(key, value).commit();
    }

    public static String getReadListItem(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static boolean exitsItem(String key) {
        return sharedPreferences.contains(key);
    }

}

package utils;

import android.widget.Toast;

import myapplication.AppContext;

/**
 * Created by Administrator on 2016/6/7.
 */
public class ToastUitls
{

    public static void showToastLong (String content)
    {
        Toast.makeText(AppContext.context, content, Toast.LENGTH_LONG).show();
    }

    public static void showToastShort (String content)
    {
        Toast.makeText(AppContext.context, content, Toast.LENGTH_SHORT).show();
    }

}

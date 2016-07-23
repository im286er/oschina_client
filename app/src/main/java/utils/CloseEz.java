package utils;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CloseEz
{

    public static void cloasEz (Closeable cls)
    {
        try {
            cls.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

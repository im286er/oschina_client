package runteset;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import bean.User;
import config.AppConfig;
import utils.CloseEz;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ObjectFromDiskTest implements Runnable {
    private Object mObj;

    public ObjectFromDiskTest(Object obj) {
        mObj = obj;
    }


    @Override
    public void run() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        File file = new File("/sdcard/" + "obj.txt");
        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            AppConfig.user = (User) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseEz.cloasEz(fileInputStream);
            CloseEz.cloasEz(objectInputStream);
        }
    }
}

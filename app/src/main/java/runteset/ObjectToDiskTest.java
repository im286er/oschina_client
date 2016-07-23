package runteset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import utils.CloseEz;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ObjectToDiskTest implements Runnable {

    private Object mObj;

    public ObjectToDiskTest(Object obj) {
        mObj = obj;
    }

    @Override
    public void run() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            File file = new File("/sdcard/" + "obj.txt");
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(mObj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseEz.cloasEz(fileOutputStream);
            CloseEz.cloasEz(objectOutputStream);
        }
    }
}

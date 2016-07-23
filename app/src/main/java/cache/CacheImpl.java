package cache;

import android.content.Context;

import java.util.Map;
import java.util.Set;

import inter.CacheInter;
import inter.SharedInter;

/**
 * Created by Administrator on 2016/6/21.
 */
public class CacheImpl implements CacheInter{

    private SharedInter shared;

    public CacheImpl(Context context) {
      shared = new SharedImpl(context);
    }

    @Override
    public boolean containKey(String key) {
        return shared.containKey(key);
    }

    @Override
    public void saveString(String key, String value) {
        shared.saveString(key, value);
    }

    @Override
    public void saveInt(String key, int value) {
       shared.saveInt(key, value);
    }

    @Override
    public void saveBoolean(String key, boolean value) {
        shared.saveBoolean(key,value);
    }

    @Override
    public void saveFloat(String key, float value) {
        shared.saveFloat(key, value);
    }

    @Override
    public void saveLong(String key, long value) {
        shared.saveLong(key, value);
    }

    @Override
    public void saveStringSet(String key, Set<String> value) {
        shared.saveStringSet(key, value);
    }

    @Override
    public String getString(String key) {
        return shared.getString(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return shared.getBoolean(key);
    }

    @Override
    public long getLong(String key) {
        return shared.getLong(key);
    }

    @Override
    public float getFloat(String key) {
        return shared.getFloat(key);
    }

    @Override
    public Set<String> getSetString(String key) {
        return shared.getSetString(key);
    }

    @Override
    public int getInt(String key) {
        return shared.getInt(key);
    }

    @Override
    public Map getAll() {
        return shared.getAll();
    }
}

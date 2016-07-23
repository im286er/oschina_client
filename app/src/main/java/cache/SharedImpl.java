package cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

import inter.SharedInter;

/**
 * Created by Administrator on 2016/6/21.
 */
public class SharedImpl implements SharedInter {

    private SharedPreferences sharedPreferences;
    private String cacheName = "netdatacache";
    private SharedPreferences.Editor editor;

    public SharedImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(cacheName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public boolean containKey(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public void saveString(String key, String value) {
        editor.putString(key, value).commit();
    }

    @Override
    public void saveInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    @Override
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    @Override
    public void saveFloat(String key, float value) {
        editor.putFloat(key, value).commit();
    }

    @Override
    public void saveStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value).commit();
    }

    @Override
    public void saveLong(String key, long value) {
        editor.putLong(key, value).commit();
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public long getLong(String key) {
        return sharedPreferences.getLong(key, -99);
    }

    @Override
    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, -99);
    }

    @Override
    public Set<String> getSetString(String key) {
        return sharedPreferences.getStringSet(key, null);
    }

    @Override
    public int getInt(String key) {
        return sharedPreferences.getInt(key, -99);
    }

    @Override
    public Map getAll() {
        return sharedPreferences.getAll();
    }
}

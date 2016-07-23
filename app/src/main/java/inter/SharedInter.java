package inter;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/21.
 */
public interface SharedInter
{
    public void saveString(String key, String value);
    public void saveInt(String key, int value);
    public void saveBoolean(String key, boolean value);
    public void saveFloat(String key, float value);
    public void saveLong(String key, long value);
    public void saveStringSet(String key, Set<String> value);
    public String getString(String key);
    public boolean getBoolean(String key);
    public long getLong(String key);
    public float getFloat(String key);
    public Set<String> getSetString(String key);
    public int getInt(String key);
    public Map getAll();
    public boolean containKey (String key);
}

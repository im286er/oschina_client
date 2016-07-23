package cache;

import android.support.v4.util.LruCache;

/**
 * Created by Administrator on 2016/6/22.
 */
public class MemoryCache {

    public static LruCache<String, String> lruCache;

    public static void initMemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 8;
        lruCache = new LruCache<>(maxMemory);
    }


    public static void saveDataToMemoryCache(String key, String value) {
        lruCache.put(key, value);
    }

    public static String getDataFromMemroyCache(String key) {
        return lruCache.get(key);
    }

}

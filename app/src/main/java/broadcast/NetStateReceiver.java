package broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import config.AppConfig;

/**
 * Created by Administrator on 2016/6/21.
 */
public class NetStateReceiver extends BroadcastReceiver {

    ConnectivityManager connectivityManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                AppConfig.networkOk = true;
            } else {
                AppConfig.networkOk = false;
            }
        }
    }
}

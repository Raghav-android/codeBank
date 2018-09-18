package com.myezassets.utils.others;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.myezassets.ui.authenticate.LoginActivity;

import static com.myezassets.utils.others.Constants.TOKEN_EXPIRED;

/**
 * Created on 28/8/2018.
 */

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    public static boolean hasNetwork() {
        return instance.checkIfHasNetwork();
    }

    public static void tokenExpired() {
        instance.onTokenExpired();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initSharedHelper();
    }

    private void initSharedHelper() {
        SharedPrefUtil.init(this);
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void onTokenExpired() {
//        ToastUtils.shortToast(getString(R.string.session_expire));
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(TOKEN_EXPIRED, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

package com.hbck.fastworkorder.util;

import android.app.Activity;
import android.app.Application;

/**
 * Created by 丁 on 2017-04-06.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        SPUtil.sp = getSharedPreferences(SPUtil.SP_NAME, Activity.MODE_PRIVATE);
    }
}

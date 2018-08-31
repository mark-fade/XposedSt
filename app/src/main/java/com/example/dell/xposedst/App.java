package com.example.dell.xposedst;

import android.app.Application;
import android.content.Context;

/*****************************
 * @作者：chenk
 * @版本：
 * @创建日期：2017/12/28.16:22
 * @描述：
 * @修订历史：
 *
 *
 * ******************************/

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        init();
    }

    private void init() {
    }

    public static Context getAppContext() {
        return context;
    }
}

package com.example.fetchchallenge;

import android.app.Application;
import android.content.Context;

public class FetchApp extends Application {

    private static Context context;
    private FetchApp MyApplication;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    //Allows access to application context in ItemRepository so that a room db can be created
    public static Context getAppContext() {
        return FetchApp.context;
    }
}

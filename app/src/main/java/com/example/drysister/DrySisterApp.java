package com.example.drysister;

import android.app.Application;
import android.content.Context;

public class DrySisterApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static DrySisterApp getContext() {
        return (DrySisterApp) context;
    }
}

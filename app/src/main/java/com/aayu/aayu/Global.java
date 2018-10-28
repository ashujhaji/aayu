package com.aayu.aayu;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

public class Global extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}

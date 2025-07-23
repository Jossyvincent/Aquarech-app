package com.aquarech.farmer.app;

import android.app.Application;

public class AquarechApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        new PreferenceManager(this);
    }
}

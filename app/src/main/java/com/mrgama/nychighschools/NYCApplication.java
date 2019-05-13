package com.mrgama.nychighschools;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.mrgama.nychighschools.di.AppComponent;
import com.mrgama.nychighschools.di.ContextModule;
import com.mrgama.nychighschools.di.DaggerAppComponent;
import com.mrgama.nychighschools.di.ServicesModule;

public class NYCApplication extends Application {
    private static AppComponent sAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        String endpoint = "https://data.cityofnewyork.us/resource/";

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .servicesModule(new ServicesModule(endpoint))
                .build();


    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @VisibleForTesting
    public static void setAppComponent(@NonNull AppComponent appComponent) {
        sAppComponent = appComponent;
    }
}

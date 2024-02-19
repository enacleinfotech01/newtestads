package com.securex.newtestads;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AudienceNetworkAds;
import com.google.firebase.FirebaseApp;


public class MyApplication extends Application {
    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        FirebaseApp.initializeApp(this);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Facebook SDK
        AudienceNetworkAds.initialize(this);

        // Applovin SDK
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
            }
        });

    }

}

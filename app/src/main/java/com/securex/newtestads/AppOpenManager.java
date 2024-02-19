package com.securex.newtestads;

import static androidx.lifecycle.Lifecycle.Event.ON_START;
import static com.securex.newtestads.AdsConfig.appOpenShowAd;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
//import androidx.lifecycle.ProcessLifecycleOwner;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAppOpenAd;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;


public class AppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    private static final String LOG_TAG = "AppOpenManager";
    private static boolean isShowingAd = false;
    private final MyApplication myApplication;
    private AppOpenAd appOpenAd = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private Activity currentActivity;
    private MaxAppOpenAd appLovinAppOpenAd;
    public AppOpenManager(MyApplication myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
//        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public void showAdIfAvailable() {
        if (!isShowingAd && isAdAvailable()) {
            Log.d(LOG_TAG, "Will show ad.");

            FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            AppOpenManager.this.appOpenAd = null;
                            isShowingAd = false;
                            fetchAd();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            isShowingAd = true;
                        }
                    };

            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAd.show(currentActivity);

        } else {
            Log.d(LOG_TAG, "Can not show ad.");
            fetchAd();
        }
    }

    public void fetchAd() {

        if (isAdAvailable()) {
            return;
        }

        loadCallback =
                new AppOpenAd.AppOpenAdLoadCallback() {

                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        appOpenAd = ad;
                    }


                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                    }

                };
        AdRequest request = getAdRequest();
        AppOpenAd.load(
                myApplication, new AdsConfig(myApplication.context).getGoogleAo(), request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }


    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }


    public boolean isAdAvailable() {
        return appOpenAd != null;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = activity;

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        currentActivity = null;
    }

    private void showAdIfReady()
    {

        if ( appLovinAppOpenAd != null && appLovinAppOpenAd.isReady() )
        {
            appLovinAppOpenAd.showAd( new AdsConfig(myApplication.context).getApplovinAo() );
        }
        else
        {
            fetchApplovinAd();
        }

    }

    public void fetchApplovinAd() {

        appLovinAppOpenAd = new MaxAppOpenAd(new AdsConfig(myApplication.context).getApplovinAo(), myApplication.context);
        appLovinAppOpenAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd maxAd) {
            }

            @Override
            public void onAdDisplayed(MaxAd maxAd) {

            }

            @Override
            public void onAdHidden(MaxAd maxAd) {
                appLovinAppOpenAd.loadAd();
            }

            @Override
            public void onAdClicked(MaxAd maxAd) {

            }

            @Override
            public void onAdLoadFailed(String s, MaxError maxError) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
                appLovinAppOpenAd.loadAd();
            }
        });
        appLovinAppOpenAd.loadAd();
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        if (new AdsConfig(myApplication.context).getAdsType() == 1) {
            showAdIfAvailable();
        } else if (new AdsConfig(myApplication.context).getAdsType() == 2) {
            showAdIfReady();
        } else if (new AdsConfig(myApplication.context).getAdsType() == 3){
            appOpenShowAd++;
            if (appOpenShowAd % new AdsConfig(myApplication.context).getAdChanger() == 0) {
                showAdIfAvailable();
            } else {
                showAdIfReady();
            }
        } else {
            showAdIfReady();
        }
        Log.d(LOG_TAG, "onStart");
    }

}
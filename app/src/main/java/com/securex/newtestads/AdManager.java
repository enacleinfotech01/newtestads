
package com.securex.newtestads;


import android.content.Context;

public class AdManager {}
//
//    private Context context;
//
//    private InterstitialAd interstitialAd;
//
//    public AdManager(Context context) {
//        this.context = context;
//
//        // Initialize ads
//        interstitialAd = new InterstitialAd(context);
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//        interstitialAd.loadAd(adRequest);
//
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//            }
//        });
//    }
//
//    public void showInterstitial() {
//        if (((InterstitialAd) interstitialAd).isLoaded()) {
//            interstitialAd.show();
//        }
//    }




//}

























//package com.securex.newtestads;
//
//import android.content.Context;
//
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.startapp.sdk.adsbase.StartAppAd;
//
//public class AdManager {
//
//    private Context context;
//
//    private InterstitialAd admobInterstitialAd;
//    private StartAppAd startAppAd;
//
//    private int adCounter = 0;
//
//    public AdManager(Context context) {
//        this.context = context;
//
//        admobInterstitialAd = createAdmobInterstitial();
//        startAppAd = createStartAppAd();
//    }
//
//    public void showNextAd() {
//
//        if(adCounter == 0) {
//            if (admobInterstitialAd.isLoaded()) {
//                admobInterstitialAd.show(context);
//            }
//        } else {
//            startAppAd.showAd();
//        }
//
//        adCounter++;
//        if(adCounter > 1) adCounter = 0;
//    }
//
//    private InterstitialAd createAdmobInterstitial() {
//        // Initialize Admob Interstitial
//        return new InterstitialAd(context);
//    }
//
//    private StartAppAd createStartAppAd() {
//        // Initialize StartApp
//        return new StartAppAd(context);
//    }
//}//package com.securex.newtestads;
////
////import android.content.Context;
////
////import com.google.android.gms.ads.interstitial.InterstitialAd;
////import com.startapp.sdk.adsbase.StartAppAd;
////
////public class AdManager {
////
////    private Context context;
////
////    private InterstitialAd admobInterstitialAd;
////    private StartAppAd startAppAd;
////
////    private int adCounter = 0;
////
////    public AdManager(Context context) {
////        this.context = context;
////
////        admobInterstitialAd = createAdmobInterstitial();
////        startAppAd = createStartAppAd();
////    }
////
////    public void showNextAd() {
////
////        if(adCounter == 0) {
////            if (admobInterstitialAd.isLoaded()) {
////                admobInterstitialAd.show(context);
////            }
////        } else {
////            startAppAd.showAd();
////        }
////
////        adCounter++;
////        if(adCounter > 1) adCounter = 0;
////    }
////
////    private InterstitialAd createAdmobInterstitial() {
////        // Initialize Admob Interstitial
////        return new InterstitialAd(context);
////    }
////
////    private StartAppAd createStartAppAd() {
////        // Initialize StartApp
////        return new StartAppAd(context);
////    }
////}
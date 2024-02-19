package com.securex.newtestads;



//import com.securex.newtestads.AdsConfig.interstitialShowAds;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class LoadedAds {

    private static final String TAG = "@@@";
    private static LoadedAds mInstance;
    private com.google.android.gms.ads.interstitial.InterstitialAd interstitial;
    AdRequest adRequest;
    public static int adIndex = 1;
    public static int interstitialShowAds = 1;

    public static int adIndexNative = 1;
    AdLoadingDialog dialog;

    public static LoadedAds getInstance() {
        if (mInstance == null) {
            mInstance = new LoadedAds();
        }
        return mInstance;
    }


    public void displayAds(Activity activity, final AdService.MyCallback myCallback) {
        adIndex++;

        if (adIndex % new AdsConfig(activity).getCounter() == 0) {
            dialog = new AdLoadingDialog(activity);

            if (new AdsConfig(activity).getAdsType() == 1) {
                if (interstitial == null) {
                    adRequest = new AdRequest.Builder().build();
                    com.google.android.gms.ads.interstitial.InterstitialAd.load(activity, new AdsConfig(activity).getGoogleI(), adRequest, new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {

                            Log.e("@@@@@", "onAdLoaded: " + interstitialAd.getAdUnitId());

                            if (dialog.isShowing())
                                dialog.dismiss();
                            interstitialAd.show(activity);
                            interstitial = interstitialAd;
                            interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                    Log.e(TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                                    interstitial = null;

                                    myCallback.callbackCall();
                                    adRequest = new AdRequest.Builder().build();
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    super.onAdShowedFullScreenContent();
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    interstitial = null;

                                    myCallback.callbackCall();
                                    adRequest = new AdRequest.Builder().build();
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            interstitial = null;
                            adRequest = new AdRequest.Builder().build();
                            if (dialog.isShowing())
                                dialog.dismiss();
                            Log.e(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());

                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }
                    });
                } else {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    myCallback.callbackCall();
                }
            } // Google
            else if (new AdsConfig(activity).getAdsType() == 2) {


                final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                maxInterstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {

                        if (dialog.isShowing())
                            dialog.dismiss();
                        maxInterstitialAd.showAd();

                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {


                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                        Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }
                });
                maxInterstitialAd.loadAd();


            } // AppLovin
            else if (new AdsConfig(activity).getAdsType() == 3) {
                interstitialShowAds++;
                if (interstitialShowAds % new AdsConfig(activity).getAdChanger() == 0) {
                    if (interstitial == null) {
                        adRequest = new AdRequest.Builder().build();
                        com.google.android.gms.ads.interstitial.InterstitialAd.load(activity, new AdsConfig(activity).getGoogleI(), adRequest, new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                interstitialAd.show(activity);

                                interstitial = interstitialAd;
                                interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                                    @Override
                                    public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                        Log.e(TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                                        interstitial = null;

                                        myCallback.callbackCall();
                                        adRequest = new AdRequest.Builder().build();
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        super.onAdShowedFullScreenContent();
                                    }

                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        super.onAdDismissedFullScreenContent();
                                        interstitial = null;

                                        myCallback.callbackCall();
                                        adRequest = new AdRequest.Builder().build();
                                    }
                                });
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                interstitial = null;
                                adRequest = new AdRequest.Builder().build();

                                Log.e(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                if (myCallback != null) {
                                    myCallback.callbackCall();
                                }
                            }
                        });
                    } else {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        myCallback.callbackCall();
                    }
                } else {
//                    if (fbInterstitialAd == null) {
//                        fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
//                        InterstitialAdListener madlistner = new InterstitialAdListener() {
//                            @Override
//                            public void onInterstitialDisplayed(Ad ad) {
//                            }
//
//                            @Override
//                            public void onInterstitialDismissed(Ad ad) {
//                                if (dialog.isShowing())
//                                    dialog.dismiss();
//                                if (myCallback != null) {
//                                    fbInterstitialAd = null;
//                                    myCallback.callbackCall();
//                                }
//                            }
//
//                            @Override
//                            public void onError(Ad ad, AdError adError) {
//                                if (dialog.isShowing())
//                                    dialog.dismiss();
//                                Log.e("@@@", "onError: " + adError.getErrorMessage());
//                                if (myCallback != null) {
//                                    fbInterstitialAd = null;
//                                    myCallback.callbackCall();
//                                }
//                            }
//
//                            @Override
//                            public void onAdLoaded(Ad ad) {
//                                fbInterstitialAd.show();
//                            }
//
//                            @Override
//                            public void onAdClicked(Ad ad) {
//                            }
//
//                            @Override
//                            public void onLoggingImpression(Ad ad) {
//                            }
//                        };
//                        fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());
//                    } else {
//                        if (dialog.isShowing())
//                            dialog.dismiss();
//                        myCallback.callbackCall();
//                    }

                    final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                    maxInterstitialAd.setListener(new MaxAdListener() {
                        @Override
                        public void onAdLoaded(MaxAd ad) {

                            if (maxInterstitialAd.isReady()) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                maxInterstitialAd.showAd();
                            } else {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                if (myCallback != null) {
                                    myCallback.callbackCall();
                                }
                            }
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {


                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                            Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }
                    });
                    maxInterstitialAd.loadAd();
                }

            } // Google and Applovin
            else if (new AdsConfig(activity).getAdsType() == 4) {
                interstitialShowAds++;
                if (interstitialShowAds % new AdsConfig(activity).getAdChanger() == 0) {
                    InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
                    InterstitialAdListener madlistner = new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            Log.e("@@@", "onError: " + adError.getErrorMessage());
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            fbInterstitialAd.show();
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }
                    };
                    fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());

                } else {
                    final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                    maxInterstitialAd.setListener(new MaxAdListener() {
                        @Override
                        public void onAdLoaded(MaxAd ad) {

                            if (maxInterstitialAd.isReady()) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                maxInterstitialAd.showAd();
                            } else {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                if (myCallback != null) {
                                    myCallback.callbackCall();
                                }
                            }
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {


                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                            Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }
                    });
                    maxInterstitialAd.loadAd();
                }
            } // Facebook and Applovin
            else if (new AdsConfig(activity).getAdsType() == 5) {
                InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
                InterstitialAdListener madlistner = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {

                        final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                        maxInterstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                maxInterstitialAd.showAd();
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                if (myCallback != null) {
                                    myCallback.callbackCall();
                                }

                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                if (myCallback != null) {
                                    myCallback.callbackCall();
                                }
                                Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                if (myCallback != null) {
                                    myCallback.callbackCall();
                                }

                            }
                        });
                        maxInterstitialAd.loadAd();

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        fbInterstitialAd.show();
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());
            } // Facebook faild Applovin
            else if (new AdsConfig(activity).getAdsType() == 6) {
                interstitialShowAds++;
                if (interstitialShowAds % new AdsConfig(activity).getAdChanger() == 0) {
                    InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
                    InterstitialAdListener madlistner = new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            Log.e("@@@", "onError: " + adError.getErrorMessage());
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            fbInterstitialAd.show();
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }
                    };
                    fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());

                } else if (interstitialShowAds % 2 == 0) {
                    InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
                    InterstitialAdListener madlistner = new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            Log.e("@@@", "onError: " + adError.getErrorMessage());
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            fbInterstitialAd.show();
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }
                    };
                    fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());
                } else {
                    final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                    maxInterstitialAd.setListener(new MaxAdListener() {
                        @Override
                        public void onAdLoaded(MaxAd ad) {

                            if (maxInterstitialAd.isReady()) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                maxInterstitialAd.showAd();
                            } else {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                if (myCallback != null) {
                                    myCallback.callbackCall();
                                }
                            }
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {


                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                            Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }
                    });
                    maxInterstitialAd.loadAd();
                }
            } // Google and Facebook and Applovin
            else {
                if (myCallback != null) {
                    myCallback.callbackCall();
                }
                dialog.dismiss();
            }

        } else {
            myCallback.callbackCall();
        }
    }

    public void displayAdsWithoutCounter(Activity activity, final AdService.MyCallback myCallback) {

        dialog = new AdLoadingDialog(activity);

        if (new AdsConfig(activity).getAdsType() == 1) {
            if (interstitial == null) {
                adRequest = new AdRequest.Builder().build();
                com.google.android.gms.ads.interstitial.InterstitialAd.load(activity, new AdsConfig(activity).getGoogleI(), adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {

                        Log.e("@@@@@", "onAdLoaded: " + interstitialAd.getAdUnitId());

                        if (dialog.isShowing())
                            dialog.dismiss();
                        interstitialAd.show(activity);
                        interstitial = interstitialAd;
                        interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                Log.e(TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                                interstitial = null;

                                myCallback.callbackCall();
                                adRequest = new AdRequest.Builder().build();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                interstitial = null;

                                myCallback.callbackCall();
                                adRequest = new AdRequest.Builder().build();
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        interstitial = null;
                        adRequest = new AdRequest.Builder().build();
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Log.e(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());

                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }
                });
            } else {
                if (dialog.isShowing())
                    dialog.dismiss();
                myCallback.callbackCall();
            }
        } // Google
        else if (new AdsConfig(activity).getAdsType() == 2) {


            final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
            maxInterstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {

                    if (dialog.isShowing())
                        dialog.dismiss();
                    maxInterstitialAd.showAd();

                }

                @Override
                public void onAdDisplayed(MaxAd ad) {


                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if (myCallback != null) {
                        myCallback.callbackCall();
                    }

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if (myCallback != null) {
                        myCallback.callbackCall();
                    }
                    Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if (myCallback != null) {
                        myCallback.callbackCall();
                    }

                }
            });
            maxInterstitialAd.loadAd();


        } // Applovin
        else if (new AdsConfig(activity).getAdsType() == 3) {
            interstitialShowAds++;
            if (interstitialShowAds % new AdsConfig(activity).getAdsChangeCounter() == 0) {

                Log.e("AdsChangeCounter", "adaptivebanner:  abmob" );

                if (interstitial == null) {
                    adRequest = new AdRequest.Builder().build();
                    com.google.android.gms.ads.interstitial.InterstitialAd.load(activity, new AdsConfig(activity).getGoogleI(), adRequest, new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            interstitialAd.show(activity);

                            interstitial = interstitialAd;
                            interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                    Log.e(TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                                    interstitial = null;

                                    myCallback.callbackCall();
                                    adRequest = new AdRequest.Builder().build();
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    super.onAdShowedFullScreenContent();
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    interstitial = null;

                                    myCallback.callbackCall();
                                    adRequest = new AdRequest.Builder().build();
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            interstitial = null;
                            adRequest = new AdRequest.Builder().build();

                            Log.e(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }
                    });
                } else {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    myCallback.callbackCall();
                }
            } else {

                Log.e("AdsChangeCounter", "adaptivebanner:  applovin" );

//                    if (fbInterstitialAd == null) {
//                        fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
//                        InterstitialAdListener madlistner = new InterstitialAdListener() {
//                            @Override
//                            public void onInterstitialDisplayed(Ad ad) {
//                            }
//
//                            @Override
//                            public void onInterstitialDismissed(Ad ad) {
//                                if (dialog.isShowing())
//                                    dialog.dismiss();
//                                if (myCallback != null) {
//                                    fbInterstitialAd = null;
//                                    myCallback.callbackCall();
//                                }
//                            }
//
//                            @Override
//                            public void onError(Ad ad, AdError adError) {
//                                if (dialog.isShowing())
//                                    dialog.dismiss();
//                                Log.e("@@@", "onError: " + adError.getErrorMessage());
//                                if (myCallback != null) {
//                                    fbInterstitialAd = null;
//                                    myCallback.callbackCall();
//                                }
//                            }
//
//                            @Override
//                            public void onAdLoaded(Ad ad) {
//                                fbInterstitialAd.show();
//                            }
//
//                            @Override
//                            public void onAdClicked(Ad ad) {
//                            }
//
//                            @Override
//                            public void onLoggingImpression(Ad ad) {
//                            }
//                        };
//                        fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());
//                    } else {
//                        if (dialog.isShowing())
//                            dialog.dismiss();
//                        myCallback.callbackCall();
//                    }

                final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                maxInterstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {

                        if (maxInterstitialAd.isReady()) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            maxInterstitialAd.showAd();
                        } else {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {


                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                        Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }
                });
                maxInterstitialAd.loadAd();
            }

        } // Google and Applovin
        else if (new AdsConfig(activity).getAdsType() == 4) {
            interstitialShowAds++;
            if (interstitialShowAds % new AdsConfig(activity).getAdChanger() == 0) {
                InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
                InterstitialAdListener madlistner = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Log.e("@@@", "onError: " + adError.getErrorMessage());
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        fbInterstitialAd.show();
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());

            } else {
                final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                maxInterstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {

                        if (maxInterstitialAd.isReady()) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            maxInterstitialAd.showAd();
                        } else {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {


                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                        Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }
                });
                maxInterstitialAd.loadAd();
            }
        } // Facebook and Applovin
        else if (new AdsConfig(activity).getAdsType() == 5) {
            InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
            InterstitialAdListener madlistner = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if (myCallback != null) {
                        myCallback.callbackCall();
                    }
                }

                @Override
                public void onError(Ad ad, AdError adError) {

                    final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                    maxInterstitialAd.setListener(new MaxAdListener() {
                        @Override
                        public void onAdLoaded(MaxAd ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            maxInterstitialAd.showAd();
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                            Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }

                        }
                    });
                    maxInterstitialAd.loadAd();

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    fbInterstitialAd.show();
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            };
            fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());
        } // Facebook fail Applovin
        else if (new AdsConfig(activity).getAdsType() == 6) {
            interstitialShowAds++;
            if (interstitialShowAds % new AdsConfig(activity).getAdChanger() == 0) {
                InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
                InterstitialAdListener madlistner = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Log.e("@@@", "onError: " + adError.getErrorMessage());
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        fbInterstitialAd.show();
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());

            } else if (interstitialShowAds % 2 == 0) {
                InterstitialAd fbInterstitialAd = new InterstitialAd(activity, new AdsConfig(activity).getFbInter());
                InterstitialAdListener madlistner = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Log.e("@@@", "onError: " + adError.getErrorMessage());
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        fbInterstitialAd.show();
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                fbInterstitialAd.loadAd(fbInterstitialAd.buildLoadAdConfig().withAdListener(madlistner).build());
            } else {
                final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(new AdsConfig(activity).getApplovinI(), activity);
                maxInterstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {

                        if (maxInterstitialAd.isReady()) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            maxInterstitialAd.showAd();
                        } else {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            if (myCallback != null) {
                                myCallback.callbackCall();
                            }
                        }
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {


                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }
                        Log.e("applovin", "onNativeAdLoadFailed: interstitial=>" + error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (myCallback != null) {
                            myCallback.callbackCall();
                        }

                    }
                });
                maxInterstitialAd.loadAd();
            }
        } // Google and Facebook and Applovin
        else {
            if (myCallback != null) {
                myCallback.callbackCall();
            }
        }
    }
}

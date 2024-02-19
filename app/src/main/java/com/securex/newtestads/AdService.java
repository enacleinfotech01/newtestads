package com.securex.newtestads;

import static com.securex.newtestads.AdsConfig.bannerShowAd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.Arrays;

//import livevideocall.randomchat.call.chat.newcall.newgirlvideocall.R;

public class AdService {

    private static AdService mInstance;
    private static AdView adView;


    public static AdService getInstance() {
        if (mInstance == null) {
            mInstance = new AdService();
        }
        return mInstance;
    }

    public interface MyCallback {
        void callbackCall();
    }

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void adaptivebanner(Activity activity, final FrameLayout frameLayout) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.shimmer_layout_native_main, (ViewGroup) null);
        FrameLayout shimer = (FrameLayout) inflate;
        ShimmerFrameLayout shimmerFrameLayout = inflate.findViewById(R.id.banner_shimer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        frameLayout.addView(shimer);
        MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345")).build());


        if (new AdsConfig(activity).getAdsType() == 1) {
            adView = new AdView(activity);
            adView.setAdUnitId(new AdsConfig(activity).getGoogleB());
            AdSize adSize = getAdSize(activity, frameLayout);
            adView.setAdSize(adSize);
            frameLayout.setVisibility(View.VISIBLE);

            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);

            adView.setAdListener(new AdListener() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    frameLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView);
                    frameLayout.setVisibility(View.VISIBLE);
                }

            });
        } // Google
        else if (new AdsConfig(activity).getAdsType() == 2) {

            MaxAdView adView = new MaxAdView(new AdsConfig(activity).getApplovinB(), activity);
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    frameLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {


                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    frameLayout.setVisibility(View.GONE);
                    Log.e("applovin", "onNativeAdLoadFailed: banner=>" + error.getMessage());
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
            final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
            adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
            frameLayout.addView(adView);
            adView.loadAd();

        } // AppLovin
        else if (new AdsConfig(activity).getAdsType() == 3) {
            bannerShowAd++;
            if (bannerShowAd % new AdsConfig(activity).getAdChanger() == 0) {
                adView = new AdView(activity);
                adView.setAdUnitId(new AdsConfig(activity).getGoogleB());
                AdSize adSize = getAdSize(activity, frameLayout);
                adView.setAdSize(adSize);
                frameLayout.setVisibility(View.VISIBLE);
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);

                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                });
            } else {

                MaxAdView adView = new MaxAdView(new AdsConfig(activity).getApplovinB(), activity);
                adView.setListener(new MaxAdViewAdListener() {
                    @Override
                    public void onAdExpanded(MaxAd ad) {

                    }

                    @Override
                    public void onAdCollapsed(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {


                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        frameLayout.setVisibility(View.GONE);
                        Log.e("applovin", "onNativeAdLoadFailed: banner=>" + error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                });
                final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                frameLayout.addView(adView);
                adView.loadAd();

            }
        } // Google and Applovin
        else if (new AdsConfig(activity).getAdsType() == 4) {
            bannerShowAd++;
            if (bannerShowAd % new AdsConfig(activity).getAdChanger() == 0) {
                com.facebook.ads.AdView adView = new com.facebook.ads.AdView(activity, new AdsConfig(activity).getFbBanner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        Log.e("@@@", "onError: " + adError.getErrorMessage());
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                }).build());
            } else {
                MaxAdView adView = new MaxAdView(new AdsConfig(activity).getApplovinB(), activity);
                adView.setListener(new MaxAdViewAdListener() {
                    @Override
                    public void onAdExpanded(MaxAd ad) {

                    }

                    @Override
                    public void onAdCollapsed(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {


                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        frameLayout.setVisibility(View.GONE);
                        Log.e("applovin", "onNativeAdLoadFailed: banner=>" + error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                });
                final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                frameLayout.addView(adView);
                adView.loadAd();
            }
        } // Facebook and Applovin
        else if (new AdsConfig(activity).getAdsType() == 5) {
            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(activity, new AdsConfig(activity).getFbBanner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.e("@@@", "onError: " + adError.getErrorMessage());

                    MaxAdView adView = new MaxAdView(new AdsConfig(activity).getApplovinB(), activity);
                    adView.setListener(new MaxAdViewAdListener() {
                        @Override
                        public void onAdExpanded(MaxAd ad) {

                        }

                        @Override
                        public void onAdCollapsed(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {
                            frameLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {


                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            frameLayout.setVisibility(View.GONE);
                            Log.e("applovin", "onNativeAdLoadFailed: banner=>" + error.getMessage());
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    });
                    final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                    final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                    adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                    frameLayout.addView(adView);
                    adView.loadAd();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView);
                    frameLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            }).build());
        } // Facebook failed Applovin
        else if (new AdsConfig(activity).getAdsType() == 6) {
            bannerShowAd++;
            if (bannerShowAd % new AdsConfig(activity).getAdChanger() == 0) {
                adView = new AdView(activity);
                adView.setAdUnitId(new AdsConfig(activity).getGoogleB());
                AdSize adSize = getAdSize(activity, frameLayout);
                adView.setAdSize(adSize);
                frameLayout.setVisibility(View.VISIBLE);

                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);

                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                });
            } else if (bannerShowAd % 2 == 0) {
                com.facebook.ads.AdView adView = new com.facebook.ads.AdView(activity, new AdsConfig(activity).getFbBanner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        Log.e("@@@", "onError: " + adError.getErrorMessage());
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                }).build());
            } else {
                MaxAdView adView = new MaxAdView(new AdsConfig(activity).getApplovinB(), activity);
                adView.setListener(new MaxAdViewAdListener() {
                    @Override
                    public void onAdExpanded(MaxAd ad) {

                    }

                    @Override
                    public void onAdCollapsed(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {


                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        frameLayout.setVisibility(View.GONE);
                        Log.e("applovin", "onNativeAdLoadFailed: banner=>" + error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                });
                final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                frameLayout.addView(adView);
                adView.loadAd();
            }
        } // Google and Facebook and Applovin
        else {
            hideLayout(frameLayout);
        }
    }

    private AdSize getAdSize(Activity activity, FrameLayout linearLayout) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float density = outMetrics.density;
        float adWidthPixels = linearLayout.getWidth();
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }
        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    private void hideLayout(FrameLayout frameLayout) {
        if (frameLayout != null) {
            frameLayout.setVisibility(View.GONE);
            frameLayout.removeAllViews();
        }
    }

    public void shareApp(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", R.string.app_name);
            intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\n" + "https://play.google.com/store/apps/details?id=" + context.getPackageName() + "\n\n");
            context.startActivity(Intent.createChooser(intent, "choose one"));
        } catch (Exception unused) {
            unused.printStackTrace();
        }
    }

    public void rateUs(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public void privacyPolicy(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://miningzoneinfotech.blogspot.com/2023/06/privacy-policy-mining-zone-infotech.html"));
        context.startActivity(intent);
    }

}

package com.securex.newtestads;


import static com.securex.newtestads.AdsConfig.nativeShowAd;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.List;

public class NativeManager {
    public static NativeManager mInstance;
    private int nativeFail = 0;


    public static NativeManager getInstance() {
        if (mInstance == null) {
            mInstance = new NativeManager();
        }
        return mInstance;
    }

    public void refreshAd(final Activity activity, final FrameLayout frameLayout) {

        View inflate = activity.getLayoutInflater().inflate(R.layout.shimmer_layout_native_main, (ViewGroup) null);
        FrameLayout shimer = (FrameLayout) inflate;
        ShimmerFrameLayout shimmerFrameLayout = inflate.findViewById(R.id.native_shimer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        frameLayout.addView(shimer);

        if (new AdsConfig(activity).getAdsType() == 1) {
            AdLoader.Builder builder = new AdLoader.Builder(activity, new AdsConfig(activity).getGoogleN());
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    NativeAdView adView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.mob_native, null);
                    populateUnifiedNativeAdView(nativeAd, adView);
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView);
                    frameLayout.setVisibility(View.VISIBLE);
                }
            });

            AdLoader adLoader = builder.withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    failAdMobNativeAds(activity, frameLayout);
                }
            }).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } // Google
        else if (new AdsConfig(activity).getAdsType() == 2) {

            MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader(new AdsConfig(activity).getApplovinN(), activity);
            nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                @Override
                public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                    frameLayout.removeAllViews();
                    frameLayout.addView(nativeAdView);
                    frameLayout.setVisibility(View.VISIBLE);

                }

                @Override
                public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                    frameLayout.setVisibility(View.GONE);
                }

                @Override
                public void onNativeAdClicked(final MaxAd ad) {

                }
            });

            nativeAdLoader.loadAd();
        } // Applovin
        else if (new AdsConfig(activity).getAdsType() == 3) {
            nativeShowAd++;
            if (nativeShowAd % new AdsConfig(activity).getAdChanger() == 0) {
                AdLoader.Builder builder = new AdLoader.Builder(activity, new AdsConfig(activity).getGoogleN());
                builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.mob_native, null);
                        populateUnifiedNativeAdView(nativeAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                        frameLayout.setVisibility(View.VISIBLE);
                    }
                });

                AdLoader adLoader = builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        failAdMobNativeAds(activity, frameLayout);
                    }
                }).build();
                adLoader.loadAd(new AdRequest.Builder().build());
            } else {

//                NativeAd nativeAd = new NativeAd(activity, new AdsConfig(activity).getFbNative());
//                NativeAdListener nativeAdListener = new NativeAdListener() {
//
//                    @Override
//                    public void onMediaDownloaded(Ad ad) {
//                    }
//
//                    @Override
//                    public void onError(Ad ad, AdError adError) {
//                        failFBNativeAds(activity, frameLayout);
//                    }
//
//                    @Override
//                    public void onAdLoaded(Ad ad) {
//                        if (frameLayout.getVisibility() == View.GONE) {
//                            frameLayout.setVisibility(View.VISIBLE);
//                        }
//                        frameLayout.removeAllViews();
//                        if (nativeAd == null || nativeAd != ad) {
//                            return;
//                        }
//                        FBnative(nativeAd, frameLayout, activity);
//                    }
//
//                    @Override
//                    public void onAdClicked(Ad ad) {
//                    }
//
//                    @Override
//                    public void onLoggingImpression(Ad ad) {
//                    }
//                };
//                nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());

                MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader(new AdsConfig(activity).getApplovinN(), activity);
                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                    @Override
                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                        frameLayout.removeAllViews();
                        frameLayout.addView(nativeAdView);
                        frameLayout.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNativeAdClicked(final MaxAd ad) {

                    }
                });

                nativeAdLoader.loadAd();

            }
        } // Google and Applovin
        else if (new AdsConfig(activity).getAdsType() == 4) {
            nativeShowAd++;
            if (nativeShowAd % new AdsConfig(activity).getAdChanger() == 0) {
                NativeAd nativeAd = new NativeAd(activity, new AdsConfig(activity).getFbNative());
                NativeAdListener nativeAdListener = new NativeAdListener() {

                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        frameLayout.removeAllViews();
                        FBnative(nativeAd, frameLayout, activity);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
            } else {
                MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader(new AdsConfig(activity).getApplovinN(), activity);
                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                    @Override
                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                        frameLayout.removeAllViews();
                        frameLayout.addView(nativeAdView);
                        frameLayout.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNativeAdClicked(final MaxAd ad) {

                    }
                });

                nativeAdLoader.loadAd();
            }
        } // Facebook and Applovin
        else if (new AdsConfig(activity).getAdsType() == 5) {
            NativeAd nativeAd = new NativeAd(activity, new AdsConfig(activity).getFbNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {

                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader(new AdsConfig(activity).getApplovinN(), activity);
                    nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                        @Override
                        public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                            frameLayout.removeAllViews();
                            frameLayout.addView(nativeAdView);
                            frameLayout.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                            frameLayout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNativeAdClicked(final MaxAd ad) {

                        }
                    });

                    nativeAdLoader.loadAd();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    frameLayout.removeAllViews();
                    FBnative(nativeAd, frameLayout, activity);
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            };
            nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        } // Facebook failed Applovin
        else if (new AdsConfig(activity).getAdsType() == 6) {
            nativeShowAd++;
            if (nativeShowAd % new AdsConfig(activity).getAdChanger() == 0) {
                AdLoader.Builder builder = new AdLoader.Builder(activity, new AdsConfig(activity).getGoogleN());
                builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.mob_native, null);
                        populateUnifiedNativeAdView(nativeAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                        frameLayout.setVisibility(View.VISIBLE);
                    }
                });

                AdLoader adLoader = builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        failAdMobNativeAds(activity, frameLayout);
                    }
                }).build();
                adLoader.loadAd(new AdRequest.Builder().build());
            } else if (nativeShowAd % 2 == 0) {
                NativeAd nativeAd = new NativeAd(activity, new AdsConfig(activity).getFbNative());
                NativeAdListener nativeAdListener = new NativeAdListener() {

                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        frameLayout.removeAllViews();
                        FBnative(nativeAd, frameLayout, activity);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
            } else {
                MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader(new AdsConfig(activity).getApplovinN(), activity);
                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                    @Override
                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                        frameLayout.removeAllViews();
                        frameLayout.addView(nativeAdView);
                        frameLayout.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNativeAdClicked(final MaxAd ad) {

                    }
                });

                nativeAdLoader.loadAd();
            }
        } // Google and Facebook and Applovin
        else {
            hideLayout(frameLayout);
        }
    }

    private void FBnative(NativeAd nativeAd, FrameLayout frameLayout, Activity activity) {
        nativeAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.fb_native, null, false);

        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);

        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);
        LinearLayout click = adView.findViewById(R.id.ad_unit);

        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
        nativeAdCallToAction.setVisibility(View.VISIBLE);

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(click);

        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);
        frameLayout.addView(adView);
        adChoicesContainer.removeAllViews();
    }


    private void hideLayout(FrameLayout frameLayout) {
        if (frameLayout != null) {
            frameLayout.setVisibility(View.GONE);
            frameLayout.removeAllViews();
        }
    }

    private void failAdMobNativeAds(Activity activity, FrameLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, new AdsConfig(activity).getGoogleN());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.mob_native, null);
                populateUnifiedNativeAdView(nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
                frameLayout.setVisibility(View.VISIBLE);
            }
        });

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Handler myHandler = new Handler();
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (nativeFail < 3) {
                            nativeFail++;
                            Log.e("failBanner", "run: recall");
                            failAdMobNativeAds(activity, frameLayout);
                        } else {
                            nativeFail = 0;
                            Log.e("failBanner", "run: end Of Banner");
                            myHandler.removeCallbacks(this);
                            frameLayout.setVisibility(View.GONE);
                        }
                    }
                };
                myHandler.postDelayed(myRunnable, 1000);
            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void populateUnifiedNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView(adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            adView.getIconView().setVisibility(View.VISIBLE);
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
        }


        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);

        VideoController vc = nativeAd.getMediaContent().getVideoController();

        if (vc.hasVideoContent()) {
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
    }
}

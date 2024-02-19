package com.securex.newtestads;

import android.content.Context;
import android.content.SharedPreferences;

public class AdsConfig {

    public static int interstitialShowAds = 1;
    public static int bannerShowAd = 1;
    public static int nativeShowAd = 1;
    public static int appOpenShowAd = 1;
    public static AppOpenManager appOpenManager;

    private final String GOOGLE_AO = "GOOGLE_AO";
    private final String GOOGLE_B = "GOOGLE_B";
    private final String GOOGLE_N = "GOOGLE_N";
    private final String GOOGLE_I = "GOOGLE_I";
    private final String Counter = "Counter";
    private final String FB_BANNER = "FB_BANNER";
    private final String FB_INTER = "FB_INTER";
    private final String FB_NATIVE = "FB_NATIVE";
    private final String FB_SMBANNER = "FB_SMBANNER";
    private final String NativeCounter = "NativeCounter";
    private final String AdsType = "AdsType";
    private final String PrivacyPolicy = "PrivacyPolicy";
    private final String corotineTime = "corotineTime";
    private final String GOOGLE_R = "_GOOGLE_R";
    private final String RATE_US = "RATE_US";
    private static String AD_CHANGER = "AD_CHANGER";
    private String FB_REWARDED = "FB_REWARDED";
    private final String APPLOVIN_AO = "APPLOVIN_AO";
    private final String APPLOVIN_I = "APPLOVIN_I";
    private final String APPLOVIN_B = "APPLOVIN_B";
    private final String APPLOVIN_N = "APPLOVIN_N";
    private final String APPLOVIN_R = "APPLOVIN_R";
    private static String IS_REVIEW = "IS_REVIEW";
    private static String ADS_CHANGE_COUNTER = "ADS_CHANGE_COUNTER";

    private static AdsConfig mInstance;
    SharedPreferences preferences;
    private SharedPreferences.Editor writer;
    Context context;

    public AdsConfig(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("AdsConfig", Context.MODE_PRIVATE);
        writer = preferences.edit();
    }

    public void setRateUs(boolean isRate) {
        writer.putBoolean(RATE_US, isRate);
        writer.commit();
    }

    public void setAdChanger(int val) {
        writer.putInt(AD_CHANGER, val);
        writer.commit();
    }

    public void setFbRewarded(String adID) {
        writer.putString(FB_REWARDED, adID);
        writer.commit();
    }

    public String getFbRewarded() {
        return preferences.getString(FB_REWARDED, "000");
    }

    public int getAdChanger() {
        return preferences.getInt(AD_CHANGER, 2);
    }

    public boolean isRate() {
        return preferences.getBoolean(RATE_US, false);
    }

    public void setPrivacyPolicy(String val) {
        writer.putString(PrivacyPolicy, val);
        writer.commit();
    }

    public String getApplovinAo() {
        return preferences.getString(APPLOVIN_AO, "000");
    }

    public void setApplovinAo(String s) {
        writer.putString(APPLOVIN_AO, s);
        writer.commit();
    }

    public String getApplovinI() {
        return preferences.getString(APPLOVIN_I, "000");
    }

    public void setApplovinI(String s) {
        writer.putString(APPLOVIN_I, s);
        writer.commit();
    }

    public String getApplovinN() {
        return preferences.getString(APPLOVIN_N, "000");
    }

    public void setApplovinN(String s) {
        writer.putString(APPLOVIN_N, s);
        writer.commit();
    }

    public String getApplovinB() {
        return preferences.getString(APPLOVIN_B, "000");
    }

    public void setApplovinB(String s) {
        writer.putString(APPLOVIN_B, s);
        writer.commit();
    }

    public String getApplovinR() {
        return preferences.getString(APPLOVIN_R, "000");
    }

    public void setApplovinR(String s) {
        writer.putString(APPLOVIN_R, s);
        writer.commit();
    }

    public void setReview(boolean val) {
        writer.putBoolean(IS_REVIEW, val).commit();
    }

    public boolean isReview() {
        return preferences.getBoolean(IS_REVIEW, false);
    }


    public String getGoogleR() {
        return preferences.getString(GOOGLE_R, "000");
    }

    public void setGoogleR(String s) {
        writer.putString(GOOGLE_R, s);
        writer.commit();
    }

    public String getPrivacyPolicy() {
        return preferences.getString(PrivacyPolicy, "");
    }

    public void setFbSmbanner(String val) {
        writer.putString(FB_SMBANNER, val);
        writer.commit();
    }

    public String getFbSmbanner() {
        return preferences.getString(FB_SMBANNER, "000");
    }

    public void setFbNative(String val) {
        writer.putString(FB_NATIVE, val);
        writer.commit();
    }

    public String getFbNative() {
        return preferences.getString(FB_NATIVE, "000");
    }

    public void setFbInter(String val) {
        writer.putString(FB_INTER, val);
        writer.commit();
    }

    public String getFbInter() {
        return preferences.getString(FB_INTER, "000");
    }

    public void setFbBanner(String val) {
        writer.putString(FB_BANNER, val);
        writer.commit();
    }

    public String getFbBanner() {
        return preferences.getString(FB_BANNER, "000");
    }

    public void setGoogleAo(String val) {
        writer.putString(GOOGLE_AO, val);
        writer.commit();
    }

    public String getGoogleAo() {
        return preferences.getString(GOOGLE_AO, "000");
    }

    public void setGoogleB(String val) {
        writer.putString(GOOGLE_B, val);
        writer.commit();
    }

    public String getGoogleB() {
        return preferences.getString(GOOGLE_B, "000");
    }

    public void setGoogleN(String val) {
        writer.putString(GOOGLE_N, val);
        writer.commit();
    }

    public String getGoogleN() {
        return preferences.getString(GOOGLE_N, "000");
    }

    public void setGoogleI(String val) {
        writer.putString(GOOGLE_I, val);
        writer.commit();
    }

    public String getGoogleI() {
        return preferences.getString(GOOGLE_I, "000");
    }

    public void setCounter(int val) {
        writer.putInt(Counter, val);
        writer.commit();
    }

    public int getCounter() {
        return preferences.getInt(Counter, 2);
    }

    public void setNativeCounter(int val) {
        writer.putInt(NativeCounter, val);
        writer.commit();
    }


    public int getNativeCounter() {
        return preferences.getInt(NativeCounter, 2);
    }

    public void setAdsChangeCounter(int val) {
        writer.putInt(ADS_CHANGE_COUNTER, val);
        writer.commit();
    }


    public int getAdsChangeCounter() {
        return preferences.getInt(ADS_CHANGE_COUNTER, 2);
    }

    public void setAdsType(int val) {
        writer.putInt(AdsType, val);
        writer.commit();
    }

    public int getAdsType() {
        return preferences.getInt(AdsType, 2);
    }

    public void setCorotineTime(int val) {
        writer.putInt(corotineTime, val);
        writer.commit();
    }

    public int getCorotineTime() {
        return preferences.getInt(corotineTime, 2);
    }
}

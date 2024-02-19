package com.securex.newtestads;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Button fullad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullad = findViewById(R.id.fullad);

        // Banner Ad
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Interstitial ads
        AdRequest adRequest1 = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Log.d("TAG", adError != null ? adError.toString() : "Ad failed to load");
                mInterstitialAd = null;
            }

            @Override
            public void onAdLoaded(InterstitialAd interstitialAd) {
                Log.d("TAG", "Ad was loaded.");
                mInterstitialAd = interstitialAd;
            }
        });

        fullad.setOnClickListener(view -> {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(MainActivity.this);
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
        });

        // start.io Ads
        // Splash Ad
        StartAppAd.disableSplash();

        // Return Ad
        StartAppSDK.init(this, "StartApp App ID", false);

        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setSecondsBetweenAds(1)
        );
    }
}

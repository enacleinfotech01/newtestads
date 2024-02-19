package com.securex.newtestads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class youtubevideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubevideo);
        WebView webView = findViewById(R.id.webView);

        // Enable JavaScript (required for some YouTube embed codes)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load YouTube embed code
        String youtubeEmbedCode = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/Hg9q1TDB_EM?si=YHOkK3NLgsEC_hMG&amp;controls=0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";

        // Load the YouTube embed code into the WebView
        webView.loadData(youtubeEmbedCode, "text/html", "utf-8");
    }
}
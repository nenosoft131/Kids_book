package com.nenosoft.englishforkids;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nenosof.englishforkids.R;

/**
 * Created by USMAN BUTT on 4/13/2015.
 */
public class Mainpage extends Activity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

    }

    public void OnStartClick(View v) {
        Intent i = new Intent(Mainpage.this, start_page.class);
        startActivity(i);
    }

    public void OnLCClick(View v) {
        Intent i = new Intent(Mainpage.this, lookandchoose.class);
        startActivity(i);
    }
    public void OnQuizClick(View v) {
        Intent i = new Intent(Mainpage.this, quiz.class);
        startActivity(i);
    }

    public void OnVideoClick(View v)
    {
        Intent i = new Intent(Mainpage.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}

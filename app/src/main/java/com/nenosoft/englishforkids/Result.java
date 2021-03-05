package com.nenosoft.englishforkids;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nenosof.englishforkids.R;

/**
 * Created by USMAN BUTT on 4/29/2015.
 */
public class Result extends Activity {
    TextView Crt, InCrt, comment;
    InterstitialAd mInterstitialAd;
    String level[] = {"Poor", "Good", "Very Good", "Excellent "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8695636775775867/4438477439");
        AdRequest adRequest2 = new AdRequest.Builder()
                .addTestDevice("F2751F1BB3463051B9B0B3AD11B1A52C")
                .build();

        mInterstitialAd.loadAd(adRequest2);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });
        Crt = (TextView) findViewById(R.id.crt_id);
        InCrt = (TextView) findViewById(R.id.incrt_id);
        comment = (TextView) findViewById(R.id.tv_comment);

        Crt.setText(String.valueOf(getIntent().getExtras().getInt("right")));
        InCrt.setText(String.valueOf(getIntent().getExtras().getInt("wrong")));
        if (getIntent().getExtras().getInt("right") <= 8) {
            comment.setText("Poor");
            comment.setTextColor(Color.RED);
        } else if (getIntent().getExtras().getInt("right") <= 12) {
            comment.setText("Good");
            comment.setTextColor(Color.YELLOW);
        } else if (getIntent().getExtras().getInt("right") <= 15) {
            comment.setText("Very Good");
            comment.setTextColor(Color.GREEN);
        } else if (getIntent().getExtras().getInt("right") <= 18) {
            comment.setText("Excellent");
            comment.setTextColor(Color.GREEN);
        } else if (getIntent().getExtras().getInt("right") > 18) {
            comment.setText("Exceptional");
            comment.setTextColor(Color.BLUE);
        }
        //   Toast.makeText(Result.this, "Correct: " + String.valueOf(getIntent().getExtras().getInt("right")) + "   Incorrect: " + ), Toast.LENGTH_LONG).show();
    }
}

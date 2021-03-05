package com.nenosoft.englishforkids;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nenosof.englishforkids.R;

import java.util.ArrayList;

public class start_page extends Activity {
    private GridView gridView;
    private AdView mAdView;
    MediaPlayer mp;
    private GridViewSecaAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        customGridAdapter = new GridViewSecaAdapter(start_page.this, R.layout.second_page_layout, getData());
        gridView = (GridView) findViewById(R.id.grid_sec);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = null;
                if (position == 0) {
                    i = new Intent(start_page.this, alphabets.class);
                } else if (position == 1) {
                    i = new Intent(start_page.this, fruites.class);
                } else if (position == 2) {
                    i = new Intent(start_page.this, flower.class);
                } else if (position == 3) {
                    i = new Intent(start_page.this, animals.class);
                } else if (position == 4) {
                    i = new Intent(start_page.this, color.class);
                } else if (position == 5) {
                    i = new Intent(start_page.this, shapes.class);
                } else if (position == 6) {
                    i = new Intent(start_page.this, countries.class);
                } else if (position == 7) {
                    i = new Intent(start_page.this, vegetables.class);
                }
                startActivity(i);
            }
        });
    }

    private ArrayList getData() {
        final ArrayList imageItems = new ArrayList();


        Drawable res = getResources().getDrawable(R.drawable.abc);
        Bitmap bitmap = ((BitmapDrawable) res).getBitmap();
        imageItems.add(new Sec_Info(bitmap, "Alphabets"));
        Drawable res0 = getResources().getDrawable(R.drawable.fruites_icon);
        Bitmap bitmap0 = ((BitmapDrawable) res0).getBitmap();
        imageItems.add(new Sec_Info(bitmap0, "Fruits"));
        Drawable res1 = getResources().getDrawable(R.drawable.flower_icon);
        Bitmap bitmap1 = ((BitmapDrawable) res1).getBitmap();
        imageItems.add(new Sec_Info(bitmap1, "Flowers"));
        Drawable res2 = getResources().getDrawable(R.drawable.animals_icon);
        Bitmap bitmap2 = ((BitmapDrawable) res2).getBitmap();
        imageItems.add(new Sec_Info(bitmap2, "Animals"));
        Drawable res3 = getResources().getDrawable(R.drawable.colors_icon);
        Bitmap bitmap3 = ((BitmapDrawable) res3).getBitmap();
        imageItems.add(new Sec_Info(bitmap3, "Colors"));
        Drawable res4 = getResources().getDrawable(R.drawable.shapes);
        Bitmap bitmap4 = ((BitmapDrawable) res4).getBitmap();
        imageItems.add(new Sec_Info(bitmap4, "Shapes"));
        Drawable res5 = getResources().getDrawable(R.drawable.flags_icon);
        Bitmap bitmap5 = ((BitmapDrawable) res5).getBitmap();
        imageItems.add(new Sec_Info(bitmap5, "Country"));
        Drawable res6 = getResources().getDrawable(R.drawable.vegetables_icon);
        Bitmap bitmap6 = ((BitmapDrawable) res6).getBitmap();
        imageItems.add(new Sec_Info(bitmap6, "Vegetables"));

        return imageItems;

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
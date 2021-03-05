package com.nenosoft.englishforkids;

import android.app.Activity;
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
import com.nenosoft.englishforkids.utils.Utils;
import com.nenosof.englishforkids.R;
import java.util.ArrayList;

public class shapes extends Activity {
    private GridView gridView;
    MediaPlayer mp;
    private GridViewSecaAdapter customGridAdapter;
    private AdView mAdView;


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
        customGridAdapter = new GridViewSecaAdapter(shapes.this, R.layout.second_page_layout, getData());
        gridView = (GridView) findViewById(R.id.grid_sec);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                try {

                    mp.stop();
                    mp.release();
                } catch (Exception e) {

                }
                mp = MediaPlayer.create(shapes.this, Utils.audio_shapes[position]);

                mp.setOnPreparedListener(
                        new MediaPlayer.OnPreparedListener() {
                            public void onPrepared(MediaPlayer player) {
                                mp.start();
                            }
                        });

            }

        });
    }

    private ArrayList getData() {
        final ArrayList imageItems = new ArrayList();

        for (int i = 0; i <Utils.shapes_name.length; i++) {

            Drawable res = getResources().getDrawable(Utils.image_shapes[i]);
            Bitmap bitmap = ((BitmapDrawable) res).getBitmap();
            imageItems.add(new Sec_Info(bitmap,Utils.shapes_name[i]));
        }

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
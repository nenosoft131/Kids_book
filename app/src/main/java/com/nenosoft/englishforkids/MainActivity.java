package com.nenosoft.englishforkids;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nenosof.englishforkids.R;

public class MainActivity extends Activity {
    private AdView mAdView;

    String color_names[] = {"ABC Song", "Twinkle Twinkle", "Baba Black Sheep", "Animals", "Pussy Cat","All Day"};
    Integer image_id[] = {R.drawable.abc_icon, R.drawable.twinkel_icon, R.drawable.baba_icon, R.drawable.pname_icon, R.drawable.pcat_icon, R.drawable.allday_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        Customlistadapter adapter = new Customlistadapter(this, image_id, color_names);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(MainActivity.this, "Pos" + position, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,Videoplayer.class);
                i.putExtra("name",color_names[position]);
                startActivity(i);
            }
        });
        lv.setAdapter(adapter);


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
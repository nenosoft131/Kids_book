package com.nenosoft.englishforkids;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nenosof.englishforkids.R;
import com.nenosoft.englishforkids.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class quiz extends Activity {
    private GridView gridView;
    MediaPlayer mp;
    private GridViewAdapter customGridAdapter;
    ArrayList<Integer> allquestions = new ArrayList<Integer>();
    ArrayList imageItems;
    int k = 0;
    int right=0;
    private AdView mAdView;
    int wrong=0;
    int[] answer = new int[18];
    boolean ch = false;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid2); mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8695636775775867/4438477439");
        AdRequest adRequest2 = new AdRequest.Builder()
                .addTestDevice("F2751F1BB3463051B9B0B3AD11B1A52C")
                .build();

        mInterstitialAd.loadAd(adRequest2);


        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

        st();
    }

    public void st()
    {
        for (int l = 0; l < answer.length; l++) {
            answer[l]=10000;
        }
         k = 0;
         right=0;
         wrong=0;
         ch = false;
        allquestions.clear();
        allquestions.addAll(randquestions(Utils.all_name));
        customGridAdapter = new GridViewAdapter(quiz.this, R.layout.grid_row, getData());
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (answer != null && answer.length > 0) {
                    for (int l = 0; l < answer.length; l++) {
                        if (answer[l] == position) {
                            ch = true;
                        }
                    }
                }
                if (!ch) {
                    displayAlertDialog(position);

                }
                ch = false;
            }
        });
    }

    private ArrayList getData() {
        imageItems = new ArrayList();

        for (int i = 0; i < 18; i++) {

            Drawable res = getResources().getDrawable(getResources().getIdentifier(Utils.all_name[allquestions.get(i) - 1].toLowerCase(), "drawable", getPackageName()));
            Bitmap bitmap = ((BitmapDrawable) res).getBitmap();
            imageItems.add(new ImageItem(bitmap, "Image#" + i));
        }

        return imageItems;

    }



    private ArrayList<Integer> randquestions(String[] questions) {
        ArrayList<Integer> number = new ArrayList<Integer>();
        ArrayList<Integer> number2 = new ArrayList<Integer>();
        for (int i = 1; i <= questions.length; ++i) number.add(i);
        Collections.shuffle(number);

        for (int i = 0; i < 18; i++) {
            number2.add(number.get(i));
        }

        return number2;
    }

    public void displayAlertDialog(final int pos) {
        final EditText ed;
        final ImageView im;
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.answer_dialog, null);
        ed = (EditText) alertLayout.findViewById(R.id.et);
        im = (ImageView) alertLayout.findViewById(R.id.iv);
        final String op1 = Utils.all_name[allquestions.get(pos) - 1].toLowerCase();
        im.setImageResource(getResources().getIdentifier(op1, "drawable", getPackageName()));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Spelling");
        alert.setView(alertLayout);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Drawable res;
                if (op1.equalsIgnoreCase(ed.getText().toString())) {
                    // Toast.makeText(getBaseContext(), "Right", Toast.LENGTH_SHORT).show();
                    res = getResources().getDrawable(R.drawable.tick);
                    right++;

                } else {
                    //Toast.makeText(getBaseContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    res = getResources().getDrawable(R.drawable.cros);
                    wrong++;
                }

                Bitmap bitmap = ((BitmapDrawable) res).getBitmap();
                imageItems.remove(pos);
                imageItems.add(pos, new ImageItem(bitmap, "Image#" + pos));
                answer[k] = pos;
                k++;
                customGridAdapter.notifyDataSetChanged();
                if(k==18) {
                    mInterstitialAd.show();
                    Toast.makeText(getBaseContext(), "Right" + right + "/Wrong" + wrong, Toast.LENGTH_SHORT).show();
                    infoDialog(quiz.this,"Result","Right: "+String.valueOf(right)+"      Wrong: "+String.valueOf(wrong));

                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public  void infoDialog(Context ctx, String title, String message) {
        AlertDialog.Builder b = new AlertDialog.Builder(ctx);
        b.setMessage(message);
        b.setCancelable(false);
        b.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();

            }
        });
        b.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                st();

            }
        });
        b.setTitle(title);
        AlertDialog ad = b.create();
        ad.show();
    }

    public void OnrestartClick(View c)
    {
        st();
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
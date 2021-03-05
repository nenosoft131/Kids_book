package com.nenosoft.englishforkids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nenosof.englishforkids.R;
import com.nenosoft.englishforkids.utils.Utils;

@SuppressLint("DefaultLocale")
public class lookandchoose extends Activity {
    /**
     * Called when the activity is first created.
     */

    ViewAnimator viewAnimator;
    boolean check = true;
    String[] movies;
    int right = 0;
    int c = 1;
    MediaPlayer mp;
    int wrong = 0;
    int i = 0;
    ImageView im1, im2;
    int count = 0;
    int total =0;
    String[] option;
    boolean hint_check = false;
    String name;
    Button op1, op2, op3, op4;
    Random ran = new Random();
    int number;
    AlertDialog dialog;
    RelativeLayout img, c1, c3;
    LinearLayout c2, c5;
    CheckBox sound, vibrate;
    Button hint_btn;
    TextView tv,q_num;
    SharedPreferences.Editor editor;
    int color_array[] = {R.color.aqua, R.color.custom, R.color.custom2, R.color.fuchsia};
    private AdView mAdView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lookandchoose);
        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        editor = getSharedPreferences("pref", MODE_PRIVATE).edit();
        q_num= (TextView) findViewById(R.id.tv_qnumber);
        c1 = (RelativeLayout) findViewById(R.id.c1);
        c3 = (RelativeLayout) findViewById(R.id.c3);
        c2 = (LinearLayout) findViewById(R.id.c2);
        c5 = (LinearLayout) findViewById(R.id.c5);
        Random r = new Random();
        int i1 = r.nextInt(4 - 0);
      //  c1.setBackgroundColor(getResources().getColor(color_array[i1]));
      //  c2.setBackgroundColor(getResources().getColor(color_array[i1]));
       // c3.setBackgroundColor(getResources().getColor(color_array[i1]));
      //  c5.setBackgroundColor(getResources().getColor(color_array[i1]));

        im1 = (ImageView) findViewById(R.id.imageView1);
        im2 = (ImageView) findViewById(R.id.imageView2);
        tv = (TextView) findViewById(R.id.tv_hint);
        viewAnimator = (ViewAnimator) this.findViewById(R.id.viewFlipper);
        img = (RelativeLayout) findViewById(R.id.ll);
        //hint_btn = (Button) findViewById(R.id.button1);

        movies = getResources().getStringArray(R.array.movies_name_easy);
        randmovies(movies);
        name = movies[i];

        op1 = (Button) findViewById(R.id.btn_op_1);
        op2 = (Button) findViewById(R.id.btn_op_2);
        op3 = (Button) findViewById(R.id.btn_op_3);
        op4 = (Button) findViewById(R.id.btn_op_4);

        im1.setBackgroundResource(getResources().getIdentifier(
                "drawable/" + movies[i].replace(" ", "").toLowerCase(), null,
                getPackageName()));
        displayquestions();
        calcutaion();

    }

    public void cancelcilck(View v) {
        Animation an1;
        an1 = AnimationUtils.loadAnimation(this, R.anim.animate2);

        // an2 = AnimationUtils.loadAnimation(this, R.anim.push_left_out);

        img.startAnimation(an1);

        img.setVisibility(View.GONE);
    }

    public void onhintclick(View v) {
        displayAlertDialog();
    }

    private void calcutaion() {
        // TODO Auto-generated method stub

    }

    public void OnPlayAgain(View v) {
        finish();
    }

    private void displayquestions() {
        // TODO Auto-generated method stub
        hint_check = false;
        int j = 0;
        total++;
        updateq();
        op1.setBackgroundResource(R.drawable.buttontab);
        op2.setBackgroundResource(R.drawable.buttontab);
        op3.setBackgroundResource(R.drawable.buttontab);
        op4.setBackgroundResource(R.drawable.buttontab);

        img.setVisibility(View.GONE);

        name = movies[count];
        option = getResources().getStringArray(
                getResources().getIdentifier(
                        movies[count].replace(" ", "").toLowerCase(), "array",
                        getPackageName()));
        tv.setText(getResources().getIdentifier("@string/" + name.replace(" ", ""), null,
                getPackageName()));
        randomize_option(option);
        count++;
        op1.setText(option[j++]);
        op2.setText(option[j++]);
        op3.setText(option[j++]);
        op4.setText(option[j++]);
    }

    private void randmovies(String[] movies2) {
        List<String> aList = new ArrayList<String>();
        for (String s : movies2)
            aList.add(s);
        Collections.shuffle(aList);

        for (int i = 0; i < movies2.length; i++) {
            movies[i] = aList.get(i);
        }
    }

    private void randomize_option(String[] option2) {
        List<String> aList = new ArrayList<String>();
        for (String s : option2)
            aList.add(s);
        Collections.shuffle(aList);

        for (int i = 0; i < option.length; i++) {
            option[i] = aList.get(i);
        }
    }

    public void Click(final View v) {
        final String tag = (String) v.getTag();
        if (option[Integer.parseInt(tag) - 1].replace(" ", "")
                .equalsIgnoreCase(name.replace(" ", ""))) {

            if (op1.getTag() == tag) {
                op1.setBackgroundResource(R.drawable.correct_button);
            } else if (op2.getTag() == tag) {
                op2.setBackgroundResource(R.drawable.correct_button);

            } else if (op3.getTag() == tag) {
                op3.setBackgroundResource(R.drawable.correct_button);
            } else if (op4.getTag() == tag) {
                op4.setBackgroundResource(R.drawable.correct_button);
            }
            mp = MediaPlayer.create(lookandchoose.this, R.raw.correct);

            mp.setOnPreparedListener(
                    new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer player) {
                            SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
                            if (prefs.getBoolean("sound", false)) {

                                mp.start();
                            }
                        }
                    });
            right++;

        } else {
            wrong++;
            mp = MediaPlayer.create(lookandchoose.this, R.raw.incorrect);

            mp.setOnPreparedListener(
                    new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer player) {
                            SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
                            if (prefs.getBoolean("sound", false)) {
                                mp.start();
                            }
                        }
                    });
            Vibrator v1 = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
            if (prefs.getBoolean("vibrate", false)) {

                v1.vibrate(500);
            }
            if (op1.getTag() == tag) {
                op1.setBackgroundResource(R.drawable.wrong_btn);
            } else if (op2.getTag() == tag) {
                op2.setBackgroundResource(R.drawable.wrong_btn);

            } else if (op3.getTag() == tag) {
                op3.setBackgroundResource(R.drawable.wrong_btn);
            } else if (op4.getTag() == tag) {
                op4.setBackgroundResource(R.drawable.wrong_btn);
            }

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    for (int cc = 0; cc < option.length; cc++) {

                        if (option[cc].replace(" ", "").equalsIgnoreCase(name.replace(" ", ""))) {
                            if (cc == 0) {
                                op1.setBackgroundResource(R.drawable.correct_button);
                            }
                            if (cc == 1) {
                                op2.setBackgroundResource(R.drawable.correct_button);
                            }
                            if (cc == 2) {
                                op3.setBackgroundResource(R.drawable.correct_button);
                            }
                            if (cc == 3) {
                                op4.setBackgroundResource(R.drawable.correct_button);
                            }
                        }
                    }

                }
            }, 500);

            // return;
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (i + 1 < 20) {
                    i++;
                } else {

                    Intent i = new Intent(lookandchoose.this, Result.class);
                    i.putExtra("right", right);
                    i.putExtra("wrong", wrong);
                    startActivity(i);
                    finish();
                }
                if (check) {

                    AnimationFactory.flipTransition(viewAnimator,
                            AnimationFactory.FlipDirection.RIGHT_LEFT);
                    im2.setBackgroundResource(getResources().getIdentifier(
                            "drawable/"
                                    + movies[i].replace(" ", "").toLowerCase(),
                            null, getPackageName()));

                    check = false;
                } else {
                    AnimationFactory.flipTransition(viewAnimator,
                            AnimationFactory.FlipDirection.LEFT_RIGHT);
                    im1.setBackgroundResource(getResources().getIdentifier(
                            "drawable/"
                                    + movies[i].replace(" ", "").toLowerCase(),
                            null, getPackageName()));

                    check = true;
                }

                displayquestions();

            }
        }, 2000);

    }




    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_setting, null);

        sound = (CheckBox) alertLayout.findViewById(R.id.cb_sound);
        vibrate = (CheckBox) alertLayout.findViewById(R.id.cb_vibrat);
        SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
        sound.setChecked(prefs.getBoolean("sound", false));
        vibrate.setChecked(prefs.getBoolean("vibrate", false));

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Quiz Setting");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.putBoolean("sound", sound.isChecked());
                editor.putBoolean("vibrate", vibrate.isChecked());
                editor.commit();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void updateq()
    {
        q_num.setText("Current Question:"+total+"/20");
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
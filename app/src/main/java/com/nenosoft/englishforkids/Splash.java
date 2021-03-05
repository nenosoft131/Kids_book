package com.nenosoft.englishforkids;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;

import com.nenosof.englishforkids.R;

public class Splash extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);


        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(Splash.this,
                            Mainpage.class);
                    startActivity(i);
                    finish();
                }

            }
        });

        t.start();

    }


}

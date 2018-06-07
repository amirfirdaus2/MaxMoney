package com.mobile.app.maxmoney.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobile.app.maxmoney.Activity.Introduction.IntroScreenActivity;
import com.mobile.app.maxmoney.R;
import com.mobile.app.maxmoney.Utils.PreferenceManagerLogin;

public class SplashScreen extends AppCompatActivity {
    TextView textMobile,ph1,ph2;
    ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    Intent MainActivityGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        //declaration
        textMobile = findViewById(R.id.textView_mobile);
        ph1 = findViewById(R.id.textView_ph1);
        ph2 = findViewById(R.id.textView_ph2);
        progressBar = findViewById(R.id.progressBar);
        MainActivityGo = new Intent(getApplicationContext(),IntroScreenActivity.class);
        //set font type avenir
        textMobile.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Light-07.ttf"));
        ph1.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Light-07.ttf"));
        ph2.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Light-07.ttf"));
        //build version more than lolipop only
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
        }
        //progressBar function
        progressBarFunctionality();
    }

    private void progressBarFunctionality() {
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(progressStatus == 100){
                    startActivity(MainActivityGo);
                }
            }
        }).start();
    }
}

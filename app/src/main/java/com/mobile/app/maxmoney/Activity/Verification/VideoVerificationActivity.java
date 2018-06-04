package com.mobile.app.maxmoney.Activity.Verification;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.maxmoney.Activity.LoginActivity;
import com.mobile.app.maxmoney.Activity.LoginActivity_old;
import com.mobile.app.maxmoney.Activity.Verification.camera.VideoActivity;
import com.mobile.app.maxmoney.R;

public class VideoVerificationActivity extends AppCompatActivity {
    TextView textView_v1;
    Button button_submit;
    Intent intent_next,intent_cancel;
    ImageView imageView_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_verification);

        textView_v1 = findViewById(R.id.textView_v1);
        button_submit = findViewById(R.id.button_submit);
        imageView_cancel = findViewById(R.id.imageView_cancel);

        intent_next = new Intent(getApplicationContext(), VideoActivity.class);
        intent_cancel = new Intent(getApplicationContext(), LoginActivity.class);

        ChangeFontType();

        //onclick
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_next);
            }
        });
        imageView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_cancel.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_cancel);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    private void ChangeFontType() {
        textView_v1.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        button_submit.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }
}

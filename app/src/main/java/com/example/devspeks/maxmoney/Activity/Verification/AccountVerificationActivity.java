package com.example.devspeks.maxmoney.Activity.Verification;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devspeks.maxmoney.Activity.LoginActivity;
import com.example.devspeks.maxmoney.Activity.LoginActivity_old;
import com.example.devspeks.maxmoney.R;

public class AccountVerificationActivity extends AppCompatActivity {
    TextView textView_v1,textView_v2,textView_v3,textView_v4;
    Button button_start;
    Intent intent_start,intent_cancel;
    ImageView imageView_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verification);

        //declare
        textView_v1 = findViewById(R.id.textView_v1);
        textView_v2 = findViewById(R.id.textView_v2);
        textView_v3 = findViewById(R.id.textView_v3);
        textView_v4 = findViewById(R.id.textView_v4);

        imageView_cancel = findViewById(R.id.imageView_cancel);

        intent_start = new Intent(getApplicationContext(),CddActivity.class);
        intent_cancel = new Intent(getApplicationContext(), LoginActivity.class);

        button_start = findViewById(R.id.button_start);

        //set font
        ChangeFontType();

        //onclick
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_start);
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

    private void ChangeFontType() {
        textView_v1.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_v2.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        textView_v3.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_v4.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        button_start.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}

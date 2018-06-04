package com.mobile.app.maxmoney.Activity.Registeration;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.app.maxmoney.Activity.LoginActivity;
import com.mobile.app.maxmoney.Activity.LoginActivity_old;
import com.mobile.app.maxmoney.Activity.Registeration.UserDetailsPersonal.UserDetailsPersonalActivity;
import com.mobile.app.maxmoney.R;

public class AccountTypeActivity extends AppCompatActivity {
    ImageView imgView_personal,imgView_business,imgView_back,imgView_cancel;
    TextView textView_personal,textView_business,textView_accType;
    Button button_select;
    Intent intent_back,intent_cancel,intent_select_personal;
    Boolean boolean_personal = false,boolean_business = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        //declaration
        textView_business = findViewById(R.id.textView_business);
        textView_personal = findViewById(R.id.textView_personal);
        textView_accType = findViewById(R.id.textView_accType);
        imgView_business = findViewById(R.id.imageView_business);
        imgView_personal = findViewById(R.id.imageView_personal);
        button_select = findViewById(R.id.button_select);
        imgView_cancel = findViewById(R.id.imageView_cancel);
        imgView_back = findViewById(R.id.imageView_back);
        intent_back = new Intent(getApplicationContext(),LoginActivity.class);
        intent_cancel = new Intent(getApplicationContext(), LoginActivity.class);
        intent_select_personal = new Intent(getApplicationContext(), UserDetailsPersonalActivity.class);

        //font type setting
        textView_business.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_personal.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        textView_accType.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        button_select.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));

        //on click funtion all
        imgView_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_personal.setTextColor(Color.parseColor("#0470C5"));
                imgView_personal.setBackgroundColor(Color.parseColor("#0470C5"));
                imgView_business.setBackgroundColor(Color.TRANSPARENT);
                textView_business.setTextColor(Color.BLACK);
                boolean_personal = true;
                boolean_business = false;
            }
        });
        imgView_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_business.setTextColor(Color.parseColor("#0470C5"));
                imgView_business.setBackgroundColor(Color.parseColor("#0470C5"));
                textView_personal.setTextColor(Color.BLACK);
                imgView_personal.setBackgroundColor(Color.TRANSPARENT);
                boolean_business = true;
                boolean_personal = false;
            }
        });
        imgView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_cancel.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_cancel);
            }
        });
        imgView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_back);
            }
        });
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boolean_business == false && boolean_personal == false){
                    Toast.makeText(getApplicationContext(),"Please select Account Type",Toast.LENGTH_SHORT).show();
                }else if(boolean_personal == true){
                    startActivity(intent_select_personal);
                }else if(boolean_business == true){
                    Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}

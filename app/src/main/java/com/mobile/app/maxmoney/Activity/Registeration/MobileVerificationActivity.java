package com.mobile.app.maxmoney.Activity.Registeration;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.maxmoney.Activity.LoginActivity;
import com.mobile.app.maxmoney.Activity.LoginActivity_old;
import com.mobile.app.maxmoney.Activity.Verification.AccountVerificationActivity;
import com.mobile.app.maxmoney.R;

public class MobileVerificationActivity extends AppCompatActivity {
    EditText editText_v1,editText_v2,editText_v3,editText_v4;
    TextView textView_1,textView_2,textView_3,textView_4;
    Button submit;
    ImageView imageView_back,imageView_cancel;
    Intent intent_back,intent_cancel,intent_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);

        //declare
        editText_v1 = findViewById(R.id.editText_v1);
        editText_v2 = findViewById(R.id.editText_v2);
        editText_v3 = findViewById(R.id.editText_v3);
        editText_v4 = findViewById(R.id.editText_v4);

        textView_1 = findViewById(R.id.textView_mobile);
        textView_2 = findViewById(R.id.textView_digit);
        textView_3 = findViewById(R.id.textView_register);
        textView_4 = findViewById(R.id.textView_below);

        imageView_back = findViewById(R.id.imageView_back);
        imageView_cancel = findViewById(R.id.imageView_cancel);

        intent_back = new Intent(getApplicationContext(),AddressActivity.class);
        intent_cancel = new Intent(getApplicationContext(), LoginActivity.class);
        intent_next = new Intent(getApplicationContext(), AccountVerificationActivity.class);

        submit = findViewById(R.id.button_submit);

        //verification of code 4 number
        editText_v1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(count==1)
                {
                    editText_v2.requestFocus();
                }

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
        editText_v2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(count==1)
                {
                    editText_v3.requestFocus();
                }else{
                    editText_v1.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
        editText_v3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(count == 1)
                {
                    editText_v4.requestFocus();
                }else{
                    editText_v2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
        editText_v4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(count == 0)
                {
                    editText_v3.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });

        //set type font for all text in activities
        ChangeFontType();

        //image back and cancel on click
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_back);
            }
        });
        imageView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_cancel.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_cancel);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_next);
            }
        });

    }

    private void ChangeFontType() {
        textView_1.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_2.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_3.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_4.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_v1.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_v2.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_v3.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_v4.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        submit.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }
    @Override
    public void onBackPressed() {
        //do nothing
    }
}

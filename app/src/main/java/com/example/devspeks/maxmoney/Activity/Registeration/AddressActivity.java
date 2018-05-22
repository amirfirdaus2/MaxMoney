package com.example.devspeks.maxmoney.Activity.Registeration;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devspeks.maxmoney.Activity.LoginActivity;
import com.example.devspeks.maxmoney.Activity.Registeration.UserDetailsPersonal.UserDetailsPersonalActivity;
import com.example.devspeks.maxmoney.Common.Validation;
import com.example.devspeks.maxmoney.R;
public class AddressActivity extends AppCompatActivity {
    TextView textView_address;
    EditText editText_address1,editText_address2,editText_state,editText_city,editText_poskod;
    Button submit;
    ImageView imageView_back,imageView_cancel;
    Intent intent_back,intent_cancel,intent_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        textView_address = findViewById(R.id.textView_address);
        editText_address1 = findViewById(R.id.editText_address1);
        editText_address2 = findViewById(R.id.editText_address2);
        editText_state = findViewById(R.id.editText_state);
        editText_city = findViewById(R.id.editText_city);
        editText_poskod = findViewById(R.id.editText_poskod);
        imageView_back = findViewById(R.id.imageView_back);
        imageView_cancel = findViewById(R.id.imageView_cancel);
        intent_back = new Intent(getApplicationContext(),UserDetailsPersonalActivity.class);
        intent_cancel = new Intent(getApplicationContext(), LoginActivity.class);
        submit = findViewById(R.id.button_submit);
        intent_next = new Intent(getApplicationContext(),MobileVerificationActivity.class);
        ChangeFontType();


        //validation
        Validation.setCapitalizeTextWatcher(editText_city);
        Validation.setCapitalizeTextWatcher(editText_state);

        //on click image go back and cancel
        imageView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_cancel.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_cancel);
            }
        });
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_back);
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
        textView_address.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_address1.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_address2.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_state.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_city.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_poskod.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        submit.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }
    @Override
    public void onBackPressed() {
        //do nothing
    }
}

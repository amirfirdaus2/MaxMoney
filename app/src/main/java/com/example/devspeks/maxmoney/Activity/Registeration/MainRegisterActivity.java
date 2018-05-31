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

import com.example.devspeks.maxmoney.Common.Validation;
import com.example.devspeks.maxmoney.Activity.LoginActivity_old;
import com.example.devspeks.maxmoney.R;

public class MainRegisterActivity extends AppCompatActivity {
    TextView txtView_create,txtView_login,txtView_member;
    Button button_register;
    Intent intent_login,intent_accType;
    ImageView imgView_cancel;
    EditText editText_email,editText_password,editText_passwordRe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        //declaration
        txtView_create = findViewById(R.id.textView_create);
        txtView_login = findViewById(R.id.textView_login);
        txtView_member = findViewById(R.id.textView_member);
        button_register = findViewById(R.id.button_register);
        intent_login = new Intent(getApplicationContext(), LoginActivity_old.class);
        intent_accType = new Intent(getApplicationContext(), AccountTypeActivity.class);
        imgView_cancel = findViewById(R.id.imageView_cancel);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_passwordRe = findViewById(R.id.editText_passwordRe);

        //set font type avenir
        ChangeFontType();

        //on click button or image
        imgView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_login);
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_email.getText().toString() == null || Validation.isEmailValid(editText_email.getText().toString()) == false) {
                    editText_email.setError("Invalid Email Address");
                }else if(editText_password.getText().toString() == null || editText_password.length() < 6){
                    editText_password.setError("Invalid Password");
                }else if(editText_passwordRe.getText().toString() == null || editText_passwordRe.length() < 6){
                    editText_passwordRe.setError("Invalid Password");
                }else if(!editText_passwordRe.getText().toString().trim().equals(editText_password.getText().toString().trim())){
                    editText_passwordRe.setError("Re-enter password not same");
                }else{
                    editText_email.setError(null);editText_email.clearFocus();
                    editText_password.setError(null);editText_password.clearFocus();
                    editText_passwordRe.setError(null);editText_passwordRe.clearFocus();


                    startActivity(intent_accType);
                }
            }
        });
        txtView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_login);
            }
        });

    }
    private void ChangeFontType() {
        txtView_create.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        txtView_login.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        txtView_member.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        button_register.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_email.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_password.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_passwordRe.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}

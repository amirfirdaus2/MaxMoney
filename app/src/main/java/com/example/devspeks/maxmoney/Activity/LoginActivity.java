package com.example.devspeks.maxmoney.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devspeks.maxmoney.Common.StandardProgressDialog;
import com.example.devspeks.maxmoney.Common.Validation;
import com.example.devspeks.maxmoney.Activity.Registeration.MainRegisterActivity;
import com.example.devspeks.maxmoney.Model.LoginAPI;
import com.example.devspeks.maxmoney.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.devspeks.maxmoney.Common.BasedURL.ROOT_URL;

public class LoginActivity extends AppCompatActivity {
    TextView txt_newUser,txt_forgotPassword,txt_signIn,txt_createAcc;
    Button button_login;
    ImageView imgView_cancel;
    Intent intent_register;
    EditText editText_email,editText_password;
    private StandardProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgressDialog = new StandardProgressDialog(this);

        //declaration
        txt_newUser = findViewById(R.id.textView_newUser);
        txt_forgotPassword = findViewById(R.id.textView_forgot);
        txt_signIn = findViewById(R.id.textView_signIn);
        txt_createAcc = findViewById(R.id.textView_account);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);
        imgView_cancel = findViewById(R.id.imageView_cancel);
        intent_register = new Intent(getApplicationContext(), MainRegisterActivity.class);

        //set font type avenir
        ChangeFontType();

        imgView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(intent_register);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_email.getText().toString() == null || Validation.isEmailValid(editText_email.getText().toString()) == false) {
                    editText_email.setError("Invalid Email Address");
                }else if(editText_password.getText().toString() == null || editText_password.length() < 3){
                    editText_password.setError("Invalid Password");
                }else{
                    mProgressDialog.show();
                    editText_email.setError(null);editText_email.clearFocus();
                    editText_password.setError(null);editText_password.clearFocus();

                    RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ROOT_URL).build();
                    LoginAPI api = adapter.create(LoginAPI.class);
                    api.loginUser(
                            editText_email.getText().toString(),
                            editText_password.getText().toString(),
                            new Callback<Response>() {
                                @Override
                                public void success(Response result, Response response) {
                                    BufferedReader reader = null;
                                    String output = "";
                                    try {
                                        reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                                        output = reader.readLine();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    try {
                                        mProgressDialog.dismiss();
                                        JSONObject obj = new JSONObject(output);
                                        if(obj.getString("status").equals("false")){
                                            Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    mProgressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                }
            }
        });

    }

    private void ChangeFontType() {
        txt_newUser.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        txt_forgotPassword.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        txt_signIn.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        txt_createAcc.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        button_login.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_email.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_password.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}

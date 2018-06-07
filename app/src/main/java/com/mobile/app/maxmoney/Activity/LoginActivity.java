package com.mobile.app.maxmoney.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.app.maxmoney.Activity.Registeration.AccountTypeActivity;
import com.mobile.app.maxmoney.Common.Validation;
import com.mobile.app.maxmoney.Model.LoginAPI;
import com.mobile.app.maxmoney.R;
import com.mobile.app.maxmoney.Utils.PreferenceManagerLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.mobile.app.maxmoney.Common.BasedURL.ROOT_URL;

public class LoginActivity extends AppCompatActivity implements Animation.AnimationListener {

    LinearLayout linearLayout_front,linearLayout_back;
    RelativeLayout relativeLayout_id;
    private Animation animation;
    TextView textView_register,textView_signIn,textView_forgotPassword,textView_copyRight;
    Button button_login,button_signIn;
    EditText editText_email,editText_password,editText_email_b,editText_password_b,editText_repassword_b;
    RelativeLayout relative_id_hide;
    Boolean statusLayout = false;
    Intent intent_accType;
    private static long back_pressed;
    PreferenceManagerLogin session;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_and_sign_up);

        session = new PreferenceManagerLogin(getApplicationContext());

        relative_id_hide = findViewById(R.id.relative_id_hide);
        relativeLayout_id = findViewById(R.id.relative_id);

        linearLayout_front = findViewById(R.id.linearLayout_front);
        linearLayout_back = findViewById(R.id.linearLayout_back);

        button_login = findViewById(R.id.button_login);
        button_signIn = findViewById(R.id.button_register);

        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_email_b = findViewById(R.id.editText_email_b);
        editText_password_b = findViewById(R.id.editText_password_b);
        editText_repassword_b = findViewById(R.id.editText_repassword_b);

        textView_signIn = findViewById(R.id.textView_signIn);
        textView_register = findViewById(R.id.textView_register);
        textView_copyRight = findViewById(R.id.textView_copyRight);
        textView_forgotPassword = findViewById(R.id.textView_forgotPassword);

        animation = AnimationUtils.loadAnimation(this, R.drawable.from_middle);
        animation.setAnimationListener(this);

        intent_accType = new Intent(getApplicationContext(), AccountTypeActivity.class);


        relative_id_hide.setVisibility(RelativeLayout.GONE);
        ChangeFontType();
        moveToBack(linearLayout_back);

        final RelativeLayout layout = new RelativeLayout(relativeLayout_id.getContext());
        layout.setGravity(Gravity.CENTER);


        linearLayout_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusLayout == false){ }else{
                    statusLayout = false;
                    moveToBack(linearLayout_back);
                    linearLayout_front.bringToFront();
                    relative_id_hide.bringToFront();
                    linearLayout_front.clearAnimation();
                    linearLayout_front.setAnimation(animation);
                    linearLayout_front.startAnimation(animation);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linearLayout_front.getLayoutParams();
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    params.setMargins(60,150,60,0);
                    linearLayout_front.setLayoutParams(params);

                    RelativeLayout.LayoutParams paramss = (RelativeLayout.LayoutParams) linearLayout_back.getLayoutParams();
                    paramss.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    paramss.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    paramss.setMargins(60,0,60,0);
                    linearLayout_back.setLayoutParams(paramss);
                }
            }
        });
        linearLayout_back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                if(statusLayout == false){
                    statusLayout = true;
                    moveToBack(linearLayout_front);
                    linearLayout_back.bringToFront();
                    relative_id_hide.bringToFront();
                    linearLayout_back.clearAnimation();
                    linearLayout_back.setAnimation(animation);
                    linearLayout_back.startAnimation(animation);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linearLayout_back.getLayoutParams();
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    params.setMargins(60,150,60,0);
                    linearLayout_back.setLayoutParams(params);

                    RelativeLayout.LayoutParams paramss = (RelativeLayout.LayoutParams) linearLayout_front.getLayoutParams();
                    paramss.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    paramss.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    paramss.setMargins(60,0,60,0);
                    linearLayout_front.setLayoutParams(paramss);
                }else{}
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
                    relative_id_hide.setVisibility(RelativeLayout.VISIBLE);
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
                                        relative_id_hide.setVisibility(RelativeLayout.GONE);
                                        JSONObject obj = new JSONObject(output);
                                        if(obj.getString("status").equals("false")){
                                            Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();
                                        }else {
                                            session.createLoginSession(obj.getString("token"),editText_email.getText().toString(),editText_password.getText().toString());
                                            Intent next = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(next);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void failure(RetrofitError error) {
                                    relative_id_hide.setVisibility(RelativeLayout.GONE);
                                    Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                }
            }
        });

        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_email_b.getText().toString() == null || Validation.isEmailValid(editText_email_b.getText().toString()) == false) {
                    editText_email_b.setError("Invalid Email Address");
                }else if(editText_password_b.getText().toString() == null || editText_password_b.length() < 4){
                    editText_password_b.setError("Invalid Password");
                }else if(!editText_password_b.getText().toString().trim().equals(editText_repassword_b.getText().toString().trim())){
                    editText_repassword_b.setError("Re-enter password not same");
                }else{
                    startActivity(intent_accType);
                }
            }
        });
    }

    private void moveToBack(View myCurrentView) {
        ViewGroup myViewGroup = ((ViewGroup) myCurrentView.getParent());
        int index = myViewGroup.indexOfChild(myCurrentView);
        for(int i = 0; i<index; i++)
        {
            myViewGroup.bringChildToFront(myViewGroup.getChildAt(i));
        }
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }
    @Override
    public void onAnimationEnd(Animation animation) {

    }
    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    private void ChangeFontType() {
        button_login.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_forgotPassword.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_copyRight.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        button_signIn.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        textView_register.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Black-03.ttf"));
        textView_signIn.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Black-03.ttf"));
    }
    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis())  moveTaskToBack(true);
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}

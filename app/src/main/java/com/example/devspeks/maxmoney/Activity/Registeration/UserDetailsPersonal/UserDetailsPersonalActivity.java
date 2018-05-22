package com.example.devspeks.maxmoney.Activity.Registeration.UserDetailsPersonal;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devspeks.maxmoney.Activity.Registeration.AccountTypeActivity;
import com.example.devspeks.maxmoney.Activity.Registeration.AddressActivity;
import com.example.devspeks.maxmoney.Common.Validation;
import com.example.devspeks.maxmoney.Activity.LoginActivity;
import com.example.devspeks.maxmoney.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class UserDetailsPersonalActivity extends AppCompatActivity {
    OkHttpClient client;
    MediaType JSON;
    SearchableSpinner spinner_country,spinner_phoneNo;
    ArrayAdapter<String> spinnerNationalityAdapter;
    ArrayAdapter<String> spinnerPhoneNoAdapter;
    EditText editText_fullName,editText_icNo,editText_dob,editText_phoneNo,editText_doe;
    TextView textView_userDetails;
    ImageView imgView_back,imgView_cancel;
    Intent intent_back,intent_cancel,intent_next;
    LinearLayout linearLayout_doe;
    Button button_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //read JSON file from assets
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");

        //birthday edit text and expiry date
        TextWatcher tw = ValidationBirthday();
        TextWatcher tww = ValidationExpiry();

        //declaration
        spinner_country = findViewById(R.id.spinner_nationality);
        spinner_phoneNo = findViewById(R.id.spinner_phoneNo);
        editText_fullName = findViewById(R.id.editText_fullName);
        editText_icNo = findViewById(R.id.editText_icNo);
        editText_dob = findViewById(R.id.editText_dob);
        editText_phoneNo = findViewById(R.id.editText_phoneNo);
        editText_doe = findViewById(R.id.editText_doe);
        editText_dob.addTextChangedListener(tw);
        editText_doe.addTextChangedListener(tww);
        textView_userDetails = findViewById(R.id.textView_userDetails);
        imgView_cancel = findViewById(R.id.imageView_cancel);
        imgView_back = findViewById(R.id.imageView_back);
        linearLayout_doe = findViewById(R.id.linear_dateofexpiry);
        intent_back = new Intent(getApplicationContext(),AccountTypeActivity.class);
        intent_cancel = new Intent(getApplicationContext(), LoginActivity.class);
        intent_next  = new Intent(getApplicationContext(), AddressActivity.class);
        button_select = findViewById(R.id.button_select);
        //set font
        ChangeFontType();
        Validation.setCapitalizeTextWatcher(editText_fullName);

        //on click image go back and cancel
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

        //spinner setter nationality
        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray array = obj.getJSONArray("country");

            ArrayList<String> list = new ArrayList<String>();
            for(int i=0; i<array.length(); i++) {
                JSONObject yeah = array.getJSONObject(i);
                list.add(yeah.getString("nationality"));
            }
            spinnerNationalityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            spinner_country.setTitle("Choose Country");
            spinner_country.setAdapter(spinnerNationalityAdapter);
            spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(spinner_country.getSelectedItem().toString().equals("Malaysian")){
                        editText_icNo.setHint("Your IC Number");
                        linearLayout_doe.setVisibility(LinearLayout.GONE);
                        editText_icNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
                    }else{
                        editText_icNo.setHint("Your Passport Number");
                        editText_icNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                        linearLayout_doe.setVisibility(LinearLayout.VISIBLE);
                    }
                    String spinner = parent.getItemAtPosition(position).toString();
                    try {
                        JSONObject obj = new JSONObject(readJSONFromAsset());
                        JSONArray array = obj.getJSONArray("country");
                        for(int i = 0; i < array.length();i++){
                            JSONObject yeah = array.getJSONObject(i);
                            if(spinner.equals(yeah.getString("name"))){

                            }
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //spinner setter phoneNo
        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray array = obj.getJSONArray("country");

            ArrayList<String> list = new ArrayList<String>();
            for(int i=0; i<array.length(); i++) {
                JSONObject yeah = array.getJSONObject(i);
                list.add(yeah.getString("dial_code")+" "+yeah.getString("name")+"");
            }
            spinnerPhoneNoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            spinner_phoneNo.setTitle("Choose Dial Code");
            spinner_phoneNo.setAdapter(spinnerPhoneNoAdapter);
            spinner_phoneNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    spinner_phoneNo.setSelection(position);

                    if(spinner_phoneNo.getSelectedItem().toString().equals("+60 Malaysia")){
                        editText_phoneNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                    }else{
                        editText_phoneNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                    }


                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_fullName == null || editText_fullName.getText().toString().length() < 6){
                    editText_fullName.setError("Please Enter Correct Name");
                }else if(editText_icNo == null || editText_icNo.getText().toString().length() < 4){
                    editText_icNo.setError("Please Enter Correct IC Number");
                }else if(editText_dob == null){
                    editText_dob.setError("Please Enter Date of birth");
                }else if(editText_phoneNo == null || editText_phoneNo.getText().toString().length() < 5){
                    editText_phoneNo.setError("Please Enter Correct Phone Number");
                }else if(editText_doe == null ){
                    editText_doe.setError("Please Enter Date of expiry");
                }else{
                    startActivity(intent_next);
                }
            }
        });
    }

    @NonNull
    private TextWatcher ValidationBirthday() {
        return new TextWatcher() {
                private String current = "";
                private String ddmmyyyy = "DDMMYYYY";
                private Calendar cal = Calendar.getInstance();
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!s.toString().equals(current)) {
                        String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                        String cleanC = current.replaceAll("[^\\d.]|\\.", "");
                        int cl = clean.length();
                        int sel = cl;
                        for (int i = 2; i <= cl && i < 6; i += 2) {
                            sel++;
                        }
                        if (clean.equals(cleanC)) sel--;
                        if (clean.length() < 8){
                            clean = clean + ddmmyyyy.substring(clean.length());
                        }else{
                            int day  = Integer.parseInt(clean.substring(0,2));
                            int mon  = Integer.parseInt(clean.substring(2,4));
                            int year = Integer.parseInt(clean.substring(4,8));
                            mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                            cal.set(Calendar.MONTH, mon-1);
                            year = (year<1900)?1900:(year>2100)?2100:year;
                            cal.set(Calendar.YEAR, year);
                            day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                            clean = String.format("%02d%02d%02d",day, mon, year);
                        }
                        clean = String.format("%s-%s-%s", clean.substring(0, 2),
                                clean.substring(2, 4),
                                clean.substring(4, 8));
                        sel = sel < 0 ? 0 : sel;
                        current = clean;
                        editText_dob.setText(current);
                        editText_dob.setSelection(sel < current.length() ? sel : current.length());
                    }
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void afterTextChanged(Editable s) {}
            };
    }

    @NonNull
    private TextWatcher ValidationExpiry() {
        return new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");
                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;
                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));
                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }
                    clean = String.format("%s-%s-%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));
                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    editText_doe.setText(current);
                    editText_doe.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    private void ChangeFontType() {
        textView_userDetails.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_fullName.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_icNo.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_dob.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_phoneNo.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        editText_doe.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}

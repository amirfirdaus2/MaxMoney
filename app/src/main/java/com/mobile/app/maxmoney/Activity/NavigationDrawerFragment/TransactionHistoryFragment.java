package com.mobile.app.maxmoney.Activity.NavigationDrawerFragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.mobile.app.maxmoney.Activity.LoginActivity;
import com.mobile.app.maxmoney.Activity.MainActivity;
import com.mobile.app.maxmoney.Common.BasedURL;
import com.mobile.app.maxmoney.Model.LoginAPI;
import com.mobile.app.maxmoney.Model.OrderAPI;
import com.mobile.app.maxmoney.Utils.PagerAdapterTransactionHistory;

import com.mobile.app.maxmoney.Common.CustomTypefaceSpan;
import com.mobile.app.maxmoney.R;
import com.mobile.app.maxmoney.Utils.PreferenceManagerLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.volley.Request.Method.GET;
import static com.mobile.app.maxmoney.Common.BasedURL.ROOT_URL;
import static com.mobile.app.maxmoney.Common.BasedURL.ROOT_URL_API;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionHistoryFragment extends Fragment {
    TabLayout tabLayout;
    private PreferenceManagerLogin session;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set title
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "Avenir-Roman-12.ttf");
        SpannableStringBuilder SS = new SpannableStringBuilder("Transaction History");
        SS.setSpan (new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getActivity().setTitle(SS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_history, container, false);

        session = new PreferenceManagerLogin(getContext().getApplicationContext());

        tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Foreign Exchange"));
        tabLayout.addTab(tabLayout.newTab().setText("Money Transfer"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = v.findViewById(R.id.pager);
        final PagerAdapterTransactionHistory adapter = new PagerAdapterTransactionHistory(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {viewPager.setCurrentItem(tab.getPosition());}
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        goaa();
        return v;
    }

    private void goaa() {
        StringRequest stringRequest = new StringRequest(GET, BasedURL.ROOT_URL_API +"v1/orders/current",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj1 = new JSONObject(response);
                            getAll(obj1.getInt("total"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext().getApplicationContext(), "Please check Internet Connection !!", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> user = session.getUserDetails();
                String token = user.get(PreferenceManagerLogin.KEY_TOKEN);
                HashMap<String, String> headers = new HashMap();
                headers.put("api-key",token);
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void getAll(int value){
        StringRequest stringRequest = new StringRequest(GET, BasedURL.ROOT_URL_API +"v1/orders/current"+"?size="+value,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj1 = new JSONObject(response);
                            JSONArray array = obj1.getJSONArray("orders");

                            Toast.makeText(getActivity().getApplicationContext(),obj1.getString("end"),Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext().getApplicationContext(), "Please check Internet Connection !!", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> user = session.getUserDetails();
                String token = user.get(PreferenceManagerLogin.KEY_TOKEN);
                HashMap<String, String> headers = new HashMap();
                headers.put("api-key",token);
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        requestQueue.add(stringRequest);
    }
}

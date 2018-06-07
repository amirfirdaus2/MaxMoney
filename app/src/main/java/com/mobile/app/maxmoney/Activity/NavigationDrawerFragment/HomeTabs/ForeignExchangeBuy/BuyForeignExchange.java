package com.mobile.app.maxmoney.Activity.NavigationDrawerFragment.HomeTabs.ForeignExchangeBuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.mobile.app.maxmoney.Activity.MainActivity;
import com.mobile.app.maxmoney.R;

public class BuyForeignExchange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_foreign_exchange);
        this.setTitle("Foreign Exchange");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {}
}

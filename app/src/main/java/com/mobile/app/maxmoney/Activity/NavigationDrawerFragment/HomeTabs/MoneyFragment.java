package com.mobile.app.maxmoney.Activity.NavigationDrawerFragment.HomeTabs;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobile.app.maxmoney.Model.CountryFirstModel;
import com.mobile.app.maxmoney.Model.CountrySecondModel;
import com.mobile.app.maxmoney.R;
import com.mobile.app.maxmoney.Utils.SpinnerAdapter;
import com.mobile.app.maxmoney.Utils.SpinnerAdapter2;

import java.util.ArrayList;
import java.util.List;

public class MoneyFragment extends Fragment {
    Spinner spinner,spinner_2;
    private SpinnerAdapter spinnerAdapter;
    private SpinnerAdapter2 spinnerAdapter2;
    private List<CountryFirstModel> countryFirstModelList = new ArrayList<>();
    private List<CountrySecondModel> countrySecondModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_money, container, false);
        spinner = v.findViewById(R.id.spinner_1);
        spinner_2 = v.findViewById(R.id.spinner_2);
        spinnerAdapter = new SpinnerAdapter(getActivity(), countryFirstModelList);
        spinnerAdapter2 = new SpinnerAdapter2(getActivity(), countrySecondModelList);
        setSpinnerFirst();
        setSpinnerSecond();
        return v;
    }

    private void setSpinnerFirst() {
        countryFirstModelList.add(new CountryFirstModel(R.drawable.malaysiaflag, "MYR"));
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


    }

    private void setSpinnerSecond() {
        countrySecondModelList.add(new CountrySecondModel(R.drawable.usaflagg, "USD"));
        countrySecondModelList.add(new CountrySecondModel(R.drawable.thailandflag, "THB"));
        countrySecondModelList.add(new CountrySecondModel(R.drawable.singaporeflag, "SGD"));
        countrySecondModelList.add(new CountrySecondModel(R.drawable.indonesiaflag, "IDR"));
        countrySecondModelList.add(new CountrySecondModel(R.drawable.chinaflag, "CNY"));
        spinner_2.setAdapter(spinnerAdapter2);
        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


    }

}

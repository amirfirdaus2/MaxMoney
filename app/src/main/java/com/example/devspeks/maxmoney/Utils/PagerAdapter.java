package com.example.devspeks.maxmoney.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.devspeks.maxmoney.Activity.NavigationDrawerFragment.HomeTabs.ForeignFragment;
import com.example.devspeks.maxmoney.Activity.NavigationDrawerFragment.HomeTabs.MoneyFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ForeignFragment foreignFragment = new ForeignFragment();
                return foreignFragment;
            case 1:
                MoneyFragment moneyFragment = new MoneyFragment();
                return moneyFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
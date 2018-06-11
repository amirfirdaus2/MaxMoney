package com.mobile.app.maxmoney.Activity.NavigationDrawerFragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.app.maxmoney.Common.CustomTypefaceSpan;
import com.mobile.app.maxmoney.R;
import com.mobile.app.maxmoney.Utils.PagerAdapter;


public class HomeFragment extends Fragment {

    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "Avenir-Roman-12.ttf");
        SpannableStringBuilder SS = new SpannableStringBuilder("Home");
        SS.setSpan (new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getActivity().setTitle(SS);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        final int[] ICONS = new int[]{
                R.drawable.ic_foreign, R.drawable.ic_mtransfer
        };


        tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Foreign Exchange"));
        tabLayout.addTab(tabLayout.newTab().setText("Money Transfer"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setCustomFont();

        final ViewPager viewPager = v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
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
        return v;

    }

    private void setCustomFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Avenir-Roman-12.ttf"));
                    ((TextView) tabViewChild).setTextSize(8);
                }
            }
        }
    }

}

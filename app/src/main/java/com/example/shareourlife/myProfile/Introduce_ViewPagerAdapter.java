package com.example.shareourlife.myProfile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;

import com.example.shareourlife.Trans_FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Introduce_ViewPagerAdapter extends Trans_FragmentPagerAdapter {
    public static final int MAX_ELEVATION_FACTOR = 8;
    List<Introduce_Fragment> fragments = new ArrayList<>();
    private float mBaseElevation;

    public Introduce_ViewPagerAdapter(FragmentManager fm, ArrayList<Introduce_Fragment> fragments, float baseElevation) {
        super(fm);
        this.fragments = fragments;
        mBaseElevation = baseElevation;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }
}

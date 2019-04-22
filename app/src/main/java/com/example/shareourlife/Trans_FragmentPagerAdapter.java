package com.example.shareourlife;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.CardView;

public abstract class Trans_FragmentPagerAdapter extends FragmentPagerAdapter {
    public Trans_FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public abstract CardView getCardViewAt(int position);

    public abstract float getBaseElevation();
}

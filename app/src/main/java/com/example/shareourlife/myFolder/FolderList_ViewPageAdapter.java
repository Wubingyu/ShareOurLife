package com.example.shareourlife.myFolder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;

import com.example.shareourlife.Trans_FragmentPagerAdapter;

import java.util.ArrayList;

public class FolderList_ViewPageAdapter extends Trans_FragmentPagerAdapter {
    ArrayList<Folder_Fragment> fragments = new ArrayList<>();
    private float mBaseElevation;

    public FolderList_ViewPageAdapter(FragmentManager fm, ArrayList<Folder_Fragment> fragments, float baseElevation) {
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

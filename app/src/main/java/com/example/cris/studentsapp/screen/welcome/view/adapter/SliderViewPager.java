package com.example.cris.studentsapp.screen.welcome.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cris.studentsapp.screen.welcome.view.fragment.SliderFragment;


public class SliderViewPager extends FragmentStatePagerAdapter {

    private int mCount;

    public SliderViewPager(FragmentManager fm, int count) {
        super(fm);
        mCount = count;
    }

    @Override
    public Fragment getItem(int i) {
        return SliderFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return mCount;
    }
}

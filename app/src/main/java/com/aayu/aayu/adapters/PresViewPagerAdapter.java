package com.aayu.aayu.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aayu.aayu.Model.Prescriptions;
import com.aayu.aayu.fragments.PresViewFragment;

import java.util.List;

public class PresViewPagerAdapter extends FragmentPagerAdapter {

    private List<Prescriptions> list;
    public PresViewPagerAdapter(FragmentManager fm, List<Prescriptions> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return PresViewFragment.getInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

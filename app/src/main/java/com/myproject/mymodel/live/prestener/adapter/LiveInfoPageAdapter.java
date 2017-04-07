package com.myproject.mymodel.live.prestener.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myproject.mymodel.base.BaseFragment;

import java.util.List;

/**
 * Created by chen on 2017/3/25 10:17.
 * 作用:XXXX
 */

public class LiveInfoPageAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> baseFragments;

    private String[] titles = {"互动" , "排行榜" , "舰队"};
    public LiveInfoPageAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
        super(fm);
        this.baseFragments = baseFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return baseFragments.get(position);
    }

    @Override
    public int getCount() {
        return baseFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

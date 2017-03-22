package com.myproject.bilibili.model.found;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myproject.bilibili.base.BaseFragment;

import java.util.List;

/**
 * Created by chen on 2017/3/22 10:18.
 * 作用:XXXX
 */

public class TabMoreAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> fragments;
    private String[] titles = {"综合", "番剧(1)", "UP主(2)", "影视"};

    public TabMoreAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

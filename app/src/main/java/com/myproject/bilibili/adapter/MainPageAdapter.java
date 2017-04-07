package com.myproject.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myproject.mymodel.base.BaseFragment;

import java.util.List;

/**
 * Created by chen on 2017/3/21 19:14.
 * 作用:XXXX
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> list;
    private String[] titles = {"直播", "推荐", "追番", "分区", "发现"};

    public MainPageAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

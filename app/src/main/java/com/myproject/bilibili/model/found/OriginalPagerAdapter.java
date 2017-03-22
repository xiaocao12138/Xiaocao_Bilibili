package com.myproject.bilibili.model.found;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myproject.bilibili.base.BaseFragment;

import java.util.List;

/**
 * Created by chen on 2017/3/21 17:14.
 * 作用:XXXX
 */

public class OriginalPagerAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> list;
    private String[] titles = new String[] { "原创", "全站", "番剧" };


    public OriginalPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
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

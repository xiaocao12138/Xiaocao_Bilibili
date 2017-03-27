package com.myproject.bilibili.video;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myproject.bilibili.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by chen on 2017/3/27 9:41.
 * 作用:XXXX
 */

public class DanMuAdapter extends FragmentPagerAdapter {


    private final ArrayList<BaseFragment> list;
    private String[] titles = {"互动" , "排行榜" , "舰队"};

    public DanMuAdapter(FragmentManager fm, ArrayList<BaseFragment> baseFragments) {
        super(fm);
        this.list = baseFragments;
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

package com.myproject.bilibili.model.found;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myproject.bilibili.base.BaseFragment;

import java.util.List;

import static android.R.id.list;

/**
 * Created by chen on 2017/3/21 20:17.
 * 作用:XXXX
 */

public class XingquPageAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> baseFragments;
    private String[] titles = {"首页", "发现", "我的" };

    public XingquPageAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
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

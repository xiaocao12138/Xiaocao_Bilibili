package com.myproject.mymodel.recommend.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myproject.mymodel.base.BaseFragment;

import java.util.List;

/**
 * Created by chen on 2017/3/21 21:01.
 * 作用:XXXX
 */

public class RecommendPageAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> list;
    private String[] titles = { "综合" , "动态" };

    public RecommendPageAdapter(FragmentManager childFragmentManager, List<BaseFragment> baseFragments) {
        super(childFragmentManager);
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

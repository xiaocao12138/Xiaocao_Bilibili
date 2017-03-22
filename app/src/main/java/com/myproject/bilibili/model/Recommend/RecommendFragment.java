package com.myproject.bilibili.model.Recommend;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/21 19:13.
 * 作用:XXXX
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> baseFragments;
    private RecommendPageAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_recommend, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        initFramgents();
        initListener();
    }

    private void initListener() {
        adapter = new RecommendPageAdapter(getChildFragmentManager(), baseFragments);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initFramgents() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new ZongheFragment());
        baseFragments.add(new DongtaiFragment());
    }
}

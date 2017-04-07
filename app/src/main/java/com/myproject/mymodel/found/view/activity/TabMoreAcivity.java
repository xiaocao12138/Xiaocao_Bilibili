package com.myproject.mymodel.found.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.myproject.bilibili.R;
import com.myproject.mymodel.found.view.fragment.FoundFragment;
import com.myproject.mymodel.found.view.fragment.MediaFragment;
import com.myproject.mymodel.found.view.fragment.zongF3ragment;
import com.myproject.mymodel.base.BaseFragment;
import com.myproject.mymodel.found.presenter.adapter.TabMoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabMoreAcivity extends AppCompatActivity {


    @BindView(R.id.edt_more)
    EditText edtMore;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private String name;

    private List<BaseFragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_more);
        ButterKnife.bind(this);

        getData();
        initFragment();
        initData();
    }

    private void initData() {
        TabMoreAdapter adapter = new TabMoreAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new zongF3ragment());//添加主页Fragment-0，
        fragments.add(new MediaFragment());//添加分类Fragment--1
        fragments.add(new MediaFragment());//添加发现Fragment--2
        fragments.add(new MediaFragment());//添加购物车Fragment
    }

    private void getData() {
        name = getIntent().getStringExtra(FoundFragment.NAME);
        edtMore.setText(name);
    }
}

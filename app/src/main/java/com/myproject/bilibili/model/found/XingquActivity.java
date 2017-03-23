package com.myproject.bilibili.model.found;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.found.fragment.FirstFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XingquActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_back)
    TextView tvBack;
    private List<BaseFragment> baseFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xingqu);
        ButterKnife.bind(this);

        initFramgent();
        initListener();
    }

    private void initListener() {
        XingquPageAdapter mainAdapter = new XingquPageAdapter(getSupportFragmentManager(), baseFragments);
        viewPager.setAdapter(mainAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initFramgent() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new FirstFragment());
        baseFragments.add(new FirstFragment());
        baseFragments.add(new FirstFragment());
    }

    @OnClick(R.id.tv_back)
    public void onClick() {
        finish();
    }
}

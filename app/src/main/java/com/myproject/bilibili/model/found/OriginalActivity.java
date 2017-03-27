package com.myproject.bilibili.model.found;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OriginalActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original);
        ButterKnife.bind(this);


        toolbar.setTitle("排行榜");
        toolbar.setTitleTextColor(Color.WHITE);

        initFragment();
        initData();
    }

    private void initFragment() {
        list = new ArrayList<>();
        list.add(new OriginalFragment());
        list.add(new OriginalFragment());
        list.add(new OriginalFragment());
    }

    private void initData() {

        OriginalPagerAdapter adapter = new OriginalPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }



    private int startY;
    private int startX;
    private boolean isScrollY;
    private boolean isFirst;
    private boolean isOpen = true;
    //tollBar 回弹效果
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int eventY = (int) ev.getY();
        int eventX = (int) ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = eventY;
                startX = eventX;
                isFirst = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isFirst) {
                    if (Math.abs(eventX - startX) > Math.abs(eventY - startY) && Math.abs(eventX - startX) > toolbar.getHeight()*0.30) {
                        isScrollY = false;
                        isFirst = false;
                        appbarLayout.setExpanded(isOpen);
                    } else if (Math.abs(eventY - startY) > Math.abs(eventX - startX) && Math.abs(eventY - startY) > toolbar.getHeight()*0.30) {
                        isScrollY = true;
                        isFirst = false;
                    }
                }
                if (isOpen) {
                    if (startY < eventY) {
                        startY = eventY;
                    }
                } else {
                    if (startY > eventY) {
                        startY = eventY;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isScrollY) {
                    if (isOpen) {
                        if (startY - eventY > toolbar.getHeight() * 0.36) {
                            appbarLayout.setExpanded(false);
                            isOpen = false;
                        } else {
                            appbarLayout.setExpanded(true);
                            isOpen = true;
                        }
                    } else {
                        if (eventY - startY > toolbar.getHeight() * 0.36) {
                            appbarLayout.setExpanded(true);
                            isOpen = true;
                        } else {
                            appbarLayout.setExpanded(false);
                            isOpen = false;
                        }
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}

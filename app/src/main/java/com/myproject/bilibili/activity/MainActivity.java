package com.myproject.bilibili.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.myproject.bilibili.R;
import com.myproject.bilibili.adapter.MainPageAdapter;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.Cartoon.fragment.CartoonFragment;
import com.myproject.bilibili.model.Partition.PartitionFragment;
import com.myproject.bilibili.model.Recommend.RecommendFragment;
import com.myproject.bilibili.model.found.FoundFragment;
import com.myproject.bilibili.model.live.LiveFragment;
import com.myproject.bilibili.search.SearchFragment;
import com.myproject.bilibili.search.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.navigation_layout)
    LinearLayout navigationLayout;

    private List<BaseFragment> baseFragments;
    private SearchFragment searchFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchFragment = SearchFragment.newInstance();
        toolbar.inflateMenu(R.menu.menu_main);
        initFramgent();
        initListener();

    }

    private void initListener() {
        MainPageAdapter mainAdapter = new MainPageAdapter(getSupportFragmentManager(), baseFragments);
        viewPager.setAdapter(mainAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });



        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();

                if (menuItemId == R.id.id_action_download) {
                    Toast.makeText(MainActivity.this, "下载", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, SearchActivity.class).putExtra("search", ""));

                } else if (menuItemId == R.id.id_action_search) {
//                    Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, TabMoreAcivity.class));
                    searchFragment.show(getSupportFragmentManager(),SearchFragment.TAG);
                }
                return true;
            }
        });

        navigationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initFramgent() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new LiveFragment());
        baseFragments.add(new RecommendFragment());
        baseFragments.add(new CartoonFragment());
        baseFragments.add(new PartitionFragment());
        baseFragments.add(new FoundFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_home:

                drawerLayout.closeDrawers();
                break;
            case R.id.item_vip:
                break;
            case R.id.item_download:
                break;
            case R.id.item_favourite:
                break;
            case R.id.item_history:
                break;
            case R.id.item_guanzhu:
                break;
            case R.id.item_tracker:
                break;
            case R.id.item_theme:
                break;
            case R.id.item_app_recommend:
                break;
            case R.id.item_settings:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
                    if (Math.abs(eventX - startX) > Math.abs(eventY - startY) && Math.abs(eventX - startX) > toolbar.getHeight() * 0.30) {
                        isScrollY = false;
                        isFirst = false;
                        appBar.setExpanded(isOpen);
                    } else if (Math.abs(eventY - startY) > Math.abs(eventX - startX) && Math.abs(eventY - startY) > toolbar.getHeight() * 0.30) {
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
                            appBar.setExpanded(false);
                            isOpen = false;
                        } else {
                            appBar.setExpanded(true);
                            isOpen = true;
                        }
                    } else {
                        if (eventY - startY > toolbar.getHeight() * 0.36) {
                            appBar.setExpanded(true);
                            isOpen = true;
                        } else {
                            appBar.setExpanded(false);
                            isOpen = false;
                        }
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 双击退出
     *
     * @param keyCode
     * @param event
     * @return
     */

    //当前时间
    private long currentTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                currentTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}

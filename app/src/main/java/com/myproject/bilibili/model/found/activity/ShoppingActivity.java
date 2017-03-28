package com.myproject.bilibili.model.found.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.shopping.bean.ShopBannerBean;
import com.myproject.bilibili.model.shopping.fragment.DingDanFragment;
import com.myproject.bilibili.model.shopping.fragment.ShopCartFragment;
import com.myproject.bilibili.model.shopping.fragment.ShopHomeFragment;
import com.myproject.bilibili.utils.ClipboardUtil;
import com.myproject.bilibili.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShoppingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fl_shop)
    FrameLayout flShop;
    @BindView(R.id.rg_shop)
    RadioGroup rgShop;
    private String url;
    private List<ShopBannerBean.ResultBean.ModelDetailsBean> modelDetails;

    /**
     * 装5个Fragment的集合
     */
    private List<BaseFragment> fragments;
    /**
     * Fragment对应的位置
     */
    private int position;

    /**
     * 刚才被显示的fragment
     */
    private Fragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        ButterKnife.bind(this);

        url = "http://bmall.bilibili.com/";
        initToolbar();
        initFragment();
        initListener();
    }


    private void initListener() {

        rgShop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_shop_cart:
                        position = 1;
                        break;
                    case R.id.rb_ding_dan:
                        position = 2;
                        break;
                }

                //根据位置切换到对应的Fragment
                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });

        //默认选中首页--注意默认选中要放在后边
        rgShop.check(R.id.rb_home);
    }

    private void switchFragment(Fragment currentFragment) {
        //切换的不是同一个页面
        if (tempFragment != currentFragment) {

            //得到FragmentMager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if (!currentFragment.isAdded()) {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }

                //添加
                ft.add(R.id.fl_shop, currentFragment);

            } else {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }

                //显示
                ft.show(currentFragment);
            }

            //事务提交
            ft.commit();

            //把当前的赋值成缓存的
            tempFragment = currentFragment;
        }

    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new ShopHomeFragment());//商品首页-0，
        fragments.add(new ShopCartFragment());//添加分类Fragment--1
        fragments.add(new DingDanFragment());//添加发现Fragment--2
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int checkedid = intent.getIntExtra(Constants.CHECKEDID , R.id.rb_home);
        rgShop.check(checkedid);
    }


    private void initToolbar() {
        toolbar.setTitle("bilibili- 周边商城");
        toolbar.inflateMenu(R.menu.banner);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.banner_info_more, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
//                showShare();
                share();
                break;
            case R.id.action_web:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.action_copy:
                ClipboardUtil.setText(ShoppingActivity.this, url);
                Toast.makeText(ShoppingActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("来自哔哩哔哩");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("https://www.baidu.com/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("来自哔哩哔哩");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://www.baidu.com/");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("哔哩哔哩");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("哔哩哔哩b");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://www.baidu.com/");

        // 启动分享GUI
        oks.show(this);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + url);
        startActivity(Intent.createChooser(intent, ""));
    }

}

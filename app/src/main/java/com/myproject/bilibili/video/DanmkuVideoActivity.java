package com.myproject.bilibili.video;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.live.GridAdapter;
import com.myproject.bilibili.model.live.fragment.HuDongFragment;
import com.myproject.bilibili.view.CircleImageView;
import com.shuyu.gsyvideoplayer.GSYPreViewManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by guoshuyu on 2017/2/19.
 * 弹幕
 */


public class DanmkuVideoActivity extends AppCompatActivity {


    @BindView(R.id.danmaku_player)
    DanmakuVideoPlayer danmakuPlayer;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_home_name)
    TextView tvHomeName;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.tv_guan_zhu)
    TextView tvGuanZhu;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.iv_send_liwu)
    ImageView ivSendLiwu;
    @BindView(R.id.edt_input_dm)
    EditText edtInputDm;
    @BindView(R.id.iv_send_dm)
    ImageView ivSendDm;
    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;
    private String url;
    private String title;
    private int online;
    private ArrayList<BaseFragment> baseFragments;
    private String imageUrl;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmaku_layout);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra(GridAdapter.URL);
        title = getIntent().getStringExtra(GridAdapter.TITLE);
        online = getIntent().getIntExtra(GridAdapter.ONLINE, 0);
        imageUrl = getIntent().getStringExtra(GridAdapter.IMAGE_URL);
        username = getIntent().getStringExtra(GridAdapter.USERNAME);

        initFramgent();
        initData();
        //使用自定义的全屏切换图片，!!!注意xml布局中也需要设置为一样的
        //必须在setUp之前设置
        danmakuPlayer.setShrinkImageRes(R.drawable.custom_shrink);
        danmakuPlayer.setEnlargeImageRes(R.drawable.custom_enlarge);

//        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        //String url = "https://res.exexm.com/cw_145225549855002";
        danmakuPlayer.setUp(url, true, null, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        danmakuPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, danmakuPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        danmakuPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        danmakuPlayer.setRotateViewAuto(false);
        danmakuPlayer.setLockLand(false);
        danmakuPlayer.setShowFullAnimation(false);
        danmakuPlayer.setNeedLockFull(true);

        //detailPlayer.setOpenPreView(true);
        danmakuPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                danmakuPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
            }
        });

        danmakuPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        danmakuPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

    }

    private void initFramgent() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new HuDongFragment());
        baseFragments.add(new HuDongFragment());
        baseFragments.add(new HuDongFragment());
    }

    private void initData() {

        tvOnline.setText(String.valueOf(online));
        tvHomeName.setText(title);
        tvUserName.setText(username);
        Glide.with(this).load(imageUrl).crossFade().into(ivHead);

        DanMuAdapter adapter = new DanMuAdapter(getSupportFragmentManager(), baseFragments);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!danmakuPlayer.isIfCurrentIsFullscreen()) {
                    danmakuPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (danmakuPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }


    private void resolveNormalVideoUI() {
        //增加title
        danmakuPlayer.getTitleTextView().setVisibility(View.GONE);
        danmakuPlayer.getTitleTextView().setText("测试视频");
        danmakuPlayer.getBackButton().setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_send_liwu, R.id.edt_input_dm, R.id.iv_send_dm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_send_liwu:
                Toast.makeText(this, "礼物", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edt_input_dm:
                Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_send_dm:
                Toast.makeText(this, "发送弹幕", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
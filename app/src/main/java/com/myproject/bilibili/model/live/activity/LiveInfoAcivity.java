package com.myproject.bilibili.model.live.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.live.adapter.LiveInfoPageAdapter;
import com.myproject.bilibili.model.live.fragment.HuDongFragment;
import com.myproject.bilibili.utils.Constants;
import com.myproject.bilibili.view.CircleImageView;
import com.myproject.bilibili.widget.media.IjkVideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LiveInfoAcivity extends AppCompatActivity {

    @BindView(R.id.ijk_player)
    IjkVideoView ijkPlayer;
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
    @BindView(R.id.hud_view)
    TableLayout hudView;
    @BindView(R.id.ib_start)
    ImageButton ibStart;
    private List<BaseFragment> baseFragments;
    private String url;
    private String title;
    private String imageUrl;
    private int online;
    private String username;
    private boolean mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_info);
        ButterKnife.bind(this);

        getData();
        initFramgent();
        iniTabLayout();
    }

    private void setData() {

        ijkPlayer.setHudView(hudView);
        Uri mVideoUri = Uri.parse(url);
        if (ijkPlayer != null) {
            ijkPlayer.setVideoPath(url);

        } else if (ijkPlayer != null) {
            ijkPlayer.setVideoURI(mVideoUri);
        } else {
            finish();
            return;
        }
        ijkPlayer.start();

        tvHomeName.setText(title);
        tvUserName.setText(username);
        tvOnline.setText(String.valueOf(online));
        Glide.with(this).load(imageUrl).crossFade().into(ivHead);

    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !ijkPlayer.isBackgroundPlayEnabled()) {
            ijkPlayer.stopPlayback();
            ijkPlayer.release(true);
            ijkPlayer.stopBackgroundPlay();
        } else {
            ijkPlayer.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(Constants.URL);
            title = intent.getStringExtra(Constants.TITLE);
            imageUrl = intent.getStringExtra(Constants.IMAGE_URL);
            online = intent.getIntExtra(Constants.ONLINE, 0);
            username = intent.getStringExtra(Constants.USERNAME);
        }
    }

    private void iniTabLayout() {
        LiveInfoPageAdapter adapter = new LiveInfoPageAdapter(getSupportFragmentManager(), baseFragments);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initFramgent() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new HuDongFragment());
        baseFragments.add(new HuDongFragment());
        baseFragments.add(new HuDongFragment());
    }

    @OnClick({R.id.tv_guan_zhu, R.id.iv_send_liwu,
            R.id.edt_input_dm, R.id.iv_send_dm ,
            R.id.ib_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_guan_zhu:
                tvGuanZhu.setTextColor(Color.GRAY);
                tvGuanZhu.setClickable(false);
                tvOnline.setText(String.valueOf(online + 1));
                break;
            case R.id.iv_send_liwu:
                Toast.makeText(this, "礼物", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edt_input_dm:
                Toast.makeText(this, "输入弹幕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_send_dm:
                Toast.makeText(this, "发送弹幕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_start:
                setData();
                ibStart.setVisibility(View.GONE);
                break;
        }
    }

}

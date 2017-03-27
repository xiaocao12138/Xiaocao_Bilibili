package com.myproject.bilibili.model.live.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.live.adapter.LiveInfoPageAdapter;
import com.myproject.bilibili.model.live.fragment.HuDongFragment;
import com.myproject.bilibili.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveInfoAcivity extends AppCompatActivity {

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_home_name)
    TextView tvHomeName;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_shou_yi)
    TextView tvShouYi;
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
    private List<BaseFragment> baseFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_info);
        ButterKnife.bind(this);


        initFramgent();
        initListener();
    }

    private void initListener() {
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

    @OnClick({R.id.tv_guan_zhu, R.id.iv_send_liwu, R.id.edt_input_dm, R.id.iv_send_dm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_guan_zhu:
                break;
            case R.id.iv_send_liwu:
                break;
            case R.id.edt_input_dm:
                break;
            case R.id.iv_send_dm:
                break;
        }
    }
}

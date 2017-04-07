package com.myproject.bilibili.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.myproject.bilibili.R;
import com.myproject.mymodel.utils.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveCenterActivity extends AppCompatActivity {

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.center_user_name)
    TextView centerUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_center);
        ButterKnife.bind(this);

    }
}

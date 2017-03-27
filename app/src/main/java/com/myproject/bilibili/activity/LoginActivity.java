package com.myproject.bilibili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.myproject.bilibili.R;
import com.myproject.bilibili.utils.CommonUtil;
import com.myproject.bilibili.utils.ConstantUtil;
import com.myproject.bilibili.utils.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_icon_left)
    ImageView ivIconLeft;
    @BindView(R.id.iv_icon_centre)
    ImageView ivIconCentre;
    @BindView(R.id.iv_icon_right)
    ImageView ivIconRight;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.delete_username)
    ImageButton deleteUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String Username;
    private String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_cancle);
        toolbar.setTitle("登录");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initListener() {

        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && Username.length() > 0) {
                    deleteUsername.setVisibility(View.VISIBLE);
                } else {
                    deleteUsername.setVisibility(View.GONE);
                }

                ivIconLeft.setImageResource(R.drawable.ic_22);
                ivIconRight.setImageResource(R.drawable.ic_33);
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ivIconLeft.setImageResource(R.drawable.ic_22_hide);
                ivIconRight.setImageResource(R.drawable.ic_33_hide);
            }
        });

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                etPassword.setText("");
                if (s.length() > 0) {
                    // 如果用户名有内容时候 显示删除按钮
                    deleteUsername.setVisibility(View.VISIBLE);
                } else {
                    // 如果用户名有内容时候 显示删除按钮
                    deleteUsername.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void initData() {
        Username = etUsername.getText().toString().trim();
        Password = etPassword.getText().toString().trim();

    }

    @OnClick({R.id.delete_username, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_username:

                etUsername.setText("");
                etPassword.setText("");
                deleteUsername.setVisibility(View.GONE);
                etUsername.setFocusable(true);
                etUsername.setFocusableInTouchMode(true);
                etUsername.requestFocus();
                break;

            case R.id.btn_login:
                boolean isNetConnected = CommonUtil.isNetworkAvailable(this);
                if (!isNetConnected) {
                    Toast.makeText(this, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                login();
                break;
        }
    }

    private void login() {

        Username = etUsername.getText().toString().trim();
        Password = etPassword.getText().toString().trim();
        
        if (TextUtils.isEmpty(Username)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        PreferenceUtil.putBoolean(ConstantUtil.KEY, true);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

}

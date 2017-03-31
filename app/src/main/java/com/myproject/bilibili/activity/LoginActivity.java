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
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.bilibili.MyApplication;
import com.myproject.bilibili.R;
import com.myproject.bilibili.utils.CommonUtil;
import com.myproject.bilibili.utils.Constants;
import com.myproject.bilibili.utils.PreferenceUtil;
import com.myproject.bilibili.utils.greendao.User;
import com.myproject.bilibili.utils.greendao.UserDao;

import java.util.List;

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
    @BindView(R.id.btn_zhuce)
    Button btnZhuce;
    @BindView(R.id.other_login)
    TextView otherLogin;
    private String Username;
    private String Password;
    private List<User> users;

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

    public UserDao getUserDao() {
        return MyApplication.getInstance().getDaoSession().getUserDao();
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

    @OnClick({R.id.delete_username, R.id.btn_zhuce, R.id.btn_login , R.id.other_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_username:
                delete();
                break;
            case R.id.btn_zhuce:
                register();
                break;
            case R.id.btn_login:

                login();
                break;
            case R.id.other_login:
                startActivity(new Intent(LoginActivity.this , FaceLoginActivity.class));
                break;
        }
    }

    //删除
    private void delete(){
        etUsername.setText("");
        etPassword.setText("");
        deleteUsername.setVisibility(View.GONE);
        etUsername.setFocusable(true);
        etUsername.setFocusableInTouchMode(true);
        etUsername.requestFocus();
    }

    //注册
    private void register(){
        if (!TextUtils.isEmpty(Username) || !TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        users = getUserDao().loadAll();
        String userNames = etUsername.getText().toString().trim();
        if (this.users.size() == 0) {
            User user = new User(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
            getUserDao().insert(user);
            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < this.users.size(); i++) {
                if (userNames.equals(this.users.get(i).getUsername().toString())) {
                    Toast.makeText(LoginActivity.this, "该帐号已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            User user = new User(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
            getUserDao().insert(user);
            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        }
    }

    //登录
    private void login() {
        boolean isNetConnected = CommonUtil.isNetworkAvailable(this);
        if (!isNetConnected) {
            Toast.makeText(this, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Username = etUsername.getText().toString().trim();
            Password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(Username) || TextUtils.isEmpty(Password)) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            List<User> user = getUserDao().loadAll();
            for (User list : user) {
                String userName = list.getUsername();
                String passWord = list.getPassword();
                if (Username.equals(userName) && Password.equals(passWord)) {
                    PreferenceUtil.putBoolean(Constants.KEY, true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(Constants.NAME, Username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账户或密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

}

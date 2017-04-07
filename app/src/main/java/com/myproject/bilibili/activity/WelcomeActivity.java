package com.myproject.bilibili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.myproject.bilibili.R;
import com.myproject.mymodel.utils.ConstantUtil;
import com.myproject.mymodel.utils.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        setAnimation();
    }

    private void setAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0 , 1);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent ;
                boolean isLogin = PreferenceUtil.getBoolean(ConstantUtil.KEY, false);
                if (isLogin) {

                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                } else {

                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                }

                startActivity(intent);

                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rlWelcome.setAnimation(animation);
    }
}

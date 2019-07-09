package com.example.zhbj52;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {
    RelativeLayout rlRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot=(RelativeLayout) findViewById(R.id.layout1);
        startAnim();

    }
    private  void startAnim(){
        //动画集合
        AnimationSet set=new AnimationSet(false);
        RotateAnimation rotate=new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(1000);
        rotate.setFillAfter(true);

        ScaleAnimation scale=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(1000);
        rotate.setFillAfter(true);


        AlphaAnimation alpha=new AlphaAnimation(0,1);
        alpha.setDuration(1000);
        alpha.setFillAfter(true);

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,guideActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlRoot.startAnimation(set);

    }

}

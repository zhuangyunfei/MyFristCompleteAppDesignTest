package com.example.myfristcompleteappdesigntest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private long exitTime = 0;
    private TextView tvRegister;
    private TextView tvLogin;
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initAnims();
    }
    /**
     *User: langyazyf
     *Data: 2018/1/29
     *Time:15:04
     *Description:初始化控件
     */
    private void initViews(){
        tvRegister = findViewById(R.id.tv_register);
        tvLogin = findViewById(R.id.tv_login);
        ivLogo = findViewById(R.id.iv_logo);
    }
    /**
     *User: langyazyf
     *Data: 2018/1/29
     *Time:15:04
     *Description:初始化Logo图片以及底部注册、登录按钮的动画
     * 在安卓坐标系中，默认坐标原点为左上角（0,0），沿X轴右方向为正，沿Y轴下方向为正
     * 动画属性中一般只有透明度（alpha）、旋转度数(rotation)、平移(translation)、缩放(scale)四种
     * 以下用了两种平移和透明度
     */
    private void initAnims(){
        /**
         *User: langyazyf
         *Data: 2018/1/29
         *Time:15:33
         *Description:第一步 控件平移
         */
        //以控件自身所在的位置为原点，从下方距离原点200像素的位置移动到原点
        //将登录控件在Y方向上向下平移200个像素
        ObjectAnimator translationLogin = ObjectAnimator.ofFloat(tvLogin,
                "translationY",200,0);
        //将注册控件在Y方向上向下平移200个像素
        ObjectAnimator translationRester = ObjectAnimator.ofFloat(tvRegister,
                "translationY",200,0);
        /**
         *User: langyazyf
         *Data: 2018/1/29
         *Time:15:33
         *Description:第二步 控件的透明度
         */
        //登录控件透明度动画
        ObjectAnimator alphaLogin = ObjectAnimator.ofFloat(tvLogin,
                "alpha",0,1);
        //注册控件透明度动画
        ObjectAnimator alphaRegister = ObjectAnimator.ofFloat(tvRegister,
                "alpha",0,1);
        /**
         *User: langyazyf
         *Data: 2018/1/29
         *Time:15:37
         *Description:第三步 动画属性准备好之后，设置动画
         */
        final AnimatorSet bottomAnim = new AnimatorSet();
        bottomAnim.setDuration(1000);//动画执行5S
        //同时播放动画
        bottomAnim.play(translationLogin).with(translationRester)
                .with(alphaLogin).with(alphaRegister);

        /**
         *User: langyazyf
         *Data: 2018/1/29
         *Time:15:44
         *Description:获取屏幕高度
         */
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //获取到物理设备的高度 以像素为单位
        int screenHeight = metrics.heightPixels;
        //通过测量获取LOGO的参数
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        ivLogo.measure(w,h);
        int logoHeight = ivLogo.getMeasuredHeight();
        /**
         *User: langyazyf
         *Data: 2018/1/29
         *Time:16:30
         *Description:控件参数获取到后，对其进行动画控制
         */
        //初始化ivLogo控件的移动和缩放动画
        float transY = (screenHeight-logoHeight)*0.28f;
        //控制ivLogo控件向上移动
        ObjectAnimator ivLogotranslation = ObjectAnimator.ofFloat(ivLogo,
                "translationY",0,-transY);
        //在X轴和Y轴上进行缩放0.75倍
        ObjectAnimator ivLogoScaleX = ObjectAnimator.ofFloat(ivLogo,
                "scaleX",1f,0.75f);
        ObjectAnimator ivLogoScaleY = ObjectAnimator.ofFloat(ivLogo,
                "scaleY",1f,0.75f);
        AnimatorSet ivLogoanimSet = new AnimatorSet();
        ivLogoanimSet.setDuration(1000);//五秒内完成动作
        ivLogoanimSet.play(ivLogotranslation).with(ivLogoScaleX).with(ivLogoScaleY);
        ivLogoanimSet.start();
        //添加监听事件
        ivLogoanimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //等待ivLogo结束后，播放两个TextView的动画
                bottomAnim.start();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
    }

    /**
     *User: langyazyf
     *Data: 2018/1/29
     *Time:16:53
     *Description:重写返回键，实现双击返回
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - exitTime >2000){
                Toast.makeText(LoginActivity.this,
                        "再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                LoginActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

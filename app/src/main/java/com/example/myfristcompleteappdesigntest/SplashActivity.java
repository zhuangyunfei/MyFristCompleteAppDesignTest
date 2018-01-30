package com.example.myfristcompleteappdesigntest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
    //设定一个两秒的定时器，延时启动Runnable线程对象
    //此两秒是指启动页面展示两秒后，在进行跳转登录
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoLogin();
            }
        }, 10000);
    }
    /**
     *User: langyazyf
     *Data: 2018/1/29
     *Time:9:02
     *Description:跳转登录业务
     */
    private void gotoLogin() {
        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        //取消界面跳转时的动画，保证Logo与登录界面想衔接
        overridePendingTransition(0,0);
    }
    /**
     *User: langyazyf
     *Data: 2018/1/29
     *Time:13:46
     *Description:屏蔽物理返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            //返回true就表示onKeyDown被屏蔽了
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
    /**
     *User: langyazyf
     *Data: 2018/1/29
     *Time:14:27
     *Description:清楚线程的消息，防止内存泄露
     */
    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }
}

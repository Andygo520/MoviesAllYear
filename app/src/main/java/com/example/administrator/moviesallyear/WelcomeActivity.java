package com.example.administrator.moviesallyear;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.AppExit;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 999) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        AppExit.getInstance().addActivity(this);

        int[] welcome_images={R.mipmap.welcome_1,R.mipmap.welcome_2,R.mipmap.welcome_3};
//      将[0,3)的任意整数赋值给i
        int i=new Random().nextInt(welcome_images.length);
        image.setImageResource(welcome_images[i]);
//        一秒后发送消息，跳转到主界面
        handler.sendEmptyMessageDelayed(999, 1000);
    }
}

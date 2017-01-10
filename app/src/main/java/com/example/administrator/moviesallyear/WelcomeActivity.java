package com.example.administrator.moviesallyear;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==999){
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        一秒后发送消息，跳转到主界面
        handler.sendEmptyMessageDelayed(999,1000);
    }
}

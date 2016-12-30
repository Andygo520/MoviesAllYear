package com.example.administrator.moviesallyear;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.ACache;

public class CriticsActivity extends AppCompatActivity {
   private Context context;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_save_critics)
    Button btnSaveCritics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critics);
        ButterKnife.bind(this);

        context=CriticsActivity.this;
    }

    @OnClick(R.id.btn_save_critics)
    public void onClick() {
        String name=etName.getText().toString().trim();
        String content=etContent.getText().toString().trim();
        if (name.equalsIgnoreCase("")){
            Toast.makeText(context,"请输入标题",Toast.LENGTH_SHORT).show();
            return;
        }
        if (content.equalsIgnoreCase("")){
            Toast.makeText(context,"请输入内容",Toast.LENGTH_SHORT).show();
            return;
        }
        ACache.get(context).put("name",name);
        ACache.get(context).put("content",content,(int)System.currentTimeMillis());
        Intent intent=new Intent(context,MainActivity.class);
        startActivity(intent);
    }
}

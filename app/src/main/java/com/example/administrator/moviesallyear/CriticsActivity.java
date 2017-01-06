package com.example.administrator.moviesallyear;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greendao.gen.MovieCriticsDao;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.MovieCritics;

public class CriticsActivity extends AppCompatActivity {
    private Context context;
    private MovieCriticsDao criticsDao;
    private int flag;// 影评页跳转过来就可以修改数据，区别于从bmb按钮跳转而来
    private long id; // 修改数据的时候主键id不变

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

        context = CriticsActivity.this;
//       获得MovieCriticsDao对象
        criticsDao = MyApplication.getInstances().getDaoSession().getMovieCriticsDao();
        flag = getIntent().getIntExtra("Flag", 0);
        if (flag == 999) {
            id = getIntent().getLongExtra("id", (long) -1);
            MovieCritics critics = criticsDao.load(id);
            etName.setText(critics.getName());
            etContent.setText(critics.getCritics());
        }

    }

    @OnClick(R.id.btn_save_critics)
    public void onClick() {
        String name = etName.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        if (name.equalsIgnoreCase("")) {
            Toast.makeText(context, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (content.equalsIgnoreCase("")) {
            Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }

//      得到创建影评的时间
        long createTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date(createTime));

//       flag为999代表更新数据，否则就为插入新数据
        if (flag == 999)
            criticsDao.update(new MovieCritics(id, name, content, time));
        else
            criticsDao.insert(new MovieCritics(null, name, content, time));
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

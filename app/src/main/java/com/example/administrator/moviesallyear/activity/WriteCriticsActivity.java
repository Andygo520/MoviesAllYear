package com.example.administrator.moviesallyear.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AppExit;
import com.MoviesAllYearApplication;
import com.example.administrator.moviesallyear.R;
import com.greendao.gen.MovieCriticsDao;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.MovieCritics;

public class WriteCriticsActivity extends AppCompatActivity {
    private Context context;
    private MovieCriticsDao criticsDao;
    private int flag;// 标志位，用来判断是修改影评还是新增
    private long id; // 修改数据的时候主键id不变

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_save_critics)
    Button btnSaveCritics;
    @BindView(R.id.ratingBar)
    SimpleRatingBar ratingBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critics);
        ButterKnife.bind(this);

        ivEdit.setVisibility(View.GONE);
        tvTitle.setText("写影评");
        context = WriteCriticsActivity.this;
//       获得MovieCriticsDao对象
        criticsDao = MoviesAllYearApplication.getInstances().getDaoSession().getMovieCriticsDao();
        flag = getIntent().getIntExtra("Flag", 0);
//        修改影评
        if (flag == 999) {
            id = getIntent().getLongExtra("id", (long) -1);
            MovieCritics critics = criticsDao.load(id);
            etName.setText(critics.getName());
            etContent.setText(critics.getCritics());
            ratingBar.setRating(critics.getStars());
        }
//        从MovieDetailActivity跳转而来
        else if (flag == -999) {
            String title = getIntent().getStringExtra("Title");
            etName.setText(title);
        }

    }


    @OnClick({R.id.iv_back, R.id.btn_save_critics})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_save_critics:
                String name = etName.getText().toString().trim();
                String content = etContent.getText().toString().trim();
//        获取用户的评分
                int starNum = (int) ratingBar.getRating();

                if (name.equalsIgnoreCase("")) {
                    Toast.makeText(context, "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (content.equalsIgnoreCase("")) {
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (starNum == 0) {
                    Toast.makeText(context, "一颗星都不给也太狠了吧", Toast.LENGTH_SHORT).show();
                    return;
                }

//      得到创建影评的时间
                long createTime = System.currentTimeMillis();
                Date date = new Date(createTime);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);

//       flag为999代表更新数据(id不变)，否则就为插入新数据
                if (flag == 999)
                    criticsDao.update(new MovieCritics(id, name, content, time, starNum));
                else
                    criticsDao.insert(new MovieCritics(null, name, content, time, starNum));
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}

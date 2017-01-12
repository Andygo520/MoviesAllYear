package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AppExit;
import com.MyApplication;
import com.example.administrator.moviesallyear.R;
import com.greendao.gen.MovieCriticsDao;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.MovieCritics;

public class CriticsDetailActivity extends AppCompatActivity {
    private Long id;// 条目的主键
    private MovieCriticsDao criticsDao;// 数据库对象

    @BindView(R.id.left_arrow)
    LinearLayout leftArrow;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_Date)
    TextView tvDate;
    @BindView(R.id.ratingBar)
    SimpleRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critics_detail);
        ButterKnife.bind(this);
        AppExit.getInstance().addActivity(this);


        ivEdit.setVisibility(View.VISIBLE);
        ivShare.setVisibility(View.VISIBLE);
        ivDelete.setVisibility(View.VISIBLE);
        title.setText("详情");
//       初始化数据库对象,并通过主键得到具体影评对象
        criticsDao = MyApplication.getInstances().getDaoSession().getMovieCriticsDao();
        id = getIntent().getLongExtra("id",  -1);
        MovieCritics critics = criticsDao.load(id);
        if (critics != null) {
            tvName.setText(critics.getName());
            tvContent.setText(critics.getCritics());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            tvDate.setText(sdf.format(critics.getCreateTime()));
            ratingBar.setRating(critics.getStars());
            ratingBar.setIndicator(true); //详情页不能点击评分条

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(CriticsDetailActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.left_arrow, R.id.iv_edit, R.id.iv_delete, R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow:
                Intent intent1 = new Intent(CriticsDetailActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_edit:
                Intent intent = new Intent(CriticsDetailActivity.this, WriteCriticsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("Flag", 999);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_delete:
                criticsDao.deleteByKey(id);
                Log.d("TAGTAG", "成功删除该条目！");
                Toast.makeText(CriticsDetailActivity.this, "成功删除该条目！", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(CriticsDetailActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.iv_share:
                break;
        }
    }
}

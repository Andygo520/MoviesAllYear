package com.example.administrator.moviesallyear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greendao.gen.MovieCriticsDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.MovieCritics;

public class CriticsDetailActivity extends AppCompatActivity {
    private long id;//影评的主键id
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critics_detail);
        ButterKnife.bind(this);

        ivEdit.setVisibility(View.VISIBLE);
        ivDelete.setVisibility(View.VISIBLE);
        title.setText("详情");
//       初始化数据库对象,并通过主键得到具体影评对象
        criticsDao = MyApplication.getInstances().getDaoSession().getMovieCriticsDao();
        id = getIntent().getLongExtra("id", (long) -1);
        MovieCritics critics = criticsDao.load(id);
        if (critics!=null){
            tvName.setText(critics.getName());
            tvContent.setText(critics.getCritics());
        }
    }

    @OnClick({R.id.left_arrow, R.id.iv_edit, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow:
                finish();
                break;
            case R.id.iv_edit:
                Intent intent = new Intent(CriticsDetailActivity.this, WriteCriticsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("Flag", 999);
                startActivity(intent);
                break;
            case R.id.iv_delete:
                criticsDao.deleteByKey(id);
                Log.d("TAGTAG","成功删除该条目！");
                Toast.makeText(CriticsDetailActivity.this, "成功删除该条目！", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

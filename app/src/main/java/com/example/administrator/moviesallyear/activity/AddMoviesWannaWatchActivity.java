package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.MoviesAllYearApplication;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.activity.base.ToolbarActivity;
import com.greendao.gen.MoviesWannaWatchDao;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.MoviesWannaWatch;

public class AddMoviesWannaWatchActivity extends ToolbarActivity {
    @BindView(R.id.et_name)
    EditText etName;
    private MoviesWannaWatchDao wannaWatchDao;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_add_movies_wanna_watch;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
//        得到一个dao对象，用来操作数据库
        wannaWatchDao= MoviesAllYearApplication.getInstances().getDaoSession().getMoviesWannaWatchDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_movies, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_save) {
            String name = etName.getText().toString().trim();
//            得到创建日期
            long createTime = System.currentTimeMillis();
            Date date = new Date(createTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(date);
            wannaWatchDao.insert(new MoviesWannaWatch(null,name,time,false));
            Intent intent = new Intent(AddMoviesWannaWatchActivity.this, MyMovieListActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

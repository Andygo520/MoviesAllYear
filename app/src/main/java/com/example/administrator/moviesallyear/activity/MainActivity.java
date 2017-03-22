package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.MoviesAllYearApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.administrator.moviesallyear.QuanysFactory;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.adapter.Top250Adapter;
import com.example.administrator.moviesallyear.utils.CheckNetwork;
import com.greendao.gen.MovieCriticsDao;
import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.ACache;
import model.Beauty;
import model.MovieCritics;
import model.Top250Movie;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.tomer.fadingtextview.FadingTextView.SECONDS;

public class MainActivity extends AppCompatActivity {
    private MovieCriticsDao criticsDao;  // 用来进行数据库操作的dao对象
    private CompositeSubscription mCompositeSubscription;
    private long currentTime = 0;
    private Drawable beautyDrawable;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvCritics)
    FadingTextView tvCritics;
    @BindView(R.id.tvComingMovies)
    TextView tvComingMovies;
    @BindView(R.id.tvTop250)
    TextView tvTop250;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.ivBeauty)
    ImageView ivBeauty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //        获取MovieCriticsDao对象
        criticsDao = MoviesAllYearApplication.getInstances().getDaoSession().getMovieCriticsDao();

//       如果数据库有数据
        if (criticsDao.queryBuilder()
                .orderDesc(MovieCriticsDao.Properties.CreateTime)
                .list().size() > 0) {
            List<MovieCritics> criticsList = criticsDao.queryBuilder()
                    .orderDesc(MovieCriticsDao.Properties.CreateTime)
                    .list();
            List<String> texts = new ArrayList<>();//存放FadingTextView显示的内容
            for (MovieCritics movieCritics : criticsList) {
                texts.add(movieCritics.getName() + "\n  " + movieCritics.getCritics());
            }
            tvCritics.setTexts(texts.toArray(new String[criticsList.size()]));
            tvCritics.setTimeout(10, SECONDS);//10秒切换一次显示内容
        } else {
            tvCritics.setText("添加影评");
        }
        Drawable drawable = ACache.get(this).getAsDrawable("beauty");
        if (!CheckNetwork.isNetworkConnected(this)) {
            if (drawable != null)
                ivBeauty.setImageDrawable(drawable);
        } else
            getBeauty();

        getHotMovies();
    }

    private void getHotMovies() {
        Subscription s = QuanysFactory.getDoubanSingleton().getHotMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Top250Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Top250Movie movie) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        List<Top250Movie.SubjectsBean> movieList = movie.getSubjects().subList(0, 5);//只显示五条记录
                        recyclerView.setAdapter(new Top250Adapter(MainActivity.this, movieList, R.layout.item_movie));
                    }
                });
        addSubscription(s);
    }

    private void getBeauty() {
        Subscription s = QuanysFactory.getGankSingleton().getMeizhiData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Beauty>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Beauty beauty) {
                        String beautyUrl = beauty.getResults().get(0).getUrl();
                        Glide.with(MainActivity.this)
                                .load(beautyUrl)
                                .into(ivBeauty)
                                .getSize(new SizeReadyCallback() {
                                    @Override
                                    public void onSizeReady(int width, int height) {
                                        beautyDrawable = ivBeauty.getDrawable();
                                        ACache.get(MainActivity.this).put("beauty", beautyDrawable);
                                    }
                                });
                    }
                });
        addSubscription(s);
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, R.string.quit_app, Toast.LENGTH_SHORT).show();
                currentTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tvCritics, R.id.tvComingMovies, R.id.tvTop250, R.id.tvMore, R.id.ivBeauty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCritics:
                startActivity(new Intent(MainActivity.this, CriticsActivity.class));
                finish();
                break;
            case R.id.tvComingMovies:
                break;
            case R.id.tvTop250:
                startActivity(new Intent(MainActivity.this, Top250Activity.class));
                finish();
                break;
            case R.id.tvMore:
                break;
            case R.id.ivBeauty:
                break;
        }
    }
}

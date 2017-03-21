package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MoviesAllYearApplication;
import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.QuanysFactory;
import com.example.administrator.moviesallyear.R;
import com.greendao.gen.MovieCriticsDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Beauty;
import model.MovieCritics;
import model.Top250Adapter;
import model.Top250Movie;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private MovieCriticsDao criticsDao;  // 用来进行数据库操作的dao对象
    private CompositeSubscription mCompositeSubscription;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvCritics)
    TextView tvCritics;
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

    private void init()  {
        //        获取MovieCriticsDao对象
        criticsDao = MoviesAllYearApplication.getInstances().getDaoSession().getMovieCriticsDao();

//        取出最新的一条影评
        if (criticsDao.queryBuilder()
                .orderDesc(MovieCriticsDao.Properties.CreateTime)
                .list().size() > 0) {
            MovieCritics critics = criticsDao.queryBuilder()
                    .orderDesc(MovieCriticsDao.Properties.CreateTime)
                    .list().get(0);
            String text = "我的影评\n " + critics.getName() + "\n" + critics.getCritics();
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.setSpan(new AbsoluteSizeSpan(50), 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
            style.setSpan(new AbsoluteSizeSpan(40), 4, text.length() - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#319BD9")), 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //颜色
            style.setSpan(new StyleSpan(Typeface.BOLD), 4, critics.getName().length() + 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //粗体,空格跟换行都算一个字符，所以要加6
            tvCritics.setText(style);
        } else {
            tvCritics.setText("我的影评");
            tvCritics.setTextSize(17);
            tvCritics.setTextColor(getResources().getColor(R.color.colorBase));
        }
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
                        List<Top250Movie.SubjectsBean> movieList=movie.getSubjects().subList(0,5);//只显示五条记录
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
                        Glide.with(MainActivity.this)
                                .load(beauty.getResults().get(0).getUrl())
                                .into(ivBeauty);
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

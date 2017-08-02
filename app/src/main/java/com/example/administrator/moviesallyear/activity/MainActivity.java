package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.MoviesAllYearApplication;
import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.activity.base.ToolbarActivity;
import com.example.administrator.moviesallyear.adapter.Top250Adapter;
import com.example.administrator.moviesallyear.retrofit.QuanysFactory;
import com.example.administrator.moviesallyear.webview.WebViewActivity;
import com.greendao.gen.MovieCriticsDao;
import com.greendao.gen.MoviesWannaWatchDao;
import com.tomer.fadingtextview.FadingTextView;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import model.Beauty;
import model.MovieCritics;
import model.Top250Movie;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tomer.fadingtextview.FadingTextView.SECONDS;

public class MainActivity extends ToolbarActivity {
    @BindView(R.id.badgeImage)
    BGABadgeImageView badgeImage;
    private MovieCriticsDao criticsDao;  // 用来进行影评表操作的dao对象
    private MoviesWannaWatchDao wannaWatchDao;// 用来进行想看表操作的dao对象
    private List<Top250Movie.SubjectsBean> movieList = new ArrayList<>();//热映电影数据源
    private Top250Adapter adapter;
    private long currentTime = 0;
    private Handler handler = new Handler();
    String beautyUrl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvCritics)
    FadingTextView tvCritics;
    @BindView(R.id.llComingMovies)
    LinearLayout llComingMovies;
    @BindView(R.id.tvTop250)
    TextView tvTop250;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.ivBeauty)
    ImageView ivBeauty;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        setUpRecyclerView();

        getHotMovies();//获取热映电影
        getBeauty();
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        //      取出Acache里的缓存数据
//        movieList = (ArrayList<Top250Movie.SubjectsBean>) ACache.get(MainActivity.this).getAsObject("movieList");
//        if (movieList != null) {
//            Log.d("movieList", movieList.size() + "");
//            adapter = new Top250Adapter(MainActivity.this, movieList, R.layout.item_movie);
//            recyclerView.setAdapter(adapter);
//            adapter.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(View itemView, int viewType, int position) {
//                    String url = movieList.get(position).getAlt();//取出电影网址url
//                    String title = movieList.get(position).getTitle();//取出电影名
//                    WebViewActivity.loadUrl(MainActivity.this, url, title);
//                }
//            });
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        获取MovieCriticsDao对象
        criticsDao = MoviesAllYearApplication.getInstances().getDaoSession().getMovieCriticsDao();
        //        得到一个dao对象，用来操作数据库
        wannaWatchDao = MoviesAllYearApplication.getInstances().getDaoSession().getMoviesWannaWatchDao();
        //BadgeView显示想看列表(watched=false)的长度
        badgeImage.showTextBadge(wannaWatchDao.queryBuilder().where(MoviesWannaWatchDao.Properties.Watched.eq(false)).list().size() + "");
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
        } else {
            tvCritics.setTexts(new String[]{"添加影评"});
        }
        tvCritics.setTimeout(10, SECONDS);//10秒切换一次显示内容
    }

    private void init() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(false);//不显示箭头

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getHotMovies();//获取热映电影
                        getBeauty();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

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
                        movieList = movie.getSubjects().subList(0, 5);//只显示五条记录
                        Log.d("movieListonNext", movieList.size() + "");
                        adapter = new Top250Adapter(MainActivity.this, movieList, R.layout.item_movie);
//                        adapter.notifyDataSetChanged();
                        Log.d("movieListonNext", adapter.toString());
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View itemView, int viewType, int position) {
                                String url = movieList.get(position).getAlt();//取出电影网址url
                                String title = movieList.get(position).getTitle();//取出电影名
                                WebViewActivity.loadUrl(MainActivity.this, url, title);
                            }
                        });
                    }
                });
        addSubscription(s);
    }

    private void getBeauty() {
        Subscription s = QuanysFactory.getGankSingleton().getMeizhiData("3600*24")
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
                        beautyUrl = beauty.getResults().get(0).getUrl();
                        Glide.with(MainActivity.this)
                                .load(beautyUrl)
                                .into(ivBeauty);
                    }
                });
        addSubscription(s);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.tvCritics, R.id.llComingMovies, R.id.tvTop250, R.id.tvMore, R.id.ivBeauty, R.id.badgeImage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCritics:
                startActivity(new Intent(MainActivity.this, CriticsActivity.class));
                break;
            case R.id.llComingMovies:
                break;
            case R.id.tvTop250:
                startActivity(new Intent(MainActivity.this, Top250Activity.class));
                break;
            case R.id.tvMore:
                break;
            case R.id.ivBeauty:
                startBeautyActivity(beautyUrl, ivBeauty);
                break;
            case R.id.badgeImage:
                startActivity(new Intent(MainActivity.this, MyMovieListActivity.class));
                break;
        }
    }

    private void startBeautyActivity(String url, View transitView) {
        Intent intent = new Intent(MainActivity.this, BeautyActivity.class);
        intent.putExtra("url", beautyUrl);
        //android V4包的类,用于两个activity转场时的缩放效果实现
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                MainActivity.this, transitView, BeautyActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(MainActivity.this, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }
    }
}

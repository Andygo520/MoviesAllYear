package com.example.administrator.moviesallyear.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.mainactivity.Category;
import com.example.administrator.moviesallyear.mainactivity.CategoryViewBinder;
import com.example.administrator.moviesallyear.mainactivity.Douban250;
import com.example.administrator.moviesallyear.mainactivity.Douban250ViewBinder;
import com.example.administrator.moviesallyear.mainactivity.HorizontalMoviesViewBinder;
import com.example.administrator.moviesallyear.mainactivity.MoviesInComingViewBinder;
import com.example.administrator.moviesallyear.retrofit.Api;
import com.example.administrator.moviesallyear.retrofit.BaseModel;
import com.example.administrator.moviesallyear.retrofit.RxHelper;
import com.example.administrator.moviesallyear.retrofit.RxSubscriber;
import com.example.administrator.moviesallyear.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import model.HorizontalMovieList;
import model.MoviesInTheater;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MultiTypeAdapter adapter;
    private Items items;
    private static final int Span = 3;//网格的列数
    private int hotMovieNum = 0;
    private int comingSoonNum = 0;
    private List<MoviesInTheater> hotMovieList = new ArrayList<>();
    private List<MoviesInTheater> comingSoonMovieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MultiTypeAdapter();
        items = new Items();
        adapter.register(Category.class, new CategoryViewBinder());
        adapter.register(HorizontalMovieList.class, new HorizontalMoviesViewBinder());
        adapter.register(MoviesInTheater.class, new MoviesInComingViewBinder());
        adapter.register(Douban250.class, new Douban250ViewBinder());

        GridLayoutManager layoutManager = new GridLayoutManager(this, Span);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
//          如果对应位置的条目是Category或MovieCritics、Douban250，那么他们占据3列
                Object item = items.get(position);
                return (item instanceof Category
                        || item instanceof HorizontalMovieList
                        || item instanceof Douban250) ? Span : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getHotMovies();
    }

    public void getHotMovies() {
        Api.getDefault().getHotMovie()
                .doOnNext(new Action1<BaseModel<ArrayList<MoviesInTheater>>>() {
                    @Override
                    public void call(BaseModel<ArrayList<MoviesInTheater>> result) {
                        hotMovieNum = result.getTotal();
                    }
                })
                .compose(RxHelper.<ArrayList<MoviesInTheater>>handleResult())
                .subscribe(new RxSubscriber<ArrayList<MoviesInTheater>>(this) {
                    @Override
                    protected void _onNext(ArrayList<MoviesInTheater> hotMovies) {
                        hotMovieList = hotMovies.subList(0, 7);
//                      完成热映电影的接口调用后，再调用即将上映影片接口
                        getComingSoonMovies();
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(message);
                    }
                });

    }

    public void getComingSoonMovies() {
        Api.getDefault().getComingSoonMovie()
                .doOnNext(new Action1<BaseModel<ArrayList<MoviesInTheater>>>() {
                    @Override
                    public void call(BaseModel<ArrayList<MoviesInTheater>> result) {
                        comingSoonNum = result.getTotal();
                    }
                })
                .compose(RxHelper.<ArrayList<MoviesInTheater>>handleResult())
                .subscribe(new RxSubscriber<ArrayList<MoviesInTheater>>(this) {
                    @Override
                    protected void _onNext(ArrayList<MoviesInTheater> comingSoonMovies) {
                        comingSoonMovieList = comingSoonMovies.subList(0, 6);
                        getDouban250();
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(message);
                    }

                    @Override
                    protected boolean showDialog() {
                        return false;
                    }
                })
        ;
    }

    public void getDouban250() {
        Api.getDefault().getMovieTop250()
                .compose(RxHelper.<ArrayList<Douban250>>handleResult())
                .subscribe(new RxSubscriber<ArrayList<Douban250>>(this) {
                    @Override
                    protected void _onNext(ArrayList<Douban250> douban250List) {
                        items.add(new Category(getString(R.string.hot_movies), getString(R.string.total) + hotMovieNum));
                        items.add(new HorizontalMovieList(hotMovieList));
                        items.add(new Category(getString(R.string.coming_soon), getString(R.string.total) + comingSoonNum));
                        items.addAll(comingSoonMovieList);
                        items.add(new Category(getString(R.string.douban250), getString(R.string.more_movies)));
                        items.addAll(douban250List.subList(0, 5));
                        adapter.setItems(items);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(message);
                    }

                    @Override
                    protected boolean showDialog() {
                        return false;
                    }
                });
    }
}

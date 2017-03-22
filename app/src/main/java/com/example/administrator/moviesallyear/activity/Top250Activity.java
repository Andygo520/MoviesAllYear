package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.administrator.moviesallyear.QuanysFactory;
import com.example.administrator.moviesallyear.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helper.UrlHelper;
import model.MovieItem;
import com.example.administrator.moviesallyear.adapter.Top250Adapter;
import model.Top250Movie;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Top250Activity extends AppCompatActivity {
    private CompositeSubscription mCompositeSubscription;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    private List<Top250Movie.SubjectsBean> data = new ArrayList<>();
    private List<MovieItem> movieList = new ArrayList<>();
    private int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250);
        ButterKnife.bind(this);

        final String url = UrlHelper.top250_url.replace("{start}", start + "");
        recyclerView.setLayoutManager(new LinearLayoutManager(Top250Activity.this));
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                start += 10;
//                getTop250(url);

            }
        });
        loadData();
    }

    private void loadData() {
        // @formatter:off
        Subscription s = QuanysFactory.getDoubanSingleton().getMovieTop250(0, 10)
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
                    public void onNext(Top250Movie top250Movie) {
                        Toast.makeText(Top250Activity.this, "success", Toast.LENGTH_SHORT).show();
                        data.addAll(top250Movie.getSubjects());
                        recyclerView.setAdapter(new Top250Adapter(Top250Activity.this, data, R.layout.item_250));
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
        if (keyCode==KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(Top250Activity.this,MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

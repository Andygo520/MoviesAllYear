package com.example.administrator.moviesallyear.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.example.administrator.moviesallyear.QuanysFactory;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.adapter.Top250Adapter;
import com.example.administrator.moviesallyear.utils.CheckNetwork;
import com.example.administrator.moviesallyear.webview.WebViewActivity;
import com.fingdo.statelayout.StateLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.MovieItem;
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
    @BindView(R.id.state_layout)
    StateLayout stateLayout;
    private List<Top250Movie.SubjectsBean> data = new ArrayList<>();
    private List<MovieItem> movieList = new ArrayList<>();
    private Top250Adapter adapter;
    private int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250);
        ButterKnife.bind(this);

        if (!CheckNetwork.isNetworkConnected(Top250Activity.this))
            stateLayout.showNoNetworkView();
        else {
            initRecyclerView();
        }
//       监听状态布局的刷新
        stateLayout.setRefreshListener(new StateLayout.OnViewRefreshListener() {
            @Override
            public void refreshClick() {
                if (CheckNetwork.isNetworkConnected(Top250Activity.this)) {
                    stateLayout.showContentView();//显示布局内容
                    initRecyclerView();
                }
            }

            @Override
            public void loginClick() {

            }
        });

    }


    private void initRecyclerView() {
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
//                loadData(start);
//                recyclerView.loadMoreComplete();//to notify that the loading more work is done
                if (start <140) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            loadData(start);
                            recyclerView.loadMoreComplete();
                            adapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }else if (start == 140){
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            loadData(start);
                            recyclerView.noMoreLoading();
                            adapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }

            }
        });
        loadData(0);

    }


    private void loadData(final int start) {
        // @formatter:off
        Subscription s = QuanysFactory.getDoubanSingleton().getMovieTop250(start, 10)
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
                        data.addAll(top250Movie.getSubjects());
                        if (start==0){
                            adapter = new Top250Adapter(Top250Activity.this, data, R.layout.item_250);
                            recyclerView.setAdapter(adapter);
                        }
                        adapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View itemView, int viewType, int position) {
                                String url = data.get(position - 1).getAlt();
                                String name = data.get(position - 1).getTitle();
                                WebViewActivity.loadUrl(Top250Activity.this, url, name);
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
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

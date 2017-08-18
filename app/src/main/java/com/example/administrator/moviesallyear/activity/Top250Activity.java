package com.example.administrator.moviesallyear.activity;


//public class Top250Activity extends ToolbarActivity {
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.app_bar_layout)
//    AppBarLayout appBarLayout;
//    @BindView(R.id.recyclerView)
//    XRecyclerView recyclerView;
//    @BindView(R.id.state_layout)
//    StateLayout stateLayout;
//    private List<Top250Movie.SubjectsBean> data = new ArrayList<>();
//    private List<MovieItem> movieList = new ArrayList<>();
//    private Top250Adapter adapter;
//    private int start = 0;
//
//    @Override
//    protected int provideContentViewId() {
//        return R.layout.activity_top250;
//    }
//
//    @Override
//    public void onToolbarClick() {
//        recyclerView.scrollToPosition(0);
//    }
//
//    @Override public boolean canBack() {
//        return true;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
//        if (!CheckNetwork.isNetworkConnected(Top250Activity.this))
//            stateLayout.showNoNetworkView();
//        else {
//            initRecyclerView();
//        }
////       监听状态布局的刷新
//        stateLayout.setRefreshListener(new StateLayout.OnViewRefreshListener() {
//            @Override
//            public void refreshClick() {
//                if (CheckNetwork.isNetworkConnected(Top250Activity.this)) {
//                    stateLayout.showContentView();//显示布局内容
//                    initRecyclerView();
//                }
//            }
//
//            @Override
//            public void loginClick() {
//
//            }
//        });
//    }
//
//
//    private void initRecyclerView() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(Top250Activity.this));
//        recyclerView.setPullRefreshEnabled(false);
//        recyclerView.setLoadingMoreEnabled(true);
//        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
//                start += 10;
////                loadData(start);
////                recyclerView.loadMoreComplete();//to notify that the loading more work is done
//                if (start < 140) {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            loadData(start);
//                            recyclerView.loadMoreComplete();
//                            adapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                } else if (start == 140) {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            loadData(start);
//                            recyclerView.noMoreLoading();
//                            adapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                }
//
//            }
//        });
//        loadData(0);
//
//    }
//
//
//    private void loadData(final int start) {
//        // @formatter:off
//        Subscription s = QuanysFactory.getDoubanSingleton().getMovieTop250(start, 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Top250Movie>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Top250Movie top250Movie) {
//                        data.addAll(top250Movie.getSubjects());
//                        if (start == 0) {
//                            adapter = new Top250Adapter(Top250Activity.this, data, R.layout.item_250);
//                            recyclerView.setAdapter(adapter);
//                        }
//                        adapter.setOnItemClickListener(new OnItemClickListener() {
//                            @Override
//                            public void onItemClick(View itemView, int viewType, int position) {
//                                String url = data.get(position - 1).getAlt();
//                                String name = data.get(position - 1).getTitle();
//                                WebViewActivity.loadUrl(Top250Activity.this, url, name);
//                            }
//                        });
//
//                    }
//                });
//
//        addSubscription(s);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//}

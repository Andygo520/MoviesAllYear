package com.example.administrator.moviesallyear.fragment;


/**
 * A simple {@link Fragment} subclass.
 */
//public class ExploreFragment extends Fragment {
//    private String query;//用户搜索的内容
//    private String url;//用户搜索的信息对应的URL
//    private List<MovieItem> movies=new ArrayList<>();
//
//    @BindView(R.id.etSearchInput)
//    EditText etSearchInput;
//    @BindView(R.id.ivDelete)
//    ImageView ivDelete;
//    @BindView(R.id.recyclerView)
//    XRecyclerView recyclerView;
//
//    public ExploreFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_explore, container, false);
//        ButterKnife.bind(this, view);
////        注册消息订阅者
//        EventBus.getDefault().register(this);
//
//        init();
//        return view;
//    }
//
//    public void init() {
//        //        默认设置XRecyclerView不能下拉刷新以及上拉加载更多
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setPullRefreshEnabled(false);
//        recyclerView.setLoadingMoreEnabled(false);
//
//        //  当EditText设置了imeOptions属性后，利用该方法给回车键设置点击事件
//        etSearchInput.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
//                    query = etSearchInput.getText().toString().trim();
//                    try {
//                        query = URLEncoder.encode(query, "utf-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//
//                    if (query.equals("")) {
//                        Toast.makeText(getActivity(), "请输入内容后再搜索", Toast.LENGTH_SHORT).show();
//                    } else {
//                        url = UrlHelper.query_url.replace("{query}", query);
//                        Log.d("queryurl", url);
//                        getMovieInfor(url);
//                    }
//                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    if (imm.isActive()) {
//                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
//                    }
//                    return true;
//                }
//                return false;
//            }
//
//        });
////     监听文本框内容变化
//        etSearchInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().equals("")) {
//                    ivDelete.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);//隐藏recyclerView
//                } else
//                    ivDelete.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//
//    //   处理事件
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void handleEvent(String name) {
//        etSearchInput.setHint(name);
//    }
//
//    public void getMovieInfor(final String url) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RequestQueue queue = Volley.newRequestQueue(getActivity());
//                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        MovieInSearch info = JSON.parseObject(s, MovieInSearch.class);
//                        final List<MovieInSearch.SubjectsBean> subjects = info.getSubjects();
////                        通过一层循环取出电影条目的标题、大图标地址URL
//                        for (MovieInSearch.SubjectsBean item : subjects) {
//                            String title=item.getTitle();
//                            String image_url=item.getImages().getLarge();
//                            List<MovieInSearch.SubjectsBean.CastsBean> castsBean = item.getCasts();
////                            通过二层循环取出演员姓名列表
//                            List<String> casts=new ArrayList<String>();
//                            for (MovieInSearch.SubjectsBean.CastsBean actors : castsBean) {
//                              casts.add(actors.getName());
//                            }
//                            movies.add(new MovieItem(title,casts,image_url));
//                        }
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                        recyclerView.setAdapter(new MovieAdapter(getActivity(),movies, R.layout.list_item));
//                        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
//                            @Override
//                            public void onLongClick(RecyclerView.ViewHolder vh) {
//                            }
//
//                            @Override
//                            public void onItemClick(RecyclerView.ViewHolder vh) {
//                                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
//                                intent.putExtra("Id", subjects.get(vh.getLayoutPosition() - 1).getId());
//                                Log.d("MoviePosition", vh.getLayoutPosition() - 1 + "");
//                                intent.putExtra("Title", subjects.get(vh.getLayoutPosition() - 1).getTitle());
//                                intent.putExtra("ImageUrl", subjects.get(vh.getLayoutPosition() - 1).getImages().getLarge());
//                                startActivity(intent);
//                            }
//                        });
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//
//                    }
//                });
//                queue.add(request);
//            }
//        }).start();
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @OnClick({R.id.ivDelete, R.id.llSearch})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.ivDelete:
//                etSearchInput.setText("");
//                break;
//
//            case R.id.llSearch:
////            获取用户的输入，并对输入内容进行utf-8格式的编码（避免中文的不兼容）
//                query = etSearchInput.getText().toString().trim();
//                String hintText = (String) etSearchInput.getHint();
//                if (query.equals("") && hintText.equals("")) {
//                    Toast.makeText(getActivity(), "请输入内容后再搜索", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                try {
//                    query = URLEncoder.encode(query, "utf-8");
//                    hintText = URLEncoder.encode(hintText, "utf-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//           //  如果用户没有输入，可以直接根据EditText的hint内容来搜索
//                if (query.equals(""))
//                    url = UrlHelper.query_url.replace("{query}", hintText);
//                else
//                    url = UrlHelper.query_url.replace("{query}", query);
//                getMovieInfor(url);
//                break;
//        }
//    }
//}
//

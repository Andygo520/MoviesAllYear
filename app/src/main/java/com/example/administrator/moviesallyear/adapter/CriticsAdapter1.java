package com.example.administrator.moviesallyear.adapter;

/**
 * Created by Administrator on 2017/3/22.
 */

//public class CriticsAdapter1 extends SuperAdapter<MovieCritics> {
//
//    private Context context = getContext();
//    private CompositeSubscription mCompositeSubscription;
//
//
//    public CriticsAdapter1(Context context, List<MovieCritics> items, IMulItemViewType<MovieCritics> mulItemViewType) {
//        super(context, items, mulItemViewType);
//    }
//
//    @Override
//    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, final MovieCritics item) {
//        holder.setText(R.id.tv_name, item.getName());
//        holder.setText(R.id.tv_content, item.getCritics());
//        holder.setText(R.id.tv_createTime, item.getCreateTime());
//        ImageView ivDouban = holder.findViewById(R.id.iv_douban);
//        SimpleRatingBar ratingBar = holder.findViewById(R.id.ratingBar);
//        ratingBar.setRating(item.getStars());
//        ratingBar.setEnabled(false);//不能操作评价条
////        有网的时候，豆瓣图标为亮色
//        if (CheckNetwork.isNetworkConnected(getContext())) {
//            ivDouban.setImageResource(R.mipmap.douban_net);
//        } else
//            ivDouban.setImageResource(R.mipmap.douban);
//
//        ivDouban.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (CheckNetwork.isNetworkConnected(getContext()))
//                    showDialog(item.getName());//根据电影名，查找相应的电影，显示一个bottomsheetdialog
//                else
//                    Toast.makeText(getContext(), R.string.check_net, Toast.LENGTH_SHORT).show();
//            }
//        });
//        switch (viewType) {
//            case 0:
//                break;
//            case 1:
//                holder.setText(R.id.tv_date, item.getCreateTime().substring(0, 7));
//                break;
//        }
//    }
//
//    private void showDialog(String query) {
//
//        Subscription s = QuanysFactory.getDoubanSingleton().getSearchedMovie(query)
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
//                        final BottomSheetDialog dialog = new BottomSheetDialog(context);
//                        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog, null);
//                        ListView listView = (ListView) view.findViewById(R.id.list);
//                        List<Top250Movie.SubjectsBean> list=  top250Movie.getSubjects();
//                        if (list.size()==0){
//                            Toast.makeText(getContext(), R.string.no_item, Toast.LENGTH_SHORT).show();
//                        }else{
// //                        只显示六条数据
//                            final List<Top250Movie.SubjectsBean> data = list.subList(0, 6);
//                            listView.setAdapter(new DialogAdapter(context, data, R.layout.dialog_item));
//                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    String url = data.get(i).getAlt();
//                                    String name = data.get(i).getTitle();
//                                    WebViewActivity.loadUrl(context, url, name);
//                                    dialog.dismiss();
//                                }
//                            });
//                            dialog.setContentView(view);
//                            dialog.show();
//                        }
//                    }
//                });
//
//        addSubscription(s);
//    }
//
//    public void addSubscription(Subscription s) {
//        if (this.mCompositeSubscription == null) {
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//        this.mCompositeSubscription.add(s);
//    }
//
//}

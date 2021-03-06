package com.example.administrator.moviesallyear.adapter;

/**
 * Created by Administrator on 2NormalCriticsDateCritics6/DateCritics2/3NormalCritics.
 */

////为RecyclerView的每个子item设置setOnClickListener，然后在onClick中再调用一次对外封装的接口，将这个事件传递给外面的调用者。而“为RecyclerView的每个子item设置setOnClickListener”在Adapter中设置。
//public class CriticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
//    private Context context;
//    private List<MovieCritics> movieList;
//    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
//    private final int NormalCriticsFlag = 0;// 普通影评的标志
//    private final int DateCriticsFlag = 1;//带日期影评标志
//    private CompositeSubscription mCompositeSubscription;
//
//
//    public static interface OnRecyclerViewItemClickListener {
//        void onItemClick(View view, int position);
//
//        void onItemLongClick(View view, int positon);
//    }
//
//    public CriticsAdapter(List<MovieCritics> movieList, Context context) {
//        this.movieList = movieList;
//        this.context = context;
//    }
//
//    //  处理单击事件
//    @Override
//    public void onClick(View view) {
//        if (mOnItemClickListener != null) {
//            //注意这里使用getTag方法获取数据
//            mOnItemClickListener.onItemClick(view, (int) view.getTag());
//        }
//    }
//
//    //  处理长按事件
//    @Override
//    public boolean onLongClick(View view) {
//        if (mOnItemClickListener != null) {
//            mOnItemClickListener.onItemLongClick(view, (int) view.getTag());
//            return true;
//        } else
//            return false;
//    }
//
//
//    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }
//
//    //     作为显示多种布局的依据
//    //判断item类别，是普通影评还是带日期的影评
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return DateCriticsFlag;
//        } else {
////            获取影评创建日期的年月字段（长度为7）
//            String date = movieList.get(position).getCreateTime().substring(0, 7);
//            String date1 = movieList.get(position - 1).getCreateTime().substring(0, 7);
//            return date.equals(date1) ? NormalCriticsFlag : DateCriticsFlag;
//        }
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == NormalCriticsFlag) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.critics_item, parent, false);
//            NormalCriticsHolder holder = new NormalCriticsHolder(view);
//            //将创建的View注册点击事件
//            view.setOnClickListener(this);
//            view.setOnLongClickListener(this);
//            return holder;
//        } else {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.critics_with_date_item, parent, false);
//            DateCriticsHolder holder2 = new DateCriticsHolder(view);
//            return holder2;
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        //将数据与item视图进行绑定
//        if (holder instanceof NormalCriticsHolder) {
//            ((NormalCriticsHolder) holder).tvName.setText(movieList.get(position).getName());
//            ((NormalCriticsHolder) holder).tvContent.setText(movieList.get(position).getCritics());
//            ((NormalCriticsHolder) holder).tvTime.setText(movieList.get(position).getCreateTime().substring(5, 16));//只显示月-日 时：分字段
//            ((NormalCriticsHolder) holder).ratingBar.setRating(movieList.get(position).getStars());
//            ((NormalCriticsHolder) holder).ratingBar.setIndicator(true);// 列表不能修改评分
//
//            ((NormalCriticsHolder) holder).name=movieList.get(position).getName();
//            //            有网络就显示亮图标
//            if (isNetAvailable())
//                ((NormalCriticsHolder) holder).ivDouban.setImageResource(R.mipmap.douban_net);
//            else
//                ((NormalCriticsHolder) holder).ivDouban.setImageResource(R.mipmap.douban);
//
//            ((NormalCriticsHolder) holder).ivDouban.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                     showDialog( ((NormalCriticsHolder) holder).name);
//                }
//            });
//
//        } else if (holder instanceof DateCriticsHolder) {
//
////           创建MovieCriticsDao数据库对象，通过数据库查询出每月所看电影的数量（qb.buildCount().count()）
//            MovieCriticsDao criticsDao = MoviesAllYearApplication.getInstances().getDaoSession().getMovieCriticsDao();
//            QueryBuilder qb = criticsDao.queryBuilder().where(MovieCriticsDao.Properties.CreateTime.like("%" + movieList.get(position).getCreateTime().substring(0, 7) + "%"));
//            long count = qb.buildCount().count();
//            ((DateCriticsHolder) holder).tvDate.setText(movieList.get(position).getCreateTime().substring(0, 7) + "（" + count + "部）");
//            ((DateCriticsHolder) holder).tvName.setText(movieList.get(position).getName());
//            ((DateCriticsHolder) holder).tvContent.setText(movieList.get(position).getCritics());
//            ((DateCriticsHolder) holder).tvTime.setText(movieList.get(position).getCreateTime().substring(5, 16));
//            ((DateCriticsHolder) holder).ratingBar.setRating(movieList.get(position).getStars());
//            ((DateCriticsHolder) holder).ratingBar.setIndicator(true);// 列表不能修改评分
//            ((DateCriticsHolder) holder).name=movieList.get(position).getName();
//
////            有网络就显示亮图标
//            if (isNetAvailable())
//                ((DateCriticsHolder) holder).ivDouban.setImageResource(R.mipmap.douban_net);
//            else
//                ((DateCriticsHolder) holder).ivDouban.setImageResource(R.mipmap.douban);
//
//            ((DateCriticsHolder) holder).ivDouban.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    showDialog( ((DateCriticsHolder) holder).name);
//                }
//            });
//
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return movieList.size();
//    }
//
//
//    //自定义ViewHolder，用于显示普通影评
//    class NormalCriticsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @BindView(R.id.tv_name)
//        TextView tvName;
//        @BindView(R.id.tv_content)
//        TextView tvContent;
//        @BindView(R.id.tv_createTime)
//        TextView tvTime;
//        @BindView(R.id.ratingBar)
//        SimpleRatingBar ratingBar;
//        @BindView(R.id.iv_douban)
//        ImageView ivDouban;
//
//        String name;
//
//        public NormalCriticsHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            ivDouban.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    }
//
//    //自定义ViewHolder，用于显示带日期的影评
//    class DateCriticsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @BindView(R.id.tv_date)
//        TextView tvDate;
//        @BindView(R.id.tv_name)
//        TextView tvName;
//        @BindView(R.id.tv_content)
//        TextView tvContent;
//        @BindView(R.id.tv_createTime)
//        TextView tvTime;
//        @BindView(R.id.ratingBar)
//        SimpleRatingBar ratingBar;
//        @BindView(R.id.iv_douban)
//        ImageView ivDouban;
//        String name;
//
//        public DateCriticsHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//            ivDouban.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    }
//
//   private void showDialog(String query){
//
//       Subscription s= QuanysFactory.getDoubanSingleton().getSearchedMovie(query)
//                      .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                   .subscribe(new Observer<Top250Movie>() {
//                       @Override
//                       public void onCompleted() {
//
//                       }
//
//                       @Override
//                       public void onError(Throwable e) {
//
//                       }
//
//                       @Override
//                       public void onNext(Top250Movie top250Movie) {
//                           Toast.makeText(context,"success",Toast.LENGTH_SHORT).show();
//
//                           BottomSheetDialog dialog=new BottomSheetDialog(context);
//                           View view=LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog,null);
//                           ListView listView= (ListView) view.findViewById(R.id.list);
//                           listView.setAdapter(new DialogAdapter(context,top250Movie.getSubjects().subList(0,6),R.layout.dialog_item));
//                           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                               @Override
//                               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                               }
//                           });
//                           dialog.setContentView(view);
//                           dialog.show();
//                       }
//                   });
//
//       addSubscription(s);
//   }
//
//    public void addSubscription(Subscription s) {
//        if (this.mCompositeSubscription == null) {
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//
//        this.mCompositeSubscription.add(s);
//    }
//
//    public boolean isNetAvailable(){
//        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
//        if (networkInfo==null){
//            return false;
//        }else
//            return true;
//    }
//}

package com.example.administrator.moviesallyear.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.MoviesAllYearApplication;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.adapter.CriticsAdapter;
import com.example.administrator.moviesallyear.adapter.CriticsAdapter1;
import com.example.administrator.moviesallyear.adapter.CriticsSearchedAdapter;
import com.greendao.gen.MovieCriticsDao;

import org.byteam.superadapter.IMulItemViewType;
import org.byteam.superadapter.OnItemClickListener;
import org.byteam.superadapter.OnItemLongClickListener;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.ShareHelper;
import helper.SnackbarHelper;
import model.MovieCritics;

public class CriticsActivity extends AppCompatActivity {
    private String query;//用户搜索的内容
    private MovieCriticsDao criticsDao;  // 用来进行数据库操作的dao对象
    private List<MovieCritics> movieCriticsList;// 根据时间排序的recyclerView的数据源
    private String[] items = {"编辑", "删除", "分享"};
    private CriticsAdapter adapter;
    private StringBuilder sb = new StringBuilder();//存放导出到手机的文本内容

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critics);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_order_stars) {
//                    先以评分降序排列，然后以时间降序排列
                    QueryBuilder qb = criticsDao.queryBuilder().orderDesc(MovieCriticsDao.Properties.Stars)
                            .orderDesc(MovieCriticsDao.Properties.CreateTime);
                    List<MovieCritics> criticsListByStars = qb.list();
                    recyclerView.setAdapter(new CriticsAdapter(criticsListByStars, CriticsActivity.this));
                    return true;
                } else if (item.getItemId() == R.id.item_order_time) {
                    showCriticsList(movieCriticsList, CriticsActivity.this, 1);
                    return true;
                } else if (item.getItemId() == R.id.item_output_critics) {
                    initData();
                    Toast.makeText(CriticsActivity.this, "导出成功", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        //        默认设置XRecyclerView不能下拉刷新以及上拉加载更多
        recyclerView.setLayoutManager(new LinearLayoutManager(CriticsActivity.this));
        //        获取MovieCriticsDao对象
        criticsDao = MoviesAllYearApplication.getInstances().getDaoSession().getMovieCriticsDao();
    }

    private void initData() {
        String filePath = "/sdcard/MovieCritics/";
        String fileName = "影评.txt";

        writeTxtToFile(sb.toString(), filePath, fileName);
    }

    // 将字符串写入到文本文件中
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //    public void onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        menu.clear();
//        getMenu.inflate(R.menu.menu_main, menu);
//        MenuItem searchItem = menu.findItem(R.id.item_search);
////        为searchItem绑定一个监听expand跟collapse状态的监听器
//        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                return true;// Return true to expand action view
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                showCriticsList(movieCriticsList, CriticsActivity.this, 1);
//                return true;// Return true to collapse action view
//            }
//        });
//
//        final SearchView searchView = (SearchView) searchItem.getActionView();
////        ImageView button=(ImageView)searchView.findViewById(R.id.search_mag_icon);
////        button.setImageDrawable(getResources().getDrawable(R.drawable.edit_critics));
////        得到SearchView的输入框，将其背景设为白色
//        View view = searchView.findViewById(android.support.v7.appcompat.R.id.search_edit_frame);
//        view.setBackgroundColor(getResources().getColor(R.color.color_white));
//
//
//        searchView.setQueryHint("输入关键字查找影评...");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                searchCritics(newText);
//                return false;
//            }
//        });
//    }

    public void onResume() {
        super.onResume();
        //   从数据库查询数据(根据创建时间降序排列)
        movieCriticsList = criticsDao.queryBuilder()
                .orderDesc(MovieCriticsDao.Properties.CreateTime)
                .list();
        for (MovieCritics critics : movieCriticsList) {
            sb.append("电影：")
                    .append(critics.getName())
                    .append("\n")
                    .append("影评：")
                    .append(critics.getCritics().replace("\\s", ""))
                    .append("\n")
                    .append(critics.getCreateTime() + "       " + critics.getStars() + "星")
                    .append("\n\n\n");
        }
//        List<MovieCritics> movieCriticsList = criticsDao.loadAll();
        showCriticsList(movieCriticsList, CriticsActivity.this, 1);
    }

    public void showCriticsList(final List<MovieCritics> movieList, final Context context, int type) {
//      type为查找状态显示的影评跟一般状态显示的影评的区分标志，type==0的时候，表示查找状态，使用CriticsSearchedAdapter
//      type==1的时候，表示一般状态，使用CriticsAdapter
        if (type == 0)
            recyclerView.setAdapter(new CriticsSearchedAdapter(movieList, context));

        else if (type == 1) {
            final CriticsAdapter1 adapter = new CriticsAdapter1(context, movieList, new IMulItemViewType<MovieCritics>() {
                @Override
                public int getViewTypeCount() {
                    return 2;
                }

                @Override
                public int getItemViewType(int position, MovieCritics critics) {
                    if (position == 0) {
                        return 1;
                    } else {
//            获取影评创建日期的年月字段（长度为7）
                        String date = movieList.get(position).getCreateTime().substring(0, 7);
                        String date1 = movieList.get(position - 1).getCreateTime().substring(0, 7);
                        return date.equals(date1) ? 0 : 1;
                    }
                }

                @Override
                public int getLayoutId(int viewType) {
                    if (viewType == 0)
                        return R.layout.critics_item;
                    else
                        return R.layout.critics_with_date_item;
                }
            });
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int viewType, int position) {
                    Log.d("TAGG", position + "");
                    Intent intent = new Intent(context, CriticsDetailActivity.class);
                    MovieCritics movieCritics = movieList.get(position);
                    intent.putExtra("id", movieCritics.getId());
                    startActivity(intent);
                }
            });
            adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View itemView, int viewType, final int position) {
                    //              长按的时候给出震动提示
                    Vibrator vibrator = (Vibrator) CriticsActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1);
                    Log.d("TAGG", position + "");
                    final MovieCritics movieCritics = movieList.get(position);
                    final long id = movieCritics.getId();
                    Log.d("TAGG", id + "");
                    final Dialog dialog = new AlertDialog.Builder(CriticsActivity.this)
                            .setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    switch (i) {
                                        //     编辑
                                        case 0: {
                                            Intent intent = new Intent(CriticsActivity.this, WriteCriticsActivity.class);
                                            intent.putExtra("Flag", 999);// 修改影评的标志位
                                            intent.putExtra("id", id);
                                            startActivity(intent);
                                            break;
                                        }
                                        //     删除
                                        case 1: {
                                            new AlertDialog.Builder(CriticsActivity.this)
                                                    .setTitle("警告")
                                                    .setMessage("删除后数据将无法恢复！\n\n确定删除吗？")
                                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.dismiss();
                                                        }
                                                    })
                                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //删除position位置的数据，注意最后要通知position下面的条目位置要更新
                                                            movieList.remove(position);
                                                            adapter.notifyItemRemoved(position);
                                                            adapter.notifyItemRangeChanged(position, movieCriticsList.size() - position);
                                                            criticsDao.deleteByKey(id);//从数据库删除记录
                                                            //使用Snackbar进行提示
                                                            SnackbarHelper.ShortSnackbar(recyclerView, "删除成功", SnackbarHelper.Info).show();
                                                        }
                                                    })
                                                    .create()
                                                    .show();

                                            break;
                                        }

                                        //     分享
                                        case 2: {
                                            ShareHelper.showShare(CriticsActivity.this, movieCritics);
                                            break;
                                        }
                                    }
                                }
                            }).create();
                    dialog.show();
                }
            });
        }
    }

    //    实现查找影评
    public void searchCritics(String query) {
        if (!query.equals("")) {
            //      criticsDao模糊查询结果
            List<MovieCritics> list = criticsDao.queryBuilder().whereOr(MovieCriticsDao.Properties.Name.like("%" + query + "%")
                    , MovieCriticsDao.Properties.Critics.like("%" + query + "%")).orderDesc(MovieCriticsDao.Properties.CreateTime)
                    .list();
            showCriticsList(list, CriticsActivity.this, 0);
        }
    }

    @OnClick(R.id.fab_add)
    public void onClick() {
        Intent intent = new Intent(CriticsActivity.this, WriteCriticsActivity.class);
        startActivity(intent);
        //实现淡入浅出的页面跳转效果
        // CriticsActivity.this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        //由左向右滑入的效果
        //CriticsActivity.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        //实现zoommin 和 zoomout,即类似iphone的进入和退出时的效果
        //CriticsActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

}

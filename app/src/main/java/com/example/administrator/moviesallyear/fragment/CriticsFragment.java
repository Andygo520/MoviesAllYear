package com.example.administrator.moviesallyear.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.MoviesAllYearApplication;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.activity.CriticsDetailActivity;
import com.example.administrator.moviesallyear.activity.WriteCriticsActivity;
import com.greendao.gen.MovieCriticsDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.OnRecyclerItemClickListener;
import helper.ShareHelper;
import helper.SnackbarHelper;
import com.example.administrator.moviesallyear.adapter.CriticsAdapter;
import com.example.administrator.moviesallyear.adapter.CriticsSearchedAdapter;
import model.MovieCritics;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriticsFragment extends Fragment {
    private String query;//用户搜索的内容
    private MovieCriticsDao criticsDao;  // 用来进行数据库操作的dao对象
    private List<MovieCritics> movieCriticsList;// 根据时间排序的recyclerView的数据源
    private String[] items = {"编辑", "删除", "分享"};
    private CriticsAdapter adapter;
    private StringBuilder sb = new StringBuilder();//存放导出到手机的文本内容

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    public CriticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_critics, container, false);
        ButterKnife.bind(this, view);
        init();


        return view;
    }

    public void init() {
        setHasOptionsMenu(true);
//        获得Fragment所在的Activity并将其强转为AppCompatActivity，这样才能调用setSupportActionBar(toolbar)
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_order_stars) {
//                    先以评分降序排列，然后以时间降序排列
                    QueryBuilder qb = criticsDao.queryBuilder().orderDesc(MovieCriticsDao.Properties.Stars)
                            .orderDesc(MovieCriticsDao.Properties.CreateTime);
                    List<MovieCritics> criticsListByStars = qb.list();
                    recyclerView.setAdapter(new CriticsAdapter(criticsListByStars, getActivity()));
                    return true;
                } else if (item.getItemId() == R.id.item_order_time) {
                    showCriticsList(movieCriticsList, getActivity(), 1);
                    return true;
                } else if (item.getItemId() == R.id.item_output_critics) {
                    initData();
                    Toast.makeText(getActivity(), "导出成功", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        //        默认设置XRecyclerView不能下拉刷新以及上拉加载更多
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
//        获得菜单中的SearchView条目
        MenuItem searchItem = menu.findItem(R.id.item_search);
//        为searchItem绑定一个监听expand跟collapse状态的监听器
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;// Return true to expand action view
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                showCriticsList(movieCriticsList, getActivity(), 1);
                return true;// Return true to collapse action view
            }
        });

        final SearchView searchView = (SearchView) searchItem.getActionView();
//        ImageView button=(ImageView)searchView.findViewById(R.id.search_mag_icon);
//        button.setImageDrawable(getResources().getDrawable(R.drawable.edit_critics));
//        得到SearchView的输入框，将其背景设为白色
        View view = searchView.findViewById(android.support.v7.appcompat.R.id.search_edit_frame);
        view.setBackgroundColor(getResources().getColor(R.color.color_white));


        searchView.setQueryHint("输入关键字查找影评...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCritics(newText);
                return false;
            }
        });
    }

    @Override
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
                    .append(critics.getCritics().replace("\\s",""))
                    .append("\n")
                    .append(critics.getCreateTime() + "       " + critics.getStars() + "星")
                    .append("\n\n\n");
        }
//        List<MovieCritics> movieCriticsList = criticsDao.loadAll();
        showCriticsList(movieCriticsList, getActivity(), 1);
    }

    public void showCriticsList(final List<MovieCritics> movieList, final Context context, int type) {
//      type为查找状态显示的影评跟一般状态显示的影评的区分标志，type==0的时候，表示查找状态，使用CriticsSearchedAdapter
//      type==1的时候，表示一般状态，使用CriticsAdapter
        if (type == 0)
            recyclerView.setAdapter(new CriticsSearchedAdapter(movieList, context));

        else if (type == 1) {
            adapter = new CriticsAdapter(movieList, context);
            recyclerView.setAdapter(adapter);
        }

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onLongClick(final RecyclerView.ViewHolder vh) {
//              长按的时候给出震动提示
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1);
                final int position = vh.getLayoutPosition() - 1;
                Log.d("idididid", position + "");
                final MovieCritics movieCritics = movieList.get(position);
                final long id = movieCritics.getId();
                Log.d("idididid", id + "");
                final Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    //     编辑
                                    case 0: {
                                        Intent intent = new Intent(getActivity(), WriteCriticsActivity.class);
                                        intent.putExtra("Flag", 999);// 修改影评的标志位
                                        intent.putExtra("id", id);
                                        startActivity(intent);
                                        break;
                                    }
                                    //     删除
                                    case 1: {
                                        new AlertDialog.Builder(getActivity())
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
                                        ShareHelper.showShare(getActivity(), movieCritics);
                                        break;
                                    }
                                }
                            }
                        }).create();
                dialog.show();
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Intent intent = new Intent(context, CriticsDetailActivity.class);
//                获得点击条目的位置
                int position = vh.getLayoutPosition() - 1;
                MovieCritics movieCritics = movieList.get(position);
                intent.putExtra("id", movieCritics.getId());
                startActivity(intent);
            }
        });
    }

    //    实现查找影评
    public void searchCritics(String query) {
        if (!query.equals("")) {
            //      criticsDao模糊查询结果
            List<MovieCritics> list = criticsDao.queryBuilder().whereOr(MovieCriticsDao.Properties.Name.like("%" + query + "%")
                    , MovieCriticsDao.Properties.Critics.like("%" + query + "%")).orderDesc(MovieCriticsDao.Properties.CreateTime)
                    .list();
            showCriticsList(list, getActivity(), 0);
        }
    }

    @OnClick(R.id.fab_add)
    public void onClick() {
        Intent intent = new Intent(getActivity(), WriteCriticsActivity.class);
        startActivity(intent);
        //实现淡入浅出的页面跳转效果
        // getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        //由左向右滑入的效果
        //getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        //实现zoommin 和 zoomout,即类似iphone的进入和退出时的效果
        //getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

}

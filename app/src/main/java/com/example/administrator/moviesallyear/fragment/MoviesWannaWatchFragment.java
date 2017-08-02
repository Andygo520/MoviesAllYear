package com.example.administrator.moviesallyear.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.MoviesAllYearApplication;
import com.example.administrator.moviesallyear.R;
import com.greendao.gen.MoviesWannaWatchDao;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helper.SnackbarHelper;
import model.MoviesWannaWatch;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesWannaWatchFragment extends Fragment {
    private List<MoviesWannaWatch> data = new ArrayList<>();
    private MoviesWannaWatchDao wannaWatchDao;
    private WannaWatchAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public MoviesWannaWatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_wanna_see, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init() {
        //        得到一个dao对象，用来操作数据库
        wannaWatchDao = MoviesAllYearApplication.getInstances().getDaoSession().getMoviesWannaWatchDao();
        data = wannaWatchDao.queryBuilder().where(MoviesWannaWatchDao.Properties.Watched.eq(false)).list();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WannaWatchAdapter(getActivity(), data, R.layout.item_movies_wanna_watch);
        recyclerView.setAdapter(adapter);
    }

    class WannaWatchAdapter extends SuperAdapter<MoviesWannaWatch> {

        private List<MoviesWannaWatch> items = new ArrayList<>();

        public WannaWatchAdapter(Context context, List<MoviesWannaWatch> items, int layoutResId) {
            super(context, items, layoutResId);
        }

        @Override
        public void onBind(SuperViewHolder holder, int viewType, final int layoutPosition, final MoviesWannaWatch item) {
//          如果电影名超过16个字符则设置显示样式为“16字符+...”
            if (item.getName().length() > 16)
                holder.setText(R.id.name, item.getName().substring(0, 16) + "...");
            else
                holder.setText(R.id.name, item.getName());
            holder.setText(R.id.date, item.getDate());
            CheckBox checkBox = holder.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        long time = System.currentTimeMillis();
                        Date date = new Date(time);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        Log.d("layoutPosition", layoutPosition + "");
                        data.remove(layoutPosition);
                        adapter.notifyItemRemoved(layoutPosition);
                        adapter.notifyItemRangeChanged(layoutPosition, data.size() - layoutPosition);
//                        更新数据的时候主键key不变
                        wannaWatchDao.update(new MoviesWannaWatch(item.getId(), item.getName(), sdf.format(date), true));
//                     使用Snackbar进行提示
                        SnackbarHelper.ShortSnackbar(recyclerView, "条目已转移到看过", SnackbarHelper.Info).show();
                    }
                }
            });
        }
    }
}

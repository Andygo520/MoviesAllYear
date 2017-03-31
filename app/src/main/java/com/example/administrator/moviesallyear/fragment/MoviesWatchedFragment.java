package com.example.administrator.moviesallyear.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.MoviesAllYearApplication;
import com.example.administrator.moviesallyear.R;
import com.greendao.gen.MoviesWannaWatchDao;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.MoviesWannaWatch;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesWatchedFragment extends Fragment {
    private List<MoviesWannaWatch> data = new ArrayList<>();
    private MoviesWannaWatchDao wannaWatchDao;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public MoviesWatchedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_watched, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init() {
        //        得到一个dao对象，用来操作数据库
        wannaWatchDao = MoviesAllYearApplication.getInstances().getDaoSession().getMoviesWannaWatchDao();
        data = wannaWatchDao.queryBuilder().where(MoviesWannaWatchDao.Properties.Watched.eq(true)).list();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new WannaWatchAdapter(getActivity(), data, R.layout.item_movies_wanna_watch));
    }

    class WannaWatchAdapter extends SuperAdapter<MoviesWannaWatch> {

        public WannaWatchAdapter(Context context, List<MoviesWannaWatch> items, int layoutResId) {
            super(context, items, layoutResId);
        }

        @Override
        public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, final MoviesWannaWatch item) {
            holder.setText(R.id.name, item.getName());
            holder.setText(R.id.date, item.getDate());
            CheckBox checkBox = holder.findViewById(R.id.checkBox);
            checkBox.setVisibility(View.GONE);
        }
    }

}

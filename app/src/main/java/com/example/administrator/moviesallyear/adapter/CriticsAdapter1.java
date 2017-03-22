package com.example.administrator.moviesallyear.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.moviesallyear.R;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import org.byteam.superadapter.IMulItemViewType;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

import model.MovieCritics;

/**
 * Created by Administrator on 2017/3/22.
 */

public class CriticsAdapter1 extends SuperAdapter<MovieCritics> {
    public CriticsAdapter1(Context context, List<MovieCritics> items, IMulItemViewType<MovieCritics> mulItemViewType) {
        super(context, items, mulItemViewType);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MovieCritics item) {
        ImageView ivDouban;
        holder.setText(R.id.tv_name,item.getName());
        holder.setText(R.id.tv_content,item.getCritics());
        holder.setText(R.id.tv_createTime,item.getCreateTime());
        ImageView imageView=holder.findViewById(R.id.iv_douban);
        SimpleRatingBar ratingBar=holder.findViewById(R.id.ratingBar);
        ratingBar.setRating(item.getStars());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();
            }
        });
        switch (viewType){
            case 0:
                break;
            case 1:
                holder.setText(R.id.tv_date,item.getCreateTime().substring(0,7));
                break;
        }
    }
}

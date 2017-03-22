package com.example.administrator.moviesallyear.adapter;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.widget.RatioImageView;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

import model.Top250Movie;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Top250Adapter extends SuperAdapter<Top250Movie.SubjectsBean> {
    public Top250Adapter(Context context, List<Top250Movie.SubjectsBean> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, Top250Movie.SubjectsBean item) {
        holder.setText(R.id.tv, item.getTitle()+"  "+item.getRating().getAverage());
        RatioImageView imageView = holder.findViewById(R.id.iv);
        imageView.setOriginalSize(50,50);  //设置图片初始长宽
        final View movie = holder.findViewById(R.id.movie_card);
        Glide.with(getContext())
                .load(item.getImages().getLarge())
                .fitCenter()
                .into(imageView)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (!movie.isShown()){
                            movie.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }
}

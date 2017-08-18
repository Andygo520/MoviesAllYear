package com.example.administrator.moviesallyear.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.widget.RatioImageView;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/8/18.
 */
public class Douban250ViewBinder extends ItemViewBinder<Douban250, Douban250ViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_douban250, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Douban250 douban250) {
        Context context = holder.image.getContext();
        Glide.with(context)
                .load(douban250.getImages().getLarge())
                .into(holder.image);
        int position = 1;
        holder.tvName.setText(position + "." + douban250.getTitle());
        position++;
        holder.tvRating.setText(douban250.getRating().getAverage() + "");
        List<Douban250.CastsBean> actorList = douban250.getCasts();
        String actor = "";
        for (Douban250.CastsBean castsBean : actorList) {
            actor += castsBean.getName() + "，";
        }
        holder.tvActor.setText(context.getString(R.string.casts) + actor.substring(0, actor.length() - 1));
        holder.ratingBar.setRating((float) douban250.getRating().getAverage());
        holder.ratingBar.setEnabled(false);//不能更改评分
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        RatioImageView image;
        @NonNull
        TextView tvName;
        @NonNull
        TextView tvRating;
        @NonNull
        TextView tvActor;
        @NonNull
        SimpleRatingBar ratingBar;

        ViewHolder(View itemView) {
            super(itemView);
            image = (RatioImageView) itemView.findViewById(R.id.image);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvRating = (TextView) itemView.findViewById(R.id.tvRating);
            tvActor = (TextView) itemView.findViewById(R.id.tvActor);
            ratingBar = (SimpleRatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }
}

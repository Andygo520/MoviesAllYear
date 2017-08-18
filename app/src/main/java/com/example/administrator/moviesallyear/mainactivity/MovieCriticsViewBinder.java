package com.example.administrator.moviesallyear.mainactivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.moviesallyear.R;

import me.drakeet.multitype.ItemViewBinder;
import model.MovieCritics;

/**
 * Created by Administrator on 2017/8/16.
 */
public class MovieCriticsViewBinder extends ItemViewBinder<MovieCritics, MovieCriticsViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_movie_critics, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MovieCritics movieCritics) {
        holder.tvContent.setText(movieCritics.getName() + "\n"
                + movieCritics.getCritics());
        holder.image.setImageResource(R.mipmap.ic_launcher);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        private ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}

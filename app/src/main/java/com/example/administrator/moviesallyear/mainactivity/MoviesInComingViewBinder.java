package com.example.administrator.moviesallyear.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.widget.RatioImageView;

import me.drakeet.multitype.ItemViewBinder;
import model.MoviesInTheater;

/**
 * Created by Administrator on 2017/8/16.
 */
public class MoviesInComingViewBinder extends ItemViewBinder<MoviesInTheater, MoviesInComingViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_movies_in_theater, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MoviesInTheater moviesInComing) {
        Context context = holder.image.getContext();
        holder.title.setText(moviesInComing.getTitle() + "  " + moviesInComing.getRating().getAverage());
        Glide.with(context)
                .load(moviesInComing.getImages().getLarge())
                .into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        RatioImageView image;
        @NonNull
        TextView title;
        @NonNull
        LinearLayout card;

        ViewHolder(View itemView) {
            super(itemView);
            image = (RatioImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            card = (LinearLayout) itemView.findViewById(R.id.movie_card);
        }
    }
}

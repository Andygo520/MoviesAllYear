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
import com.example.administrator.moviesallyear.webview.WebViewActivity;
import com.example.administrator.moviesallyear.widget.RatioImageView;

import java.util.List;

import model.MoviesInTheater;

/**
 * Created by Administrator on 2017/8/17.
 */

public class MoviesInTheaterAdapter extends RecyclerView.Adapter<MoviesInTheaterAdapter.ViewHolder> {

    private List<MoviesInTheater> posts;


    public void setMoviesInTheaters(@NonNull List<MoviesInTheater> posts) {
        this.posts = posts;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies_in_theater, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MoviesInTheater post = posts.get(position);
        final Context context = holder.image.getContext();
        holder.title.setText(post.getTitle() + "  " + post.getRating().getAverage());
        Glide.with(context)
                .load(post.getImages().getLarge())
                .into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                第二个参数为电影条目url，第三个为电影标题
                WebViewActivity.loadUrl(context, post.getAlt(), post.getTitle());
            }
        });
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
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

package com.example.administrator.moviesallyear.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.moviesallyear.R;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import model.HorizontalMovieList;
import model.MoviesInTheater;

/**
 * Created by Administrator on 2017/8/16.
 */
public class HorizontalMoviesViewBinder extends ItemViewBinder<HorizontalMovieList, HorizontalMoviesViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_movie_list, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HorizontalMovieList movieList) {
        holder.setPosts(movieList.list);
        assertGetAdapterNonNull();
    }

    /**
     * Just test
     */
    private void assertGetAdapterNonNull() {
        if (getAdapter() == null) {
            throw new NullPointerException("getAdapter() == null");
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private MoviesInTheaterAdapter adapter;

        private ViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.horizontalRecyclerView);
            Context context = recyclerView.getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            adapter = new MoviesInTheaterAdapter();
            recyclerView.setAdapter(adapter);
        }

        private void setPosts(List<MoviesInTheater> posts) {
            adapter.setMoviesInTheaters(posts);
            adapter.notifyDataSetChanged();
        }
    }
}

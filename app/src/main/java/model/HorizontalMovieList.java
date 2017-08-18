package model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class HorizontalMovieList {
    public @NonNull List<MoviesInTheater> list;

    public HorizontalMovieList(@NonNull List<MoviesInTheater> list) {
        this.list = list;
    }
}

package com.example.administrator.moviesallyear.mainactivity;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/8/16.
 */
public class Category {
    @NonNull
    String categoryItem;
    @NonNull
    String num;

    public Category(@NonNull String categoryItem, @NonNull String num) {
        this.categoryItem = categoryItem;
        this.num = num;
    }
}
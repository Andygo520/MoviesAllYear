package com.example.administrator.moviesallyear.mainactivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.moviesallyear.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/8/16.
 */
public class CategoryViewBinder extends ItemViewBinder<Category, CategoryViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category category) {
        holder.category.setText(category.categoryItem);
        holder.num.setText(category.num);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView category;
        private TextView num;

        ViewHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.tvCategory);
            num = (TextView) itemView.findViewById(R.id.tvNum);
        }
    }
}

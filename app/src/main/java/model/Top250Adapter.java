package model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Top250Adapter extends SuperAdapter<MovieItem> {
    public Top250Adapter(Context context, List<MovieItem> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MovieItem item) {
        holder.setText(R.id.tv, item.getTitle());
        ImageView imageView = holder.findViewById(R.id.iv);
        Glide.with(getContext())
                .load(item.getImageUrl())
                .fitCenter()
                .into(imageView);
    }
}

package model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class MovieAdapter extends SuperAdapter<MovieItem> {

    public MovieAdapter(Context context, List<MovieItem> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MovieItem item) {
//        显示电影名
        holder.setText(R.id.item_title_tv,item.getTitle());
//        显示演员
        List<String> casts=new ArrayList<>();
        String actor="";
        casts=item.getCasts();
        for (String cast : casts) {
            actor+=cast+"，";
        }
        holder.setText(R.id.item_cast_tv,actor.substring(0,actor.length()-1));
//        显示图片
        ImageView image=holder.findViewById(R.id.item_iv);
        //        使用Glide库加载图片,图片缩放来显示整张图
        Glide.with(getContext())
                .load(item.getImageUrl())
                .fitCenter()
                .into(image);
    }
}

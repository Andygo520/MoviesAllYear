package model;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.widget.RatioImageView;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * Created by andy on 2017/3/21.
 */

public class DialogAdapter extends SuperAdapter<Top250Movie.SubjectsBean> {
    public DialogAdapter(Context context, List<Top250Movie.SubjectsBean> items, int layoutResId) {
        super(context, items, layoutResId);
    }


    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, Top250Movie.SubjectsBean item) {
        holder.setText(R.id.tv, item.getTitle() + "   " +
                item.getYear()
        );
        RatioImageView imageView=holder.findViewById(R.id.iv);
        imageView.setOriginalSize(50,55);
        Glide.with(getContext())
                .load(item.getImages().getLarge())
                .centerCrop()
                .into(imageView);
    }
}

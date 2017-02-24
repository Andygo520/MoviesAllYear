package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/28.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<String> titleList;
    private List<String> castList;
    private List<String> imageUrlList;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int positon);
    }

    public MoviesAdapter(List<String> titleList, List<String> castList, List<String> imageUrlList, Context context) {
        this.titleList = titleList;
        this.castList = castList;
        this.imageUrlList = imageUrlList;
        this.context = context;
    }

    //  处理单击事件
    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    //  处理长按事件
    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(view, (int) view.getTag());
            return true;
        } else
            return false;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(titleList.get(position));
        holder.tvCast.setText(castList.get(position));
//        使用Glide库加载图片,图片缩放来显示整张图
        Glide.with(context)
                .load(imageUrlList.get(position))
                .fitCenter()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title_tv)
        TextView tvTitle;
        @BindView(R.id.item_cast_tv)
        TextView tvCast;
        @BindView(R.id.item_iv)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

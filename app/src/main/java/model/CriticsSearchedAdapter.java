package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.moviesallyear.R;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class CriticsSearchedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<MovieCritics> movieList;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int positon);

    }

    public CriticsSearchedAdapter(List<MovieCritics> movieList, Context context) {
        this.movieList = movieList;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.critics_item, parent, false);
            CriticsHolder holder = new CriticsHolder(view);
            //将创建的View注册点击事件
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将数据与item视图进行绑定
            ((CriticsHolder) holder).tvName.setText(movieList.get(position).getName());
            ((CriticsHolder) holder).tvContent.setText(movieList.get(position).getCritics());
            ((CriticsHolder) holder).tvTime.setText(movieList.get(position).getCreateTime().substring(5,16));//只显示月-日 时：分字段
            ((CriticsHolder) holder).ratingBar.setRating(movieList.get(position).getStars());
            ((CriticsHolder) holder).ratingBar.setIndicator(true);// 列表不能修改评分
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class CriticsHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvContent, tvTime;
        SimpleRatingBar ratingBar;

        public CriticsHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvTime = (TextView) itemView.findViewById(R.id.tv_createTime);
            ratingBar = (SimpleRatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }
}

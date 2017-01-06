package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.moviesallyear.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */

//为RecyclerView的每个子item设置setOnClickListener，然后在onClick中再调用一次对外封装的接口，将这个事件传递给外面的调用者。而“为RecyclerView的每个子item设置setOnClickListener”在Adapter中设置。
public class CriticsAdapter extends RecyclerView.Adapter<CriticsAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<MovieCritics> movieList;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int positon);

    }

    public CriticsAdapter(List<MovieCritics> movieList,Context context) {
        this.movieList = movieList;
        this.context=context;
    }
    //  处理单击事件
    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    //  处理长按事件
    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickListener!=null){
            mOnItemClickListener.onItemLongClick(view, (int) view.getTag());
            return true;
        }else
            return false;
    }



    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_critics_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(movieList.get(position).getName());
        holder.tvContent.setText(movieList.get(position).getCritics());
        holder.tvTime.setText(movieList.get(position).getCreateTime());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvContent,tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvTime = (TextView) itemView.findViewById(R.id.tv_createTime);
        }
    }
}

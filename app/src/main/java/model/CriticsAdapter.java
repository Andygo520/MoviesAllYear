package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.moviesallyear.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */

//为RecyclerView的每个子item设置setOnClickListener，然后在onClick中再调用一次对外封装的接口，将这个事件传递给外面的调用者。而“为RecyclerView的每个子item设置setOnClickListener”在Adapter中设置。
public class CriticsAdapter extends RecyclerView.Adapter<CriticsAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<String> nameList;
    private List<String> contentList;


    //    设置每个item view的高度为随机值，实现瀑布流效果
    private List<Integer> height;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int positon);

    }

    public CriticsAdapter(List<String> nameList, List<String> contentList,Context context) {
        this.nameList = nameList;
        this.contentList = contentList;
        this.context=context;

        //随机获取一个height值
        height=new ArrayList<>();
        for (int j=0;j<nameList.size();j++){
            height.add( (int) (100 + Math.random() * 300));
        }
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

    //移除数据
    public void removeData(int position) {
        nameList.remove(position);
        contentList.remove(position);
        notifyItemRemoved(position);
//     表示从当前移除的位置后面的item的position要相应的更新
        notifyItemRangeChanged(position,nameList.size()-position);
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
        holder.tvName.setText(nameList.get(position));
        holder.tvContent.setText(contentList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}

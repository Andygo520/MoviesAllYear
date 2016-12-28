package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.administrator.moviesallyear.R;

import java.util.ArrayList;
import java.util.List;

import helper.VolleyHelper;

/**
 * Created by Administrator on 2016/12/28.
 */

//为RecyclerView的每个子item设置setOnClickListener，然后在onClick中再调用一次对外封装的接口，将这个事件传递给外面的调用者。而“为RecyclerView的每个子item设置setOnClickListener”在Adapter中设置。
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<String> titleList;
    private List<String> castList;
    private List<String> imageUrlList;


    //    设置每个item view的高度为随机值，实现瀑布流效果
    private List<Integer> height;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int positon);

    }

    public MyAdapter(List<String> titleList, List<String> castList,List<String> imageUrlList,Context context) {
        this.titleList = titleList;
        this.castList = castList;
        this.imageUrlList=imageUrlList;
        this.context=context;

        //随机获取一个height值
        height=new ArrayList<>();
        for (int j=0;j<titleList.size();j++){
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
        titleList.remove(position);
        castList.remove(position);
        notifyItemRemoved(position);
//     表示从当前移除的位置后面的item的position要相应的更新
        notifyItemRangeChanged(position,titleList.size()-position);
    }

    //新增数据
//    public void addData(int position){
//        titleList.add(position,"Add One");
//        notifyItemInserted(position);
//        notifyItemRangeChanged(position,titleList.size()-position);
//    }
    //更改某个位置的数据
//    public void changeData(int position){
//        titleList.set(position,"Item has changed"+ count++);
//        notifyItemChanged(position);
//        notifyItemRangeChanged(position,titleList.size()-position);
//    }

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
        VolleyHelper.showImageByUrl(context,imageUrlList.get(position),holder.imageView);
        //绑定数据的同时，修改每个ItemView的高度,瀑布流的时候用到
//        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//        lp.height = height.get(position);
//        holder.itemView.setLayoutParams(lp);

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        holder.tvCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"你点击了第"+position+"位置的内容",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCast;
        NetworkImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.item_title_tv);
            tvCast = (TextView) itemView.findViewById(R.id.item_cast_tv);
            imageView = (NetworkImageView) itemView.findViewById(R.id.item_iv);
        }
    }
}

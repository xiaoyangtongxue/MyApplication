package m;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import luoboman.www.luobo.com.myapplication.R;

/**
 * Created by Administrator on 2017/10/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<bean.SongListBean> list=new ArrayList<>();
    Context context;
    View view;


    //这个很重要  参数顺序
    public MyAdapter(List<bean.SongListBean> list, Context context) {
        this.list=list;
        this.context = context;
    }
    //定义如下接口  一
    public interface OnItemClieckLinster{

        void onItemClickListener(View view , int pos);
        void onItemLongClickListener(View view , int pos);
    }
    private  OnItemClieckLinster onItemClieckLinster;

    public void setOnItemClieckLinster(OnItemClieckLinster listener){

        this.onItemClieckLinster = listener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        ViewHolder vh = new ViewHolder(view);
        return vh;


    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        holder.text.setText(list.get(position).getTitle());
        holder.text2.setText(list.get(position).getArtist_name());
        ImageLoader.getInstance().displayImage(list.get(position).getPic_big(),holder.img);
        if(onItemClieckLinster != null){

            //onitemclicklistener
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClieckLinster.onItemClickListener(holder.itemView , position);
                }
            });

            //onitemlongclicklistener
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    onItemClieckLinster.onItemLongClickListener(holder.itemView , position);
                    return false;
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView text,text2;
        public ViewHolder(View itemView) {
            super(itemView);
            img =  view.findViewById(R.id.img2);
            text =  view.findViewById(R.id.text3);
            text2 =  view.findViewById(R.id.text4);

        }
    }
}
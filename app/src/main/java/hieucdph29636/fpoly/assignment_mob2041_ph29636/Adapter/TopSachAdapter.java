package hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet.Chitiet_LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.TopSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class TopSachAdapter extends RecyclerView.Adapter<TopSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TopSach> list;
    private int i =0;
    public TopSachAdapter(Context context, ArrayList<TopSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_topsach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index_ = position;
        i++;
            holder.tv_ten.setText(list.get(index_).getTenSachTop());
            holder.tv_sl.setText(list.get(index_).getSoLuong()+"");
            holder.tv_top.setText(""+i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_top,tv_ten,tv_sl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_top = itemView.findViewById(R.id.top);
            tv_ten = itemView.findViewById(R.id.tv_tensach_top);
            tv_sl = itemView.findViewById(R.id.tv_soluong);
        }
    }
}

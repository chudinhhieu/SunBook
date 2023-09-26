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
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index_ = position;
            holder.tv_tenloaisach.setText(list.get(index_).getTenLoai());
            holder.tv_chitiet_ls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Chitiet_LoaiSach.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id_ls",list.get(index_).getMaLoai());
                    bundle.putString("ten_ls",list.get(index_).getTenLoai());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_loaisach;
        TextView tv_tenloaisach,tv_chitiet_ls;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_loaisach = itemView.findViewById(R.id.img_loaisach);
            tv_tenloaisach = itemView.findViewById(R.id.tv_tenloaisach);
            tv_chitiet_ls = itemView.findViewById(R.id.tv_chitiet_ls);
        }
    }
}

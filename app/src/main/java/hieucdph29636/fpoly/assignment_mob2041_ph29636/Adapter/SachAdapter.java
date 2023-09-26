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
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet.Chitiet_Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    Context context;
    ArrayList<Sach> list;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach,parent,false);
        return new SachAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index_ = position;
        holder.tv_tenSach.setText(list.get(position).getTenSach());
        holder.tv_giaThue.setText(list.get(position).getGiaThue()+"");
        holder.tv_chiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chitiet_Sach.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_s",list.get(index_).getMaSach());
                bundle.putInt("id_ls",list.get(index_).getMaLoai());
                bundle.putString("ten_s",list.get(index_).getTenSach());
                bundle.putInt("gia",list.get(index_).getGiaThue());
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
        TextView tv_tenSach;
        TextView tv_giaThue;
        TextView tv_chiTiet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenSach = itemView.findViewById(R.id.tv_tensach);
            tv_giaThue = itemView.findViewById(R.id.tv_giathue);
            tv_chiTiet = itemView.findViewById(R.id.tv_chitiet_sach);
        }
    }
}

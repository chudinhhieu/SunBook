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

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet.Chitiet_PhieuMuon;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet.Chitiet_Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.PhieuMuon;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.MyDate;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder>{
    private ArrayList<PhieuMuon> list;
    private Context context;
    MyDate myDate;
    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_pm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index_ = position;
            holder.tv_Ten.setText(list.get(position).getTenTV()+"");
            holder.tv_tenSach.setText(list.get(position).getTenSach()+"");
            holder.tv_ct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Chitiet_PhieuMuon.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("maPM",list.get(index_).getMaPM());
                    bundle.putString("tenTT",list.get(index_).getHoTenTT());
                    bundle.putString("tenTV",list.get(index_).getTenTV());
                    bundle.putString("tenSach",list.get(index_).getTenSach());
                    bundle.putInt("giaThue",list.get(index_).getTienThue());
                    bundle.putSerializable("ngayMuon", list.get(index_).getNgay());
                    bundle.putInt("trangThai",list.get(index_).getTrangThai());
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
        TextView tv_tenSach,tv_Ten,tv_ct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenSach = itemView.findViewById(R.id.tv_tensach_pm);
            tv_Ten = itemView.findViewById(R.id.tv_tenTV_pm);
            tv_ct = itemView.findViewById(R.id.tv_chitiet_pm);
        }
    }
}

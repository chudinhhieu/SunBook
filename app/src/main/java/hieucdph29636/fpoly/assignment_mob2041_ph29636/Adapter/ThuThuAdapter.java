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

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet.ChiTiet_ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet.Chitiet_LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ThuThu> list;

    public ThuThuAdapter(Context context, ArrayList<ThuThu> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thuthu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index_ = position;
            holder.tv_tenThuThu.setText(list.get(index_).getHoTen());
            holder.tv_chitiet_tt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTiet_ThuThu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id_tt",list.get(index_).getMaTT());
                    bundle.putString("ten_tt",list.get(index_).getHoTen());
                    bundle.putString("mk_tt",list.get(index_).getMatKhau());
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
        ImageView img_thuThu;
        TextView tv_tenThuThu,tv_chitiet_tt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_thuThu = itemView.findViewById(R.id.img_thuThu);
            tv_tenThuThu = itemView.findViewById(R.id.tv_tenThuThu);
            tv_chitiet_tt = itemView.findViewById(R.id.tv_chitiet_tt);
        }
    }
}

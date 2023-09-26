package hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class AdapterSpinerSach extends BaseAdapter {
    Context context;
    ArrayList<Sach> list;

    public AdapterSpinerSach(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        ViewOfItem viewOfItem = null;
        if (view == null){
            view = layoutInflater.inflate(R.layout.layout_item_loaisachspiner, null);
            viewOfItem = new ViewOfItem();
            viewOfItem.tvTenLoaiSach = view.findViewById(R.id.tvTen);
            view.setTag(viewOfItem);
        }else {
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.tvTenLoaiSach.setText(list.get(i).getTenSach());
        return view;
    }

    public static class ViewOfItem {
        TextView tvTenLoaiSach;
    }

}

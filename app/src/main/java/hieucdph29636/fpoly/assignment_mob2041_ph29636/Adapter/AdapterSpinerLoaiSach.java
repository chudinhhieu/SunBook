package hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class AdapterSpinerLoaiSach extends BaseAdapter {
    Context mContext;
    ArrayList<LoaiSach> mListLoaiSach;

    public AdapterSpinerLoaiSach(Context mContext, ArrayList<LoaiSach> mListLoaiSach) {
        this.mContext = mContext;
        this.mListLoaiSach = mListLoaiSach;

    }

    @Override
    public int getCount() {
        return mListLoaiSach.size();
    }

    @Override
    public Object getItem(int i) {
        return mListLoaiSach.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = ((Activity) mContext).getLayoutInflater();
        ViewOfItem viewOfItem = null;
        if (view == null){
            view = layoutInflater.inflate(R.layout.layout_item_loaisachspiner, null);
            viewOfItem = new ViewOfItem();
            viewOfItem.tvTenLoaiSach = view.findViewById(R.id.tvTen);
            view.setTag(viewOfItem);
        }else {
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.tvTenLoaiSach.setText(mListLoaiSach.get(i).getTenLoai());
        return view;
    }

    public static class ViewOfItem {
        TextView tvTenLoaiSach;
    }


}

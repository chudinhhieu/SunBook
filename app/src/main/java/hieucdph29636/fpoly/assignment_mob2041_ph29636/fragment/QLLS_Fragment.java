package hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.LoaiSachAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.PhieuMuonAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class QLLS_Fragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    LoaiSachDAO dao;
    ArrayList<LoaiSach> list;
    LoaiSachAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlls,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_qlls);
        floatingActionButton = view.findViewById(R.id.flbtn_add_qlls);
        loadData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_loaisach);
                TextInputEditText tenLoaiSach =  dialog.findViewById(R.id.ed_them_tenloaisach);
                TextInputLayout inputLayout =  dialog.findViewById(R.id.edL_them_tenloaisach);
                Button btn_Them = dialog.findViewById(R.id.btn_them_loaisach);
                Button btn_Huy = dialog.findViewById(R.id.btn_huy_loaisach);
                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tenLoaiSach.getText().toString().isEmpty()){
                            inputLayout.setError("Vui lòng nhập tên loại sách!");
                            return;
                        }else {
                            inputLayout.setError("");
                        }
                        LoaiSach loaiSach = new LoaiSach();
                        loaiSach.setTenLoai(tenLoaiSach.getText().toString());
                        if(dao.insert(loaiSach)>0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
    }
    private void loadData(){
        dao = new LoaiSachDAO(getContext());
        list = dao.getAll();
        adapter = new LoaiSachAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tim_kiem, menu);

        // Lấy thanh tìm kiếm
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();


        // Đặt Listener cho thanh tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Xử lý khi người dùng nhấn Submit (hoặc Enter) trên bàn phím
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter = new LoaiSachAdapter(getContext(),dao.getTen(query));
                recyclerView.setAdapter(adapter);
                return true;
            }

            // Xử lý khi người dùng thay đổi nội dung trên thanh tìm kiếm
            @Override
            public boolean onQueryTextChange(String newText) {
                loadData();
                return true;
            }
        });
    }

}

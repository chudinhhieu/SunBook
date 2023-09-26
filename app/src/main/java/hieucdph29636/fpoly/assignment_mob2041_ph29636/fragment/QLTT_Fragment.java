package hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.ThanhVienAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.ThuThuAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThuThuDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class QLTT_Fragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ThuThuDAO dao;
    ArrayList<ThuThu> list;
    ThuThuAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qltt,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_qltt);
        floatingActionButton = view.findViewById(R.id.flbtn_add_qltt);
        loadData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_tt);
                TextInputEditText maTT =  dialog.findViewById(R.id.ed_them_maTT);
                TextInputEditText hoTen =  dialog.findViewById(R.id.ed_them_hoTenTT);
                TextInputEditText matKhau =  dialog.findViewById(R.id.ed_them_matkhau);
                TextInputLayout edL_maTT =  dialog.findViewById(R.id.edL_them_maTT);
                TextInputLayout edL_hoTen =  dialog.findViewById(R.id.edL_them_hoTenTT);
                TextInputLayout edL_matKhau =  dialog.findViewById(R.id.edL_them_matkhau);
                Button btn_Them = dialog.findViewById(R.id.btn_them_tt);
                Button btn_Huy = dialog.findViewById(R.id.btn_huy_tt);
                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (maTT.getText().toString().isEmpty()){
                            edL_maTT.setError("Vui lòng nhập mã thủ thư!");
                            return;
                        }else {
                            edL_maTT.setError("");
                        }
                        if (hoTen.getText().toString().isEmpty()){
                            edL_hoTen.setError("Vui lòng nhập họ tên!");
                            return;
                        }else {
                            edL_hoTen.setError("");
                        }
                        if (matKhau.getText().toString().isEmpty()){
                            edL_matKhau.setError("Vui lòng mật khẩu!");
                            return;
                        }else {
                            edL_matKhau.setError("");
                        }
                        ThuThu thuThu = new ThuThu();
                        thuThu.setMaTT(maTT.getText().toString());
                        thuThu.setHoTen(hoTen.getText().toString());
                        thuThu.setMatKhau(matKhau.getText().toString());
                        if(dao.insert(thuThu)>0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }else {
                            edL_maTT.setError("Mã thủ thư đã tồn tại,vui lòng nhập mã khác!");
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
    }
    private void loadData(){
        dao = new ThuThuDAO(getContext());
        list = dao.getAll();
        adapter = new ThuThuAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
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
                adapter = new ThuThuAdapter(getContext(),dao.getTenTT(query));
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

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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerLoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.LoaiSachAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.SachAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.SachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class QLS_Fragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    LoaiSachDAO loaiSachDAO;
    SachDAO dao;
    ArrayList<LoaiSach> listLS;
    ArrayList<Sach> list;
    SachAdapter adapter;
    AdapterSpinerLoaiSach adapterSpiner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qls,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_qls);
        floatingActionButton = view.findViewById(R.id.flbtn_add_qls);
        loadData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSachDAO = new LoaiSachDAO(getContext());
                listLS = loaiSachDAO.getAll();
                if (listLS.size()==0) {
                    Toast.makeText(getContext(), "Vui lòng nhập loại sách trước!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.dialog_themsach);
                    TextInputEditText tenSach = dialog.findViewById(R.id.ed_them_tensach);
                    TextInputEditText giathue = dialog.findViewById(R.id.ed_them_giathue);
                    TextInputLayout edL_tensach = dialog.findViewById(R.id.edL_them_tensach);
                    TextInputLayout edL_giathue = dialog.findViewById(R.id.edL_them_giathue);
                    Spinner spinner = dialog.findViewById(R.id.spn_loaisach);
                    AdapterSpinerLoaiSach adapterSpiner = new AdapterSpinerLoaiSach(getContext(), listLS);
                    spinner.setAdapter(adapterSpiner);
                    Button btn_Them = dialog.findViewById(R.id.btn_them_sach);
                    Button btn_Huy = dialog.findViewById(R.id.btn_huy_sach);
                    btn_Huy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    btn_Them.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (tenSach.getText().toString().isEmpty()) {
                                edL_tensach.setError("Vui lòng nhập tên sách!");
                                return;
                            } else {
                                edL_tensach.setError("");
                            }
                            if (giathue.getText().toString().isEmpty()) {
                                edL_giathue.setError("Vui lòng nhập giá thuê!");
                                return;
                            } else {
                                edL_giathue.setError("");
                            }
                            Sach sach = new Sach();
                            sach.setTenSach(tenSach.getText().toString());
                            sach.setGiaThue(Integer.valueOf(giathue.getText().toString()));
                            LoaiSach loaiSach = (LoaiSach) spinner.getSelectedItem();
                            sach.setMaLoai(loaiSach.getMaLoai());
                            if (dao.insert(sach) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                loadData();
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                }
            }
        });
    }
    private void loadData(){
        dao = new SachDAO(getContext());
        list = dao.getAll();
        adapter = new SachAdapter(getContext(),list);
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
                adapter = new SachAdapter(getContext(),dao.getTenSach(query));
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

package hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.SachAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.ThanhVienAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThanhVienDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.MyDate;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class QLTV_Fragment extends Fragment {
    RecyclerView recyclerView;
    MyDate date;
    FloatingActionButton floatingActionButton;
    ThanhVienDAO dao;
    ArrayList<ThanhVien> list;
    ThanhVienAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qltv,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_qltv);
        floatingActionButton = view.findViewById(R.id.flbtn_add_qltv);
        loadData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_themtv);
                TextInputEditText tenThanhVien =  dialog.findViewById(R.id.ed_them_tentv);
                TextInputEditText namsinh =  dialog.findViewById(R.id.ed_them_ngaysinh);
                TextInputLayout edL_tenThanhVien =  dialog.findViewById(R.id.edL_them_tentv);
                TextInputLayout edL_namsinh =  dialog.findViewById(R.id.edL_them_ngaysinh);
                Button btn_Them = dialog.findViewById(R.id.btn_them_tv);
                Button btn_Huy = dialog.findViewById(R.id.btn_huy_tv);
                namsinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar.set(i, i1, i2);
                                namsinh.setText(date.toStringVn(calendar.getTime()));
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tenThanhVien.getText().toString().isEmpty()){
                            edL_tenThanhVien.setError("Vui lòng nhập tên thành viên!");
                            return;
                        }else  {
                            edL_tenThanhVien.setError("");
                        }
                        if (namsinh.getText().toString().isEmpty()){
                            edL_namsinh.setError("Vui lòng nhập ngày sinh!");
                            return;
                        }else  {
                            edL_namsinh.setError("");
                        }
                        ThanhVien thanhVien = new ThanhVien();
                        thanhVien.setHoTen(tenThanhVien.getText().toString());
                        thanhVien.setNamSinh(namsinh.getText().toString());
                        if(dao.insert(thanhVien)>0){
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
        dao = new ThanhVienDAO(getContext());
        list = dao.getAll();
        adapter = new ThanhVienAdapter(getContext(),list);
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
                adapter = new ThanhVienAdapter(getContext(),dao.getTen(query));
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

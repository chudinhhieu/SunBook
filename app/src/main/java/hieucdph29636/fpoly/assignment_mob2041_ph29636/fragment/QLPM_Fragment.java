package hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerLoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.PhieuMuonAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.SachAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.ThanhVienAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.PhieuMuonDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.SachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThanhVienDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThuThuDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.PhieuMuon;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.MyDate;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class QLPM_Fragment extends Fragment {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String ngayMuon = "";
    MyDate myDate;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    PhieuMuonDAO dao;
    ArrayList<Sach> listSach;
    ArrayList<PhieuMuon> list;
    ArrayList<ThanhVien> listTV;
    ArrayList<ThuThu> listTT;
    PhieuMuonAdapter adapter;
    AdapterSpinerLoaiSach adapterSpiner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlpm,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_qlpm);
        floatingActionButton = view.findViewById(R.id.flbtn_add_qlpm);
        loadData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVienDAO = new ThanhVienDAO(getContext());
                listTV = thanhVienDAO.getAll();
                sachDAO = new SachDAO(getContext());
                listSach = sachDAO.getAll();
                if(listTV.size()==0){
                    Toast.makeText(getContext(), "Vui lòng nhập thành viên trước !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(listSach.size()==0){
                    Toast.makeText(getContext(), "Vui lòng nhập sách trước !", Toast.LENGTH_SHORT).show();
                    return;
                }
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_pm);
                TextInputEditText ngay =  dialog.findViewById(R.id.ed_them_ngay);
                TextInputEditText giathue =  dialog.findViewById(R.id.ed_them_giathuepm);
                TextInputLayout edL_ngay =  dialog.findViewById(R.id.edL_them_ngay);
                ngay.setOnClickListener(new View.OnClickListener() {
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
                                ngay.setText(myDate.toStringVn(calendar.getTime()));
                                ngayMuon = myDate.toString(calendar.getTime());
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });
                TextInputLayout edL_giathue =  dialog.findViewById(R.id.edL_them_giathuepm);
                Spinner spinner_sach = dialog.findViewById(R.id.spn_sach);

                AdapterSpinerSach adapterSpiner = new AdapterSpinerSach(getContext(), listSach);
                spinner_sach.setAdapter(adapterSpiner);
                spinner_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Sach sach = (Sach) spinner_sach.getSelectedItem();
                        giathue.setText(String.valueOf(sach.getGiaThue()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                Spinner spinner_tv = dialog.findViewById(R.id.spn_tv);

                AdapterSpinerThanhVien adapterSpiner_tv = new AdapterSpinerThanhVien(getContext(), listTV);
                spinner_tv.setAdapter(adapterSpiner_tv);
                Button btn_Them = dialog.findViewById(R.id.btn_them_pm);
                Button btn_Huy = dialog.findViewById(R.id.btn_huy_pm);
                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ngay.getText().toString().isEmpty()){
                            edL_ngay.setError("Vui lòng nhập ngày mượn!");
                            return;
                        }else {
                            edL_ngay.setError("");
                        }

                        try {
                            PhieuMuon phieuMuon = new PhieuMuon();
                            ThanhVien thanhVien = (ThanhVien) spinner_tv.getSelectedItem();
                            Sach sach = (Sach) spinner_sach.getSelectedItem();
                            phieuMuon.setNgay(myDate.toDate(ngayMuon));
                            Log.d("oooooo", ""+phieuMuon.getNgay());
                            phieuMuon.setTienThue(Integer.valueOf(giathue.getText().toString()));
                            phieuMuon.setMaSach(sach.getMaSach());
                            phieuMuon.setTenSach(sach.getTenSach());
                            phieuMuon.setMaTV(thanhVien.getMaTV());
                            phieuMuon.setTenTV(thanhVien.getHoTen());
                            phieuMuon.setTrangThai(0);
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
                            String matt = sharedPreferences.getString("MaTT","");
                            phieuMuon.setMaTT(matt);
                            thuThuDAO = new ThuThuDAO(getContext());
                            listTT = thuThuDAO.getTen(matt);
                            phieuMuon.setHoTenTT(listTT.get(0).getHoTen());
                            if(dao.insert(phieuMuon)>0){
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                loadData();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
                dialog.show();
            }
        });
    }
    private void loadData(){
        dao = new PhieuMuonDAO(getContext());
        list = dao.getAll();
        adapter = new PhieuMuonAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
    private void showDateDialog() {

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
                adapter = new PhieuMuonAdapter(dao.getTenTV(query),getContext());
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

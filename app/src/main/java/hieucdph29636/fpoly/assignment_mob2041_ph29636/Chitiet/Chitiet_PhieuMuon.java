package hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerLoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.PhieuMuonDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.SachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThanhVienDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.PhieuMuon;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.MyDate;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class Chitiet_PhieuMuon extends AppCompatActivity {
    MyDate myDate;
    String ngayMuon="";
    TextView tv_maPM,tv_tenTT,tv_tenTV,tv_tenSach,tv_giaThue,tv_ngayMuon,tv_trangThai;
    Button btn_sua,btn_xoa;
    PhieuMuonDAO dao;
    ImageView img_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_phieu_muon);
        dao = new PhieuMuonDAO(this);
        img_exit = findViewById(R.id.img_exit_pm);
        tv_maPM = findViewById(R.id.tv_maPM_ctpm);
        tv_tenTT = findViewById(R.id.tv_tenTT_ctpm);
        tv_tenTV = findViewById(R.id.tv_tenTV_ctpm);
        tv_tenSach = findViewById(R.id.tv_tenSach_ctpm);
        tv_giaThue = findViewById(R.id.tv_giathue_ctpm);
        tv_ngayMuon = findViewById(R.id.tv_ngaymuon_ctpm);
        tv_trangThai = findViewById(R.id.tv_trangthai_ctpm);
        btn_sua = findViewById(R.id.btn_sua_pm);
        btn_xoa = findViewById(R.id.btn_xoa_pm);
        Bundle bundle = getIntent().getExtras();
        tv_maPM.setText(bundle.getInt("maPM")+"");
        tv_tenTT.setText(bundle.getString("tenTT"));
        tv_tenTV.setText(bundle.getString("tenTV"));
        tv_tenSach.setText(bundle.getString("tenSach"));
        tv_giaThue.setText(bundle.getInt("giaThue")+"");
        tv_ngayMuon.setText(myDate.toStringVn((Date) bundle.getSerializable("ngayMuon")));
        tv_trangThai.setText(bundle.getInt("trangThai")==1?"Đã trả":"Chưa trả");
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_PhieuMuon.this);
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tv_nd_xoa = dialog.findViewById(R.id.tv_nd_xoa);
                TextView tv_tieude= dialog.findViewById(R.id.tv_xoa);
                tv_tieude.setText("Xóa phiếu mượn");
                tv_nd_xoa.setText("Bạn chắc chắn muốn xóa phiếu mượn cửa ' "+tv_tenTV.getText().toString()+" 'này?");
                Button btn_xoa = dialog.findViewById(R.id.btn_dialog_xoa);
                Button btn_huy = dialog.findViewById(R.id.btn_dialog_huy);
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dao.delete(Integer.parseInt(tv_maPM.getText().toString()))>0){
                            Toast.makeText(Chitiet_PhieuMuon.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            onBackPressed();
                        }else {
                            Toast.makeText(Chitiet_PhieuMuon.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_PhieuMuon.this);
                dialog.setContentView(R.layout.dialog_sua_pm);
                TextView tv_them_pm = dialog.findViewById(R.id.tv_them_pm_sua);
                CheckBox checkBox = dialog.findViewById(R.id.chk_tt);
                TextInputEditText ngayThue =  dialog.findViewById(R.id.ed_them_ngay_sua);
                TextInputEditText giathue =  dialog.findViewById(R.id.ed_them_giathuepm_sua);
                TextInputLayout edL_ngayThue =  dialog.findViewById(R.id.edL_them_ngay_sua);
                TextInputLayout edL_giathue =  dialog.findViewById(R.id.edL_them_giathuepm_sua);
                ngayThue.setText(tv_ngayMuon.getText().toString());
                giathue.setText(tv_giaThue.getText().toString()+"");
                if(tv_trangThai.getText().equals("Đã trả")){
                    checkBox.setChecked(true);
                }
                ngayThue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Chitiet_PhieuMuon.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar.set(i, i1, i2);
                                ngayThue.setText(myDate.toStringVn(calendar.getTime()));
                                ngayMuon = myDate.toString(calendar.getTime());
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });
                Spinner spinner_tv = dialog.findViewById(R.id.spn_tv_sua);
                ThanhVienDAO thanhVienDAO = new ThanhVienDAO(Chitiet_PhieuMuon.this);
                ArrayList<ThanhVien> list_tv = new ArrayList<>();
                list_tv = thanhVienDAO.getAll();
                AdapterSpinerThanhVien adapterSpiner_tv = new AdapterSpinerThanhVien(Chitiet_PhieuMuon.this, list_tv);
                spinner_tv.setAdapter(adapterSpiner_tv);
                for (int i = 0; i < list_tv.size(); i++) {
                    ThanhVien tv_ = (ThanhVien) list_tv.get(i);
                    if (tv_.getHoTen() == bundle.getString("tenTV")) {
                        spinner_tv.setSelection(i);
                        break;
                    }
                }
                Spinner spinner_sach = dialog.findViewById(R.id.spn_sach_sua);
                SachDAO sachDAO = new SachDAO(Chitiet_PhieuMuon.this);
                ArrayList<Sach> list_sach = new ArrayList<>();
                list_sach = sachDAO.getAll();
                AdapterSpinerSach adapterSpiner = new AdapterSpinerSach(Chitiet_PhieuMuon.this, list_sach);
                spinner_sach.setAdapter(adapterSpiner);
                for (int i = 0; i < list_sach.size(); i++) {
                    Sach sach_ = (Sach) list_sach.get(i);
                    if (sach_.getTenSach() == bundle.getString("tenSach")) {
                        spinner_sach.setSelection(i);
                        break;
                    }
                }
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
                Button btn_Sua = dialog.findViewById(R.id.btn_them_pm_sua);
                Button btn_Huy = dialog.findViewById(R.id.btn_huy_pm_sua);
                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_Sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       if (ngayThue.getText().toString().isEmpty()){
                            edL_ngayThue.setError("Vui lòng nhập giá thuê!");
                            return;
                        }else {
                            edL_ngayThue.setError("");
                        }
                        PhieuMuon phieuMuon = new PhieuMuon();
                        phieuMuon.setMaPM(bundle.getInt("maPM"));
                        phieuMuon.setTienThue(Integer.valueOf(giathue.getText().toString()));
                        try {
                            phieuMuon.setNgay(MyDate.toDate(ngayMuon));
                            Log.d("oooooo", ""+phieuMuon.getNgay());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Sach sach = (Sach) spinner_sach.getSelectedItem();
                        phieuMuon.setMaSach(Integer.valueOf(sach.getMaSach()));
                        ThanhVien thanhVien = (ThanhVien) spinner_tv.getSelectedItem();
                        phieuMuon.setMaTV(Integer.valueOf(thanhVien.getMaTV()));
                        phieuMuon.setTrangThai(checkBox.isChecked()?1:0);
                        if(dao.update(phieuMuon)>0){
                            Toast.makeText(Chitiet_PhieuMuon.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            tv_tenTV.setText(thanhVien.getHoTen());
                            tv_ngayMuon.setText(ngayThue.getText().toString()+"");
                            tv_tenSach.setText(sach.getTenSach());
                            tv_giaThue.setText(giathue.getText().toString()+"");
                            tv_trangThai.setText(phieuMuon.getTrangThai()==1?"Đã trả":"Chưa trả");
                            dialog.dismiss();
                        }else {
                            Toast.makeText(Chitiet_PhieuMuon.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

    }
}
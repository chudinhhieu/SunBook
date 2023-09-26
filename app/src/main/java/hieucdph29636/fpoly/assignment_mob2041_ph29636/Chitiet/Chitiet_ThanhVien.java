package hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerLoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.SachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThanhVienDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.MyDate;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class Chitiet_ThanhVien extends AppCompatActivity {
    TextView tv_maTV,tv_tenTV,tv_namsinh,tv_maLoai;
    Button btn_sua,btn_xoa;
    MyDate date;
    ThanhVienDAO dao;
    ImageView img_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_thanh_vien);
        dao = new ThanhVienDAO(this);
        img_exit = findViewById(R.id.img_exit_tv);
        tv_maTV = findViewById(R.id.tv_maTV_ct);
        tv_namsinh = findViewById(R.id.tv_namsinh_ct);
        tv_tenTV = findViewById(R.id.tv_tenTV_ct);
        btn_sua = findViewById(R.id.btn_sua_tv);
        btn_xoa = findViewById(R.id.btn_xoa_tv);
        Bundle bundle = getIntent().getExtras();
        tv_maTV.setText(bundle.getInt("id_tv")+"");
        tv_namsinh.setText(bundle.getString("namsinh_tv"));
        tv_tenTV.setText(bundle.getString("ten_tv"));
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_ThanhVien.this);
                dialog.setContentView(R.layout.dialog_suatv);
                TextInputEditText tenTV =  dialog.findViewById(R.id.ed_sua_tentv);
                TextInputEditText ngaysinh =  dialog.findViewById(R.id.ed_sua_ngaysinh);
                TextInputLayout edL_tenTV =  dialog.findViewById(R.id.edL_sua_tentv);
                TextInputLayout edL_ngaysinh =  dialog.findViewById(R.id.edL_sua_ngaysinh);
                tenTV.setText(tv_tenTV.getText().toString());
                ngaysinh.setText(tv_namsinh.getText().toString());
                ngaysinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Chitiet_ThanhVien.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar.set(i, i1, i2);
                                ngaysinh.setText(date.toStringVn(calendar.getTime()));
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                Button btn_Sua = dialog.findViewById(R.id.btn_sua_tv);
                Button btn_Huy = dialog.findViewById(R.id.btn_huysua_tv);
                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_Sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tenTV.getText().toString().isEmpty()){
                            edL_tenTV.setError("Vui lòng nhập tên sách!");
                            return;
                        }else {
                            edL_tenTV.setError("");
                        }if (ngaysinh.getText().toString().isEmpty()){
                            edL_ngaysinh.setError("Vui lòng nhập giá thuê!");
                            return;
                        }else {
                            edL_ngaysinh.setError("");
                        }
                        ThanhVien thanhVien = new ThanhVien();
                        thanhVien.setMaTV(Integer.valueOf(tv_maTV.getText().toString()));
                        thanhVien.setHoTen(tenTV.getText().toString());
                        thanhVien.setNamSinh(ngaysinh.getText().toString());
                        if(dao.update(thanhVien)>0){
                            Toast.makeText(Chitiet_ThanhVien.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            tv_tenTV.setText(tenTV.getText().toString());
                            tv_namsinh.setText(ngaysinh.getText().toString()+"");
                            dialog.dismiss();
                        }else {
                            Toast.makeText(Chitiet_ThanhVien.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_ThanhVien.this);
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tv_nd_xoa = dialog.findViewById(R.id.tv_nd_xoa);
                TextView tv_tieude= dialog.findViewById(R.id.tv_xoa);
                tv_tieude.setText("Xóa sách");
                tv_nd_xoa.setText("Bạn chắc chắn muốn xóa thành viên ' "+tv_tenTV.getText().toString()+" 'này?");
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
                        dao.delete(Integer.parseInt(tv_maTV.getText().toString()));
                            Toast.makeText(Chitiet_ThanhVien.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            onBackPressed();
                    }
                });
                dialog.show();
            }
        });
    }
}
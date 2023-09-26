package hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.AdapterSpinerLoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.SachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class Chitiet_Sach extends AppCompatActivity {
    TextView tv_maSach,tv_tenSach,tv_giaThue,tv_maLoai;
    Button btn_sua,btn_xoa;
    SachDAO dao;
    ImageView img_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sach);
        dao = new SachDAO(this);
        img_exit = findViewById(R.id.img_exit);
        tv_maSach = findViewById(R.id.tv_maSach_ct);
        tv_giaThue = findViewById(R.id.tv_giathue_ct);
        tv_tenSach = findViewById(R.id.tv_tenSach_ct);
        tv_maLoai = findViewById(R.id.tv_maLoai_ct);
        btn_sua = findViewById(R.id.btn_sua_s);
        btn_xoa = findViewById(R.id.btn_xoa_s);
        Bundle bundle = getIntent().getExtras();
        tv_maSach.setText(bundle.getInt("id_s")+"");
        tv_maLoai.setText(bundle.getInt("id_ls")+"");
        tv_giaThue.setText(bundle.getInt("gia")+"");
        tv_tenSach.setText(bundle.getString("ten_s"));
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_Sach.this);
                dialog.setContentView(R.layout.dialog_sua_sach);
                TextInputEditText tenSach =  dialog.findViewById(R.id.ed_sua_tensach);
                TextInputEditText giathue =  dialog.findViewById(R.id.ed_sua_giathue);
                TextInputLayout edL_tensach =  dialog.findViewById(R.id.edL_sua_tensach);
                TextInputLayout edL_giathue =  dialog.findViewById(R.id.edL_sua_giathue);
                tenSach.setText(tv_tenSach.getText().toString());
                giathue.setText(tv_giaThue.getText().toString()+"");
                Spinner spinner = dialog.findViewById(R.id.spn_loaisach_sua);
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(Chitiet_Sach.this);
                ArrayList<LoaiSach> listLS = new ArrayList<>();
                listLS = loaiSachDAO.getAll();
                AdapterSpinerLoaiSach adapterSpiner = new AdapterSpinerLoaiSach(Chitiet_Sach.this, listLS);
                spinner.setAdapter(adapterSpiner);
                for (int i = 0; i < listLS.size(); i++) {
                    LoaiSach loaiSach_ = (LoaiSach) listLS.get(i);
                    if (loaiSach_.getMaLoai() == bundle.getInt("id_ls")) {
                        spinner.setSelection(i);
                        break;
                    }
                }
                Button btn_Sua = dialog.findViewById(R.id.btn_sua_sach);
                Button btn_Huy = dialog.findViewById(R.id.btn_huysua_sach);
                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_Sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tenSach.getText().toString().isEmpty()){
                            edL_tensach.setError("Vui lòng nhập tên sách!");
                            return;
                        }else {
                            edL_tensach.setError("");
                        }if (giathue.getText().toString().isEmpty()){
                            edL_giathue.setError("Vui lòng nhập giá thuê!");
                            return;
                        }else {
                            edL_giathue.setError("");
                        }
                        Sach sach = new Sach();
                        sach.setMaSach(Integer.valueOf(tv_maSach.getText().toString()));
                        sach.setTenSach(tenSach.getText().toString());
                        sach.setGiaThue(Integer.valueOf(giathue.getText().toString()));
                        LoaiSach loaiSach = (LoaiSach) spinner.getSelectedItem();
                        sach.setMaLoai(Integer.valueOf(loaiSach.getMaLoai()));
                        if(dao.update(sach)>0){
                            Toast.makeText(Chitiet_Sach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            tv_tenSach.setText(tenSach.getText().toString());
                            tv_giaThue.setText(giathue.getText().toString()+"");
                            tv_maLoai.setText(loaiSach.getMaLoai().toString()+"");
                            dialog.dismiss();
                        }else {
                            Toast.makeText(Chitiet_Sach.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_Sach.this);
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tv_nd_xoa = dialog.findViewById(R.id.tv_nd_xoa);
                TextView tv_tieude= dialog.findViewById(R.id.tv_xoa);
                tv_tieude.setText("Xóa sách");
                tv_nd_xoa.setText("Bạn chắc chắn muốn xóa sách ' "+tv_tenSach.getText().toString()+" 'này?");
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
                        dao.delete(Integer.parseInt(tv_maSach.getText().toString()));
                            Toast.makeText(Chitiet_Sach.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            onBackPressed();
                    }
                });
                dialog.show();
            }
        });
    }
}
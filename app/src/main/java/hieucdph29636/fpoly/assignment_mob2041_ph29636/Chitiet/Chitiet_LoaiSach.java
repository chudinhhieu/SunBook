package hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.LoaiSachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.QLLS_Fragment;

public class Chitiet_LoaiSach extends AppCompatActivity {
    TextView tv_id,tv_ten;
    Button btn_sua,btn_xoa;
    LoaiSachDAO dao;
    ImageView img_exit_ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_loai_sach);
        dao = new LoaiSachDAO(this);
        tv_id = findViewById(R.id.tv2);
        img_exit_ls = findViewById(R.id.img_exit_ls);
        tv_ten = findViewById(R.id.tv4);
        btn_sua = findViewById(R.id.btn_sua_ls);
        btn_xoa = findViewById(R.id.btn_xoa_ls);
        Bundle bundle = getIntent().getExtras();
        tv_id.setText(bundle.getInt("id_ls")+"");
        tv_ten.setText(bundle.getString("ten_ls"));
        img_exit_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_LoaiSach.this);
                dialog.setContentView(R.layout.dialog_sua_ls);
                TextInputEditText tenLoaiSach =  dialog.findViewById(R.id.ed_sua_tenloaisach);
                tenLoaiSach.setText(tv_ten.getText().toString());
                TextInputLayout inputLayout =  dialog.findViewById(R.id.edL_sua_tenloaisach);
                Button btn_suals = dialog.findViewById(R.id.btn_sua_tenls);
                Button btn_huy_suals = dialog.findViewById(R.id.btn_huy_suals);
                btn_huy_suals.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_suals.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoaiSach loaiSach = new LoaiSach(); 
                        loaiSach.setMaLoai(Integer.valueOf(tv_id.getText().toString()));
                        loaiSach.setTenLoai(tenLoaiSach.getText().toString());
                        if(dao.update(loaiSach)>0){
                            Toast.makeText(Chitiet_LoaiSach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            tv_ten.setText(tenLoaiSach.getText().toString());
                            dialog.dismiss();
                        }else {
                            Toast.makeText(Chitiet_LoaiSach.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                dialog.show();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Chitiet_LoaiSach.this);
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tv_nd_xoa = dialog.findViewById(R.id.tv_nd_xoa);
                tv_nd_xoa.setText("Bạn chắc chắn muốn xóa loại sách ' "+tv_ten.getText().toString()+" 'này?");
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
                        dao.delete(Integer.parseInt(tv_id.getText().toString()));
                            Toast.makeText(Chitiet_LoaiSach.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            onBackPressed();
                    }
                });
                dialog.show();
            }
        });
    }
}
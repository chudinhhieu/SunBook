package hieucdph29636.fpoly.assignment_mob2041_ph29636.Chitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThuThuDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class ChiTiet_ThuThu extends AppCompatActivity {
    TextView tv_id,tv_ten,tv_mk;
    Button btn_sua,btn_xoa;
    ThuThuDAO dao;
    ImageView img_exit_tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thu_thu);
        dao = new ThuThuDAO(this);
        tv_id = findViewById(R.id.tv_maTT_ct);
        tv_mk = findViewById(R.id.tv_matKhauTT_ct);
        img_exit_tt = findViewById(R.id.img_exit_tt);
        tv_ten = findViewById(R.id.tv_hoTenTT_ct);
        btn_sua = findViewById(R.id.btn_sua_tt);
        btn_xoa = findViewById(R.id.btn_xoa_tt);
        Bundle bundle = getIntent().getExtras();
        tv_id.setText(bundle.getString("id_tt")+"");
        tv_mk.setText(bundle.getString("mk_tt")+"");
        tv_ten.setText(bundle.getString("ten_tt"));
        img_exit_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ChiTiet_ThuThu.this);
                dialog.setContentView(R.layout.dialog_sua_tt);
                TextInputEditText hoTen =  dialog.findViewById(R.id.ed_them_hoTenTTs_sua);
                TextInputEditText matKhau =  dialog.findViewById(R.id.ed_them_matkhau_sua);
                TextInputLayout edL_hoTen =  dialog.findViewById(R.id.edL_them_hoTenTT_sua);
                TextInputLayout edL_matKhau =  dialog.findViewById(R.id.edL_them_matkhau_sua);
                matKhau.setText(bundle.getString("mk_tt")+"");
                hoTen.setText(bundle.getString("ten_tt"));
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
                        thuThu.setMaTT(bundle.getString("id_tt"));
                        thuThu.setHoTen(hoTen.getText().toString());
                        thuThu.setMatKhau(matKhau.getText().toString());
                        if(dao.update(thuThu)>0){
                            Toast.makeText(ChiTiet_ThuThu.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            tv_ten.setText(hoTen.getText().toString());
                            tv_mk.setText(matKhau.getText().toString());
                            dialog.dismiss();
                        }else {
                            Toast.makeText(ChiTiet_ThuThu.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ChiTiet_ThuThu.this);
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tv_nd_xoa = dialog.findViewById(R.id.tv_nd_xoa);
                TextView tv_xoa = dialog.findViewById(R.id.tv_xoa);
                tv_xoa.setText("Xóa thủ thư");
                tv_nd_xoa.setText("Bạn chắc chắn muốn xóa thủ thư tên ' "+tv_ten.getText().toString()+" 'này?");
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
                        dao.delete(tv_id.getText().toString());
                            Toast.makeText(ChiTiet_ThuThu.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            onBackPressed();
                    }
                });
                dialog.show();
            }
        });
    }
}
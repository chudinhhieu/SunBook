package hieucdph29636.fpoly.assignment_mob2041_ph29636;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThuThuDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;

public class DoiMatKhau extends AppCompatActivity {
    TextInputLayout edL_matKhauCu,edL_matKhauMoi,edL_matKhauMoi2;
    TextInputEditText ed_matKhauCu,ed_matKhauMoi,ed_matKhauMoi2;
    Button btn_dmk;
    ThuThuDAO dao;
    ArrayList<ThuThu> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        edL_matKhauCu = findViewById(R.id.edL_matKhauCu);
        edL_matKhauMoi = findViewById(R.id.edL_matKhauMoi);
        edL_matKhauMoi2 = findViewById(R.id.edL_matKhauMoi2);
        ed_matKhauMoi = findViewById(R.id.ed_matKhauMoi);
        ed_matKhauCu = findViewById(R.id.ed_matKhauCu);
        ed_matKhauMoi2 = findViewById(R.id.ed_matKhauMoi2);
        btn_dmk = findViewById(R.id.btn_dmk);
        dao = new ThuThuDAO(this);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("MaTT","");
        btn_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = dao.getTen(matt);
                ThuThu thuThu = list.get(0);
                if (ed_matKhauCu.getText().toString().isEmpty()){
                    edL_matKhauCu.setError("Vui lòng nhập!");
                    return;
                }else {
                    edL_matKhauCu.setError("");
                }
                if (ed_matKhauMoi.getText().toString().isEmpty()){
                    edL_matKhauMoi.setError("Vui lòng nhập!");
                    return;
                }else {
                    edL_matKhauMoi.setError("");
                }
                if (ed_matKhauMoi2.getText().toString().isEmpty()){
                    edL_matKhauMoi2.setError("Vui lòng nhập!");
                    return;
                }else {
                    edL_matKhauMoi2.setError("");
                }
                if (!ed_matKhauCu.getText().toString().equals(thuThu.getMatKhau())){
                    edL_matKhauCu.setError("Mật khẩu cũ sai!");
                    return;
                }else {
                    edL_matKhauCu.setError("");
                }
                if(!ed_matKhauMoi.getText().toString().equals(ed_matKhauMoi2.getText().toString())){
                    edL_matKhauMoi.setError("Mật khẩu không khớp!");
                    edL_matKhauMoi2.setError("Mật khẩu không khớp!");
                    return;
                }else {
                    edL_matKhauMoi.setError("");
                    edL_matKhauMoi2.setError("");
                }
                if(thuThu.getMatKhau().equals(ed_matKhauMoi.getText().toString())){
                    edL_matKhauMoi.setError("Mật khẩu mới phải khác mật khẩu cũ");
                    edL_matKhauMoi2.setError("Mật khẩu mới phải khác mật khẩu cũ");
                    return;
                }else {
                    edL_matKhauMoi.setError("");
                    edL_matKhauMoi2.setError("");
                }
                if (ed_matKhauCu.getText().toString().equals(thuThu.getMatKhau())&&ed_matKhauMoi.getText().toString().equals(ed_matKhauMoi2.getText().toString())){
                   if(dao.update(new ThuThu(matt,thuThu.getHoTen(),ed_matKhauMoi.getText().toString()))>0){
                       Toast.makeText(DoiMatKhau.this, "Đổi thành công!", Toast.LENGTH_SHORT).show();
                       onBackPressed();
                   }
                }
            }
        });
    }
}
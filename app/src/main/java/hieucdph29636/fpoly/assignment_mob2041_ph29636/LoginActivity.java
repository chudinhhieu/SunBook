package hieucdph29636.fpoly.assignment_mob2041_ph29636;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    TextInputEditText ed_taiKhoan,ed_matKhau;
    TextInputLayout edL_taiKhoan,edL_matKhau;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        ed_taiKhoan = findViewById(R.id.ed_taiKhoan);
        edL_taiKhoan = findViewById(R.id.edL_taiKhoan);
        edL_matKhau = findViewById(R.id.edL_matKhau);
        ed_matKhau = findViewById(R.id.ed_matKhau);
        checkBox = findViewById(R.id.checkbox);
        SharedPreferences sharedPreferences = getSharedPreferences("nhotaiKhoan", MODE_PRIVATE);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (isChecked) {
                    editor.putString("username", ed_taiKhoan.getText().toString());
                    editor.putString("password", ed_matKhau.getText().toString());
                } else {
                    editor.remove("username");
                    editor.remove("password");
                }
                editor.apply();
            }
        });
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (!username.isEmpty() && !password.isEmpty()) {
           ed_taiKhoan.setText(username);
           ed_matKhau.setText(password);
        } else {
            // Không có tài khoản và mật khẩu được lưu trữ.
        }
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = ed_taiKhoan.getText().toString();
                String matKhau = ed_matKhau.getText().toString();
                if(thuThuDAO.checkDangNhap(taiKhoan,matKhau)){
                        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("MaTT",ed_taiKhoan.getText().toString());
                        editor.putString("MatKhau",ed_taiKhoan.getText().toString());
                        editor.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    if(ed_taiKhoan.getText().toString().isEmpty()){
                        edL_taiKhoan.setError("Vui lòng nhập tài khoản");
                    }else {
                        edL_taiKhoan.setError("");
                    }
                    if(ed_matKhau.getText().toString().isEmpty()){
                        edL_matKhau.setError("Vui lòng nhập mật khẩu");
                    }else {
                        edL_matKhau.setError("");
                    }
                    if (!ed_taiKhoan.getText().toString().isEmpty()&&!ed_matKhau.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
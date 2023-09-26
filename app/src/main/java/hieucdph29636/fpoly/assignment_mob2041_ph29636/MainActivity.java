package hieucdph29636.fpoly.assignment_mob2041_ph29636;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.SachDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.ThuThuDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.QLLS_Fragment;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.QLPM_Fragment;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.QLS_Fragment;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.QLTT_Fragment;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.QLTV_Fragment;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.Top10_Fragment;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment.TopRevenue;

public class MainActivity extends AppCompatActivity{
    DrawerLayout drawer_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_layout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.flContent);
        NavigationView navigationView = findViewById(R.id.nav_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        SachDAO dao = new SachDAO(this);
        dao.getAll();
        QLPM_Fragment home = new QLPM_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, home)
                .commit();
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("MaTT","");
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        ArrayList<ThuThu> list = thuThuDAO.getTen(matt);
        View headerLayout = navigationView.getHeaderView(0);
        TextView tv_name = headerLayout.findViewById(R.id.tv_name);
        tv_name.setText(matt);
        TextView tv_fullname = headerLayout.findViewById(R.id.tv_fullname);
        tv_fullname.setText(list.get(0).getHoTen());
        TextView tv_quyen = headerLayout.findViewById(R.id.tv_quyen);
        if(matt.equals("admin")){
            tv_quyen.setText("Admin");
            navigationView.getMenu().findItem(R.id.nav_ql_thu_thu).setVisible(true);
        }else {
            tv_quyen.setText("Thử thư");
            navigationView.getMenu().findItem(R.id.nav_ql_thu_thu).setVisible(false);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_phieu_muon:
                        fragment = new QLPM_Fragment();
                        break;
                    case R.id.nav_loai_sach:
                        fragment = new QLLS_Fragment();
                        break;
                    case R.id.nav_sach:
                        fragment = new QLS_Fragment();
                        break;
                    case R.id.nav_thanh_vien:
                        fragment = new QLTV_Fragment();
                        break;
                    case R.id.nav_top10:
                        fragment = new Top10_Fragment();
                        break;
                    case R.id.nav_doanh_thu:
                        fragment = new TopRevenue();
                        break;
                    case R.id.nav_ql_thu_thu:
                        fragment = new QLTT_Fragment();
                        break;
                    case R.id.nav_doimk:
                        startActivity(new Intent(getApplicationContext(), DoiMatKhau.class));
                        return true;
                    case R.id.nav_dangxuat:
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        fragment = new QLPM_Fragment();
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();
                drawer_layout.closeDrawer(GravityCompat.START);
                toolbar.setTitle(item.getTitle());
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            drawer_layout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
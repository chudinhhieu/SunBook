package hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.PhieuMuonDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.MyDate;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class TopRevenue extends Fragment {
    MyDate myDate;
    String tuNgay ="";
    String denNgay ="";
    PhieuMuonDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toprevenue,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText ed_tuNgay,ed_denNgay;
        TextInputLayout edL_tuNgay,edL_denNgay;
        Button btn_tinh;
        TextView tv_doanhthu;
        ed_denNgay = view.findViewById(R.id.ed_denNgay);
        edL_tuNgay = view.findViewById(R.id.edL_tuNgay);
        edL_denNgay = view.findViewById(R.id.edL_denNgay);
        ed_tuNgay = view.findViewById(R.id.ed_tuNgay);
        btn_tinh = view.findViewById(R.id.btn_tinh);
        dao = new PhieuMuonDAO(getContext());
        tv_doanhthu = view.findViewById(R.id.doanhThu);
        ed_tuNgay.setOnClickListener(new View.OnClickListener() {
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
                        ed_tuNgay.setText(myDate.toStringVn(calendar.getTime()));
                        tuNgay = myDate.toString(calendar.getTime());
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        ed_denNgay.setOnClickListener(new View.OnClickListener() {
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
                        ed_denNgay.setText(myDate.toStringVn(calendar.getTime()));
                        denNgay = myDate.toString(calendar.getTime());
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        btn_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuNgay.isEmpty()&&denNgay.isEmpty()){
                    edL_tuNgay.setError("Cần chọn ngày!");
                    edL_denNgay.setError("Cần chọn ngày!");
                    return;
                }else {
                    edL_tuNgay.setError("");
                    edL_denNgay.setError("");
                }
                if(tuNgay.isEmpty()){
                    edL_tuNgay.setError("Cần chọn ngày!");
                    return;
                }else {
                    edL_tuNgay.setError("");
                }
                if(denNgay.isEmpty()){
                    edL_denNgay.setError("Cần chọn ngày!");
                    return;
                }else {
                    edL_denNgay.setError("");
                }
                int doanhThu = dao.getDoanhThu(tuNgay,denNgay);
                tv_doanhthu.setText(doanhThu+"");
            }
        });
    }
}

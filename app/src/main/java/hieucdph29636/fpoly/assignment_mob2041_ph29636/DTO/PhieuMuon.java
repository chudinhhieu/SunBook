package hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO;

import java.util.Date;

public class PhieuMuon {
    private Integer MaPM,MaTV,MaSach,TienThue,TrangThai;
    private String MaTT,HoTenTT,TenSach,TenTV;
    private Date Ngay;
//    pm.MaPM, pm.MaTV,tv.HoTen,pm.MaTT,tt.HoTen,pm.MaSach,s.TenSach,pm.Ngay,pm.TrangThai,pm.TienThue

    public PhieuMuon() {
    }

    public PhieuMuon(Integer maPM, Integer maTV, String tenTV, String maTT, String hoTenTT, Integer maSach, String tenSach, Date ngay, Integer trangThai, Integer tienThue) {
        MaPM = maPM;
        MaTV = maTV;
        TenTV = tenTV;
        MaTT = maTT;
        HoTenTT = hoTenTT;
        MaSach = maSach;
        TenSach =tenSach;
        Ngay = ngay;
        TrangThai = trangThai;
        TienThue = tienThue;
    }

    public String getTenTV() {
        return TenTV;
    }

    public void setTenTV(String tenTV) {
        TenTV = tenTV;
    }

    public String getHoTenTT() {
        return HoTenTT;
    }

    public void setHoTenTT(String hoTenTT) {
        HoTenTT = hoTenTT;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public Integer getMaPM() {
        return MaPM;
    }

    public void setMaPM(Integer maPM) {
        MaPM = maPM;
    }

    public Integer getMaTV() {
        return MaTV;
    }

    public void setMaTV(Integer maTV) {
        MaTV = maTV;
    }

    public Integer getMaSach() {
        return MaSach;
    }

    public void setMaSach(Integer maSach) {
        MaSach = maSach;
    }

    public Integer getTienThue() {
        return TienThue;
    }

    public void setTienThue(Integer tienThue) {
        TienThue = tienThue;
    }

    public Integer getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Integer trangThai) {
        TrangThai = trangThai;
    }

    public String getMaTT() {
        return MaTT;
    }

    public void setMaTT(String maTT) {
        MaTT = maTT;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

}

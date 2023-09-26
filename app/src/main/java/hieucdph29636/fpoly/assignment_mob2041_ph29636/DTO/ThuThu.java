package hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO;

public class ThuThu {
   private String MaTT,HoTen,MatKhau;

    public ThuThu(String maTT, String hoTen, String matKhau) {
        MaTT = maTT;
        HoTen = hoTen;
        MatKhau = matKhau;
    }

    public ThuThu() {
    }

    public String getMaTT() {
        return MaTT;
    }

    public void setMaTT(String maTT) {
        MaTT = maTT;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}

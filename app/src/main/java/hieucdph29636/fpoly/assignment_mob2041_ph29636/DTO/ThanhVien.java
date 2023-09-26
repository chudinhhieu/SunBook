package hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO;

public class ThanhVien {
    private Integer maTV;
    private String HoTen,NamSinh;

    public ThanhVien() {
    }

    public ThanhVien(Integer maTV, String hoTen, String namSinh) {
        this.maTV = maTV;
        HoTen = hoTen;
        NamSinh = namSinh;
    }

    public Integer getMaTV() {
        return maTV;
    }

    public void setMaTV(Integer maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }
}

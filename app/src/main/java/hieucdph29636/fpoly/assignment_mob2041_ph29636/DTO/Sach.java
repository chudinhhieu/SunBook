package hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO;

public class Sach {
    private Integer MaSach,MaLoai,GiaThue;
    private String TenSach;

    public Sach() {
    }

    public Sach(Integer maSach, String tenSach, Integer giaThue, Integer maLoai) {
        MaSach = maSach;
        MaLoai = maLoai;
        GiaThue = giaThue;
        TenSach = tenSach;
    }

    public Integer getMaSach() {
        return MaSach;
    }

    public void setMaSach(Integer maSach) {
        MaSach = maSach;
    }

    public Integer getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(Integer maLoai) {
        MaLoai = maLoai;
    }

    public Integer getGiaThue() {
        return GiaThue;
    }

    public void setGiaThue(Integer giaThue) {
        GiaThue = giaThue;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }
}

package hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO;

public class TopSach {
    private String tenSachTop;
    private Integer soLuong,maSachTop;

    public TopSach() {
    }

    public TopSach(Integer maSachTop, String tenSachTop, Integer soLuong) {
        this.tenSachTop = tenSachTop;
        this.soLuong = soLuong;
        this.maSachTop = maSachTop;
    }

    public String getTenSachTop() {
        return tenSachTop;
    }

    public void setTenSachTop(String tenSachTop) {
        this.tenSachTop = tenSachTop;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getMaSachTop() {
        return maSachTop;
    }

    public void setMaSachTop(Integer maSachTop) {
        this.maSachTop = maSachTop;
    }
}

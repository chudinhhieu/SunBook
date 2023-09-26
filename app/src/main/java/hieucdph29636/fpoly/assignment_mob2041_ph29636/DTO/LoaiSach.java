package hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO;

public class LoaiSach {
    private Integer MaLoai;
    private String TenLoai;

    public Integer getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(Integer maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public LoaiSach() {
    }

    public LoaiSach(Integer maLoai, String tenLoai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
    }
}

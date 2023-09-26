package hieucdph29636.fpoly.assignment_mob2041_ph29636.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "SunBook", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String thuthu = "CREATE TABLE ThuThu(MaTT TEXT PRIMARY KEY UNIQUE NOT NULL,HoTen TEXT NOT NULL,MatKhau TEXT NOT NULL)";
        db.execSQL(thuthu);
        String thanhvien = "CREATE TABLE ThanhVien(MaTV integer PRIMARY KEY AUTOINCREMENT,HoTen TEXT NOT NULL,NamSinh TEXT NOT NULL)";
        db.execSQL(thanhvien);
        String loaisach = "CREATE TABLE LoaiSach(MaLoai integer PRIMARY KEY AUTOINCREMENT,TenLoai TEXT NOT NULL)";
        db.execSQL(loaisach);
        String sach = "CREATE TABLE Sach(MaSach integer PRIMARY KEY AUTOINCREMENT,TenSach TEXT NOT NULL,GiaThue integer NOT NULL,MaLoai integer references LoaiSach(MaLoai))";
        db.execSQL(sach);
        String phieumuon = "CREATE TABLE PhieuMuon(MaPM integer PRIMARY KEY AUTOINCREMENT,MaTV integer references ThanhVien(MaTV),MaTT TEXT references ThuThu(MaTT),MaSach integer references Sach(MaSach),Ngay Date NOT NULL, TienThue integer references Sach(GiaThue), TrangThai integer NOT NULL)";
        db.execSQL(phieumuon);
        db.execSQL("INSERT INTO ThuThu VALUES('admin','Chu Đình Hiếu','1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS ThuThu");
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            db.execSQL("DROP TABLE IF EXISTS LoaiSach");
            db.execSQL("DROP TABLE IF EXISTS Sach");
            db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
            onCreate(db);
        }
    }
}

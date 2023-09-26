package hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.PhieuMuon;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Database.DBHelper;

public class ThuThuDAO {
    DBHelper dbHelper;
    public ThuThuDAO (Context context){
        dbHelper = new DBHelper(context);
    }
    public long insert(ThuThu thuThu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTT", thuThu.getMaTT());
        values.put("HoTen", thuThu.getHoTen());
        values.put("MatKhau",thuThu.getMatKhau());
        return db.insert("ThuThu", null, values);
    }
    public long update(ThuThu thuThu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTT", thuThu.getMaTT());
        values.put("HoTen", thuThu.getHoTen());
        values.put("MatKhau",thuThu.getMatKhau());
        return db.update("ThuThu",  values,"MaTT=?",new String[]{thuThu.getMaTT()});
    }
    public void delete(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
         db.delete("ThuThu","MaTT=?",new String[]{id});
         db.delete("PhieuMuon","MaTT=?",new String[]{id});
    }
    public ArrayList<ThuThu> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<ThuThu> list = new ArrayList<>();
        String[] ds_cot = new String[]{"*"};
        Cursor cursor = db.query("ThuThu", ds_cot, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(cursor.getString(0));
                thuThu.setHoTen(cursor.getString(1));
                thuThu.setMatKhau(cursor.getString(2));
                list.add(thuThu);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public ArrayList<ThuThu> getTen(String maTT){
        ArrayList<ThuThu> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT*FROM ThuThu WHERE MaTT=?",new String[]{maTT});;
        if (cursor.getCount()!=0){
            cursor.moveToNext();
            do {
                ThuThu thuThu = new ThuThu(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                list.add(thuThu);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<ThuThu> getTenTT(String ten){
        ArrayList<ThuThu> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT*FROM ThuThu WHERE HoTen=?",new String[]{ten});;
        if (cursor.getCount()!=0){
            cursor.moveToNext();
            do {
                ThuThu thuThu = new ThuThu(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                list.add(thuThu);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean checkDangNhap(String MaTT,String matKhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE MaTT =? AND MatKhau =?",new String[]{MaTT,matKhau});
        if (c.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
}

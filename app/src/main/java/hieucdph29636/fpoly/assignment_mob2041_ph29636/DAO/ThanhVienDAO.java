package hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Database.DBHelper;

public class ThanhVienDAO {
    DBHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insert(ThanhVien thanhVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HoTen", thanhVien.getHoTen());
        values.put("NamSinh", thanhVien.getNamSinh());
        return db.insert("ThanhVien", null, values);
    }
    public long update(ThanhVien thanhVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HoTen", thanhVien.getHoTen());
        values.put("NamSinh", thanhVien.getNamSinh());
        return db.update("ThanhVien", values,"MaTV=?",new String[]{thanhVien.getMaTV()+""});
    }
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("ThanhVien","MaTV=?",new String[]{String.valueOf(id)});
        db.delete("PhieuMuon","MaTV=?",new String[]{String.valueOf(id)});
    }
    public ArrayList<ThanhVien> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<ThanhVien> list = new ArrayList<>();
        String[] ds_cot = new String[]{"*"};
        Cursor cursor = db.query("ThanhVien", ds_cot, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setMaTV(cursor.getInt(0));
                thanhVien.setHoTen(cursor.getString(1));
                thanhVien.setNamSinh(cursor.getString(2));
                list.add(thanhVien);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public ArrayList<ThanhVien> getTen(String ten) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM ThanhVien WHERE HoTen=?", new String[]{ten});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setMaTV(cursor.getInt(0));
                thanhVien.setHoTen(cursor.getString(1));
                thanhVien.setNamSinh(cursor.getString(2));
                list.add(thanhVien);
                cursor.moveToNext();
            }
        }
        return list;
    }
}

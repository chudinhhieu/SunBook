package hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Database.DBHelper;

public class LoaiSachDAO {
    DBHelper dbHelper;

    public LoaiSachDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insert(LoaiSach loaiSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiSach.getTenLoai());
        return db.insert("LoaiSach", null, values);
    }
    public long update(LoaiSach loaiSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiSach.getTenLoai());
        return db.update("LoaiSach", values,"MaLoai=?",new String[]{loaiSach.getMaLoai()+""});
    }
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
         db.delete("LoaiSach","MaLoai=?",new String[]{String.valueOf(id)});
         db.delete("Sach","MaLoai=?",new String[]{String.valueOf(id)});
    }
    public ArrayList<LoaiSach> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<LoaiSach> list = new ArrayList<>();
        String[] ds_cot = new String[]{"*"};
        Cursor cursor = db.query("LoaiSach", ds_cot, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                LoaiSach loaiSach = new LoaiSach();
                loaiSach.setMaLoai(cursor.getInt(0));
                loaiSach.setTenLoai(cursor.getString(1));
                list.add(loaiSach);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public ArrayList<LoaiSach> getTen(String ten) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM LoaiSach WHERE TenLoai=?", new String[]{ten});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                LoaiSach loaiSach = new LoaiSach();
                loaiSach.setMaLoai(cursor.getInt(0));
                loaiSach.setTenLoai(cursor.getString(1));
                list.add(loaiSach);
                cursor.moveToNext();
            }
        }
        return list;
    }
}

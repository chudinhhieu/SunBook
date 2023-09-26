package hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.LoaiSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThuThu;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Database.DBHelper;

public class SachDAO {
    DBHelper dbHelper;
    public SachDAO (Context context){
        dbHelper = new DBHelper(context);
    }
    public long insert(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenSach", sach.getTenSach());
        values.put("GiaThue", sach.getGiaThue());
        values.put("MaLoai",sach.getMaLoai());
        return db.insert("Sach", null, values);
    }
    public long update(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenSach", sach.getTenSach());
        values.put("GiaThue", sach.getGiaThue());
        values.put("MaLoai",sach.getMaLoai());
        return db.update("Sach", values,"MaSach=?",new String[]{sach.getMaSach()+""});
    }
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Sach","MaSach=?",new String[]{String.valueOf(id)});
        db.delete("PhieuMuon","MaSach=?",new String[]{String.valueOf(id)});
    }
    public ArrayList<Sach> getAll(){
     ArrayList<Sach> list = new ArrayList<>();
     SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
     Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach",null);
     if (cursor.getCount() != 0){
         cursor.moveToFirst();
         do {
             list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
         }while (cursor.moveToNext());
     }
     return list;
    }
    public ArrayList<Sach> getTenSach(String ten){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT*FROM Sach WHERE TenSach=?",new String[]{String.valueOf(ten)});;
        if (cursor.getCount()!=0){
            cursor.moveToNext();
            do {
                Sach sach = new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
                list.add(sach);
            }while (cursor.moveToNext());
        }
        return list;
    }
}

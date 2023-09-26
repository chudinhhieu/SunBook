package hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.PhieuMuon;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.Sach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.ThanhVien;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.TopSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.Database.DBHelper;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.MyDate;

public class PhieuMuonDAO {
    DBHelper dbHelper;
    public PhieuMuonDAO (Context context){
        dbHelper = new DBHelper(context);
    }
    public long insert(PhieuMuon phieuMuon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaPM", phieuMuon.getMaPM());
        values.put("MaTV", phieuMuon.getMaTV());
        values.put("MaTT", phieuMuon.getMaTT());
        values.put("MaSach", phieuMuon.getMaSach());
        values.put("Ngay", MyDate.toString(phieuMuon.getNgay()));
        values.put("TienThue", phieuMuon.getTienThue());
        values.put("TrangThai", phieuMuon.getTrangThai());
        return db.insert("PhieuMuon", null, values);
    }
    public long update(PhieuMuon phieuMuon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaPM", phieuMuon.getMaPM());
        values.put("MaTV", phieuMuon.getMaTV());
        values.put("MaSach", phieuMuon.getMaSach());
        values.put("Ngay", MyDate.toString(phieuMuon.getNgay()));
        values.put("TienThue", phieuMuon.getTienThue());
        values.put("TrangThai", phieuMuon.getTrangThai());
        return db.update("PhieuMuon",  values,"MaPM=?",new String[]{String.valueOf(phieuMuon.getMaPM())});
    }
    public ArrayList<PhieuMuon> getAll(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT pm.MaPM, pm.MaTV,tv.HoTen,pm.MaTT,tt.HoTen,pm.MaSach,s.TenSach,pm.Ngay,pm.TrangThai,s.GiaThue " +
                "FROM PhieuMuon pm,ThanhVien tv,ThuThu tt, Sach s " +
                "WHERE pm.MaTV = tv.MaTV AND pm.MaTT = tt.MaTT AND pm.MaSach = s.MaSach",null);;
        if (cursor.getCount()!=0){
            cursor.moveToNext();
            do {
                PhieuMuon phieuMuon = null;
                try {
                    phieuMuon = new PhieuMuon(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6), MyDate.toDate(cursor.getString(7)),cursor.getInt(8),cursor.getInt(9));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.add(phieuMuon);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public long delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("PhieuMuon","MaPM=?",new String[]{String.valueOf(id)});
    }
    public ArrayList<TopSach> getTop10(){
     ArrayList<TopSach> list = new ArrayList<>();
     SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT pm.MaSach,s.TenSach, COUNT(pm.MaSach) as soLuong FROM PhieuMuon pm,Sach s WHERE pm.MaSach = s.MaSach GROUP BY pm.MaSach, s.TenSach ORDER BY COUNT(pm.MaSach) DESC LIMIT 10",null);
        if (cursor.getCount()!=0){
            cursor.moveToNext();
            do {
                list.add(new TopSach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ArrayList<Integer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT SUM(TienThue) as doanhThu FROM PhieuMuon WHERE Ngay BETWEEN ? AND ?", new String[]{tuNgay, denNgay});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                try{
                    list.add(cursor.getInt(cursor.getColumnIndex("doanhThu")));
                }catch (Exception e){
                    list.add(0);
                }
            }while (cursor.moveToNext());
        }
        return list.get(0);
    }

    public ArrayList<PhieuMuon> getTenTV(String tenTV){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT pm.MaPM, pm.MaTV,tv.HoTen,pm.MaTT,tt.HoTen,pm.MaSach,s.TenSach,pm.Ngay,pm.TrangThai,s.GiaThue " +
                "FROM PhieuMuon pm,ThanhVien tv,ThuThu tt, Sach s " +
                "WHERE pm.MaTV = tv.MaTV AND pm.MaTT = tt.MaTT AND pm.MaSach = s.MaSach AND tv.HoTen = ?",new String[]{tenTV});;
        if (cursor.getCount()!=0){
            cursor.moveToNext();
            do {
                PhieuMuon phieuMuon = null;
                try {
                    phieuMuon = new PhieuMuon(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6), MyDate.toDate(cursor.getString(7)),cursor.getInt(8),cursor.getInt(9));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.add(phieuMuon);
            }while (cursor.moveToNext());
        }
        return list;
    }
}

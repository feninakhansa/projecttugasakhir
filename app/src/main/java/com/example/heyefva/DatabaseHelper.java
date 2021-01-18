package com.example.heyefva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="data_beli.db";
    public static final String TABLE_NAME="tabel_efva";
    public static final String COL_1="jumlah";
    public static final String COL_2="item";
    public static final String COL_3="nama_pemesan";
    public static final String COL_4="alamat";
    public static final String COL_5="harga";
    public static final int DATABASE_VERTION=1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERTION);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tabel_efva(jumlah text null,item text null,nama_pemesan text null,alamat text null,harga text null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    //metode untuk tambah data
    public boolean insertData(String jumlah, String item, String nama_pemesan, String alamat, String harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, jumlah);
        contentValues.put(COL_2, item);
        contentValues.put(COL_3, nama_pemesan);
        contentValues.put(COL_4, alamat);
        contentValues.put(COL_5, harga);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


    //metode untuk mengambil data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from tabel_efva", null);
        return res;
    }

    //metode untuk merubah data
    public boolean updateData(String jumlah, String item, String nama_pemesan, String alamat,String harga, String bu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, jumlah);
        contentValues.put(COL_2, item);
        contentValues.put(COL_3, nama_pemesan);
        contentValues.put(COL_4, alamat);
        contentValues.put(COL_5, harga);


        db.update(TABLE_NAME, contentValues, "jumlah = ?", new String[]{jumlah});
        return true;
    }


    //metode untuk menghapus data
    public int deleteData(String jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "jumlah = ?", new String[]{jumlah});
    }

}

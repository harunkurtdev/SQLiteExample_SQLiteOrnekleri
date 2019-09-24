package com.example.lab08.sqliteopenhelper_dahilikullanimi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SimpleDatabaseHelper {
    private SQLiteOpenHelper _openHelper;


    public SimpleDatabaseHelper(Context context) {
        _openHelper = new SimpleSQLiteOpenHelper(context);
    }


    class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {
        SimpleSQLiteOpenHelper(Context context) {
            super(context, "main.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table if not exists notlar (id integer primary key autoincrement, baslik text(255), tarih varchar(255), aciklama text)");
            db.execSQL("insert into notlar (baslik,tarih,aciklama) values ('baslik','09.01.2019','açıklama') ");

            // Veritabanının üretilme zamanını temsil eder.
            // primary key - değerin benzersiz ve birincil anahtar olması
            // autoincrement - sayılarda kullanılır, primary key'in otomatik artmasını sağlar.
            // if not exists - eğer yoksa demektir.
            // veritabanı tablosu üretilmek için onCreate metodu içerisinde sorgu yazılır.
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Veritabanının güncelleme zamanını temsil eder.
        }
    }


    public Cursor getAll(String sorgu) {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        return db.rawQuery(sorgu, null);
        // Select sorgusundan dönen değerleri Cursor sınıfında saklayabiliriz.
    }

    public ContentValues get(long id) {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        ContentValues row = new ContentValues();
        Cursor cur = db.rawQuery("select title, priority from todos where _id = ?", new String[]{String.valueOf(id)});
        if (cur.moveToNext()) {
            row.put("title", cur.getString(0));
            row.put("priority", cur.getInt(1));
        }
        cur.close();
        db.close();
        return row;
        /*
        Content Values daha düzenli şekilde, Key-Value formatında verileri
        Eklemek veya Veri Güncellemek amaçlı veritabanı işlemlerinde kullanılır.
         */
    }

    public long add(String tableName, ContentValues contentValues) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        long id = db.insert(tableName, null, contentValues);
        db.close();
        return id;
        /*
        db.execSql(insert into tabloadi (kolonlar) values ('"++"'))
         */
    }

    public void delete(String tableName, String whereClause, String[] values) {
        // delete from tabloAdi where id=1 //_id = ?
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete(tableName, whereClause, values);
        db.close();
    }


    public void update(String tableName, ContentValues values, String whereClause, String[] whereClauseValues) {
        // update tabloadi set yas=20 where id=1
        SQLiteDatabase db = _openHelper.getWritableDatabase(); //_id = ?
        if (db == null) {
            return;
        }
        db.update(tableName, values, whereClause, whereClauseValues);
        db.close();
    }
}
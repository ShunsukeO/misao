package com.example.sun.infowithphoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Owner on 2017/05/15.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    public static String DBNAME = "InfoWithPhoto";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Info(Name varchar(30), Phone varchar(20), Image varchar(50))");
        this.sqLiteDatabase = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }

    public int insert(String name, String phone, String fileName) {
        sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Phone", phone);
        cv.put("Image", fileName);
        sqLiteDatabase.insert("Info", null, cv);
        return 1;
    }

    public Cursor show(String name) {
        sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select * from Info where Name = '" + name + "'", null);
        return c;
    }
}

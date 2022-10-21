package com.gokisoft.c2010g.lesson05;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TourDAO {
    private static final String TABLE_NAME = "tour";

    public static final String SQL_CREATE_TABLE = "create table tour (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tname varchar(250),\n" +
            "\taddress varchar(250),\n" +
            "\tprice float,\n" +
            "\tstart_date nvarchar(20),\n" +
            "\tend_date nvarchar(20)\n" +
            ")";

    public static void insert(Tour tour) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", tour.getName());
        values.put("address", tour.getAddress());
        values.put("price", tour.getPrice());
        values.put("start_date", tour.getStartDate());
        values.put("end_date", tour.getEndDate());

        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public static void update(Tour tour) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", tour.getName());
        values.put("address", tour.getAddress());
        values.put("price", tour.getPrice());
        values.put("start_date", tour.getStartDate());
        values.put("end_date", tour.getEndDate());

        sqLiteDatabase.update(TABLE_NAME, values, "_id = " + tour.getId(), null);
    }

    public static void delete(int id) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME, "_id = " + id, null);
    }

    public static Cursor getCursor() {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getReadableDatabase();

        String sql = "select * from " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        return cursor;
    }
}

package com.gokisoft.c2010g.note;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteModify {
    public static final String TABLE_NAME = "note";

    public static final String SQL_CREATE_TABLE = "create table note (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tnoidung text,\n" +
            "\tquantrong integer,\n" +
            "\tngaytao varchar(20)\n" +
            ")";

    public static void insert(Note note) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noidung", note.getNoidung());
        values.put("quantrong", note.getQuantrong());
        values.put("ngaytao", note.getNgaytaoStr());

        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public static void update(Note note) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noidung", note.getNoidung());
        values.put("quantrong", note.getQuantrong());
        values.put("ngaytao", note.getNgaytaoStr());

        sqLiteDatabase.update(TABLE_NAME, values, "_id = " + note.get_id(), null);
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

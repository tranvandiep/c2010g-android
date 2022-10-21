package com.gokisoft.c2010g.lesson05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private final static String BD_NAME = "C2010G";
    private final static int VERSION = 1;

    private static DBHelper instance = null;

    private DBHelper(@Nullable Context context) {
        super(context, BD_NAME, null, VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TourDAO.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
}

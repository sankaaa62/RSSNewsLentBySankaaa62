package com.example.rssnewslentbysankaaa62;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "poststore.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "posts"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_URLCHANEL = "urlchanel";
    public static final String COLUMN_URLPOST = "urlpost";
    public static final String COLUMN_TITLPOST = "titlepost";
    public static final String COLUMN_URLIMAGE = "urlimage";
    public static final String COLUMN_DATEPOST = "datepost";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_URLCHANEL + " TEXT, "
                + COLUMN_URLPOST + " TEXT, "
                + COLUMN_TITLPOST + " TEXT, "
                + COLUMN_URLIMAGE + " TEXT, "
                + COLUMN_DATEPOST + " TEXT);");

        // добавление начальных данных
        for (int i = 0; i < 30; i++){
            db.execSQL("INSERT INTO "+ TABLE +" ("
                    + COLUMN_URLCHANEL + ", "
                    + COLUMN_URLPOST + ", "
                    + COLUMN_TITLPOST + ", "
                    + COLUMN_URLIMAGE + ", "
                    + COLUMN_DATEPOST
                    + ") VALUES ('chanel', 'URL', 'Title post', 'image', 'May 24, 2019');");
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}

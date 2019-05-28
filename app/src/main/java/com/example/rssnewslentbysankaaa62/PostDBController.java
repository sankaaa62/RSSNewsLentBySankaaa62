package com.example.rssnewslentbysankaaa62;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PostDBController {

    private static final String DB_NAME = "postsDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE = "posts";

    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_URLCHANEL = "urlchanel";
    public static final String COLUMN_URLPOST = "urlpost";
    public static final String COLUMN_TITLPOST = "titlepost";
    public static final String COLUMN_URLIMAGE = "urlimage";
    public static final String COLUMN_DATEPOST = "datepost";
    public static final String COLUMN_DESCRIPTION = "description";

    // запрос на создание базы
    private static final String DB_CREATE =
            "CREATE TABLE " + TABLE + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_URLCHANEL + " TEXT, "
                    + COLUMN_URLPOST + " TEXT, "
                    + COLUMN_TITLPOST + " TEXT, "
                    + COLUMN_URLIMAGE + " TEXT, "
                    + COLUMN_DATEPOST + " TEXT, "
                    + COLUMN_DESCRIPTION + " TEXT);";

    private final Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public PostDBController(Context ctx) {
        mCtx = ctx;
    }

    // открыть подключение
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    // закрыть подключение
    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }

    // получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        return mDB.query(TABLE, null, null, null, null, null, COLUMN_DATEPOST);
    }

    // получить пост по id
    public Post getPost(long id){

        Post result = new Post();

        String selection = "_id = ?";
        String[] selectionArgs = new String[] {String.valueOf(id)};
        Cursor c = mDB.query(TABLE, null, selection, selectionArgs, null, null, null);
        if(c.moveToFirst()){
            // достаем данные из курсора
            result.postChanelURL = c.getString(c.getColumnIndexOrThrow("urlchanel"));
            result.postURL = c.getString(c.getColumnIndexOrThrow("urlpost"));
            result.postTitle = c.getString(c.getColumnIndexOrThrow("titlepost"));
            result.postImageURL = c.getString(c.getColumnIndexOrThrow("urlimage"));
            result.postDate = c.getString(c.getColumnIndexOrThrow("datepost"));
            result.postDescription = c.getString(c.getColumnIndexOrThrow("description"));
        }
        return result;
    }

    // добавить запись в DB_TABLE
    public void addRec(Post data) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_URLCHANEL, data.postChanelURL);
        cv.put(COLUMN_URLPOST, data.postURL);
        cv.put(COLUMN_TITLPOST, data.postTitle);
        cv.put(COLUMN_URLIMAGE, data.postImageURL);
        cv.put(COLUMN_DATEPOST, data.postDate);
        cv.put(COLUMN_DESCRIPTION, data.postDescription);

        mDB.insert(TABLE, null, cv);
    }

    // удалить запись из DB_TABLE
    public void delRec(long id) {
        mDB.delete(TABLE, COLUMN_ID + " = " + id, null);
    }

    // класс по созданию и управлению БД
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // создаем и заполняем БД
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);

            // добавление начальных данных
            //ContentValues cv = new ContentValues();
            //for (int i = 1; i < 30; i++) {

             //   cv.put(COLUMN_URLCHANEL, "chanel");
             //   cv.put(COLUMN_URLPOST, "URL POST");
             //   cv.put(COLUMN_TITLPOST, "Title post!");
             //   cv.put(COLUMN_URLIMAGE, "URL IMAGE");
             //   cv.put(COLUMN_DATEPOST, "May 24, 2019");

             //   db.insert(TABLE, null, cv);
            //}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
}
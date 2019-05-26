package com.example.rssnewslentbysankaaa62;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

public class MyCursorLoader extends CursorLoader {

    PostDBController db;

    public MyCursorLoader(Context context, PostDBController db) {
        super(context);
        this.db = db;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = db.getAllData();

        return cursor;
    }

}
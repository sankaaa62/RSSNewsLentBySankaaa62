package com.example.rssnewslentbysankaaa62;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;


public class CustomSimpleCursorAdapter extends SimpleCursorAdapter {
    private LayoutInflater inflater;

    public CustomSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }



    @Override
    public void bindView(View convertView, Context context, Cursor cursor) {
        super.bindView(convertView, context, cursor);

        String url = cursor.getString(cursor.getColumnIndexOrThrow("urlimage"));


        if (url != "none"){
            Uri uri = Uri.parse(url);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.postImg);

            Picasso.get().load(uri).into(imageView);
        }

    }


}
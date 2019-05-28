package com.example.rssnewslentbysankaaa62;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<ViewHolder, Void, ViewHolder> {

    @Override
    protected ViewHolder doInBackground(ViewHolder... params) {
        // TODO Auto-generated method stub
        //load image directly
        ViewHolder viewHolder = params[0];
        try {
            URL imageURL = new URL(viewHolder.imageURL);
            viewHolder.bitmap = BitmapFactory.decodeStream(imageURL.openStream());
        } catch (IOException e) {
            // TODO: handle exception
            Log.e("error", "Downloading Image Failed");
            viewHolder.bitmap = null;
        }

        return viewHolder;
    }

    @Override
    protected void onPostExecute(ViewHolder result) {
        // TODO Auto-generated method stub
        if (result.bitmap == null) {
            result.imageView.setImageResource(R.drawable.postthumb_loading);
        } else {
            result.imageView.setImageBitmap(result.bitmap);
        }
    }
}
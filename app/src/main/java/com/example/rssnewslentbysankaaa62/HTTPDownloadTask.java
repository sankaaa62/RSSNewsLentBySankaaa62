package com.example.rssnewslentbysankaaa62;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPDownloadTask extends AsyncTask<
        String,
        Integer,
        PostData[]> {

    @Override
    protected PostData[] doInBackground(String... params) {
        // TODO Auto-generated method stub
        String urlStr = params[0];
        InputStream is = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10*1000);
            connection.setConnectTimeout(10*1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("debug", "The response is: " + response);
            is = connection.getInputStream();

            //read string
            final int bufferSize = 1024;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            while(true) {
                int count = is.read(buffer, 0, bufferSize);
                if(count == -1) {
                    break;
                }

                os.write(buffer);
            }
            os.close();

            String result = new String(os.toByteArray(), "UTF-8");
            Log.d("debug", result);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
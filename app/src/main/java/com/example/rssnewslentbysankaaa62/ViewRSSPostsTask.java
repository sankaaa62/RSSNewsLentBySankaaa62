package com.example.rssnewslentbysankaaa62;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewRSSPostsTask extends AsyncTask<String, Integer, ArrayList<PostData>> {
    private RSSXMLTag currentTag;
    private MainActivity context;

    public ViewRSSPostsTask(MainActivity ctx){
        context = ctx;
    }
    @Override
    protected void onPostExecute(ArrayList<PostData> result) {
        // TODO Auto-generated method stub

        ArrayList<PostData> postDataList = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            postDataList.add(result.get(i));
        }

        PostData[] listData = postDataList.toArray(new PostData[postDataList.size()]);

        ListView listView = (ListView) context.findViewById(R.id.postListView);
        PostItemAdapter itemAdapter = new PostItemAdapter(context,
                R.layout.postitem, listData);
        listView.setAdapter(itemAdapter);
    }

    @Override
    protected ArrayList<PostData> doInBackground(String... params) {
        // TODO Auto-generated method stub

        ArrayList<PostData> postDataList = new ArrayList<PostData>();
        String urlStr = params[0];

        RSSParser rssParser = new RSSParser();
        postDataList = rssParser.ReadRSS(urlStr);

        return postDataList;
    }

}

package com.example.rssnewslentbysankaaa62;

import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

public class ParseRSStoDBTask extends AsyncTask<String, Integer, ArrayList<Post>> {
    private MainActivity context;

    public ParseRSStoDBTask(MainActivity ctx){
        context = ctx;
    }

    @Override
    protected void onPostExecute(ArrayList<Post> result) {
        // TODO Auto-generated method stub

        ArrayList<Post> postList = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            postList.add(result.get(i));
        }

        for (Post post:result) {
            context.db.addRec(post);
        }
    }

    @Override
    protected ArrayList<Post> doInBackground(String... params) {
        // TODO Auto-generated method stub

        String urlStr = params[0];
        RSSParser rssParser = new RSSParser();
        return rssParser.ReadRSS(urlStr);
    }

}

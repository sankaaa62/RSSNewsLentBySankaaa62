package com.example.rssnewslentbysankaaa62;

import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewRSSPostsTask extends AsyncTask<String, Integer, ArrayList<Post>> {
    private RSSXMLTag currentTag;
    private MainActivity context;

    public ViewRSSPostsTask(MainActivity ctx){
        context = ctx;
    }
    @Override
    protected void onPostExecute(ArrayList<Post> result) {
        // TODO Auto-generated method stub

        ArrayList<Post> postList = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            postList.add(result.get(i));
        }

        Post[] listData = postList.toArray(new Post[postList.size()]);

        //ЗАКОМЕНТИЛ ДЛЯ БИЛДА, РАЗГРЕСТИ!!!
 //       ListView listView = (ListView) context.findViewById(R.id.postListView);
 //       PostItemAdapter itemAdapter = new PostItemAdapter(context,
 //               R.layout.postitem, listData);
 //       listView.setAdapter(itemAdapter);
    }

    @Override
    protected ArrayList<Post> doInBackground(String... params) {
        // TODO Auto-generated method stub

        ArrayList<Post> postList = new ArrayList<Post>();
        String urlStr = params[0];

        RSSParser rssParser = new RSSParser();
        postList = rssParser.ReadRSS(urlStr);

        return postList;
    }

}

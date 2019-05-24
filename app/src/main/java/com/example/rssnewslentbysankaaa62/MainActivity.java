package com.example.rssnewslentbysankaaa62;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public PostData[] listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        new RssDataController(this).execute("https://habr.com/ru/rss/best/daily/?fl=ru");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

 //   private void generateDummyData() {
 //       PostData data = null;
 //       listData = new PostData[10];
 //       for (int i = 0; i < 10; i++) {
 //           data = new PostData();
 //           data.postDate = "May 24, 2019";
 //           data.postTitle = "Post " + (i + 1) + " Title: This is the Post Title from RSS Feed";
 //           data.postLink = null;
 //           listData[i] = data;
 //       }
 //   }

}

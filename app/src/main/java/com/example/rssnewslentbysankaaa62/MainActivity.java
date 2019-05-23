package com.example.rssnewslentbysankaaa62;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] listData = new String[]{"Post 1", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6",
            "Post 7", "Post 8", "Post 9", "Post 10", "Post 11", "Post 12", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6",
            "Post 7", "Post 8", "Post 9", "Post 10", "Post 11", "Post 12", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6",
            "Post 7", "Post 8", "Post 9", "Post 10", "Post 11", "Post 12", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6",
            "Post 7", "Post 8", "Post 9", "Post 10", "Post 11", "Post 12", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6",
            "Post 7", "Post 8", "Post 9", "Post 10", "Post 11", "Post 12", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6",
            "Post 7", "Post 8", "Post 9", "Post 10", "Post 11", "Post 12", "Post 2", "Post 3", "Post 4", "Post 5", "Post 6",
            "Post 7", "Post 8", "Post 9", "Post 10", "Post 11", "Post 12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        ListView listView = (ListView)this.findViewById(R.id.postListView);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, R.layout.postitem, listData);
        listView.setAdapter(itemAdapter);
    }
}

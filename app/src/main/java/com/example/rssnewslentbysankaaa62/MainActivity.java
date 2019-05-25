package com.example.rssnewslentbysankaaa62;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    SimpleCursorAdapter postAdapter;
    Cursor postCursor;

    ListView userList;
    TextView header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        header = (TextView)findViewById(R.id.header);
        userList = (ListView)findViewById(R.id.postListView);

       // new ViewRSSPostsTask(this).execute("https://habr.com/ru/rss/best/daily/?fl=ru");

        //времянка
        databaseHelper = new DatabaseHelper(getApplicationContext());

//        ArrayList<PostData> postDataList = getPosts();

//        PostData[] listData = postDataList.toArray(new PostData[postDataList.size()]);
//
//        ListView listView = (ListView) this.findViewById(R.id.postListView);
//        PostItemAdapter itemAdapter = new PostItemAdapter(this,
//                R.layout.postitem, listData);
//        listView.setAdapter(itemAdapter);
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

    @Override
    public void onResume() {
        super.onResume();

        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        postCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);

        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DatabaseHelper.COLUMN_TITLPOST, DatabaseHelper.COLUMN_DATEPOST};

        // формируем столбцы сопоставления
        String[] from = new String[] { DatabaseHelper.COLUMN_TITLPOST, DatabaseHelper.COLUMN_DATEPOST };
        int[] to = new int[] { R.id.postTitleLabel, R.id.postDateLabel };


        // создаем адаптер, передаем в него курсор
        postAdapter = new SimpleCursorAdapter(
                this,
                R.layout.postitem,
                postCursor,
                from,
                to, 0);

        header.setText("Найдено элементов: " + String.valueOf(postCursor.getCount()));
        userList.setAdapter(postAdapter);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        postCursor.close();
    }

    public ArrayList<PostData> getPosts(){
        ArrayList<PostData> posts = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst()){
            do{
                String urlPost = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_URLPOST));
                String titlePost = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_URLPOST));
                String datePost = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_URLPOST));

                posts.add(new PostData(urlPost, titlePost, datePost));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return  posts;
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_URLPOST, DatabaseHelper.COLUMN_TITLPOST, DatabaseHelper.COLUMN_DATEPOST};
        return  db.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

}

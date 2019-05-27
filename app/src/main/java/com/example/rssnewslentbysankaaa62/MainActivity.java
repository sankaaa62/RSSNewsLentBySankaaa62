package com.example.rssnewslentbysankaaa62;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    private static final int CM_DELETE_ID = 1;
    private static final int CM_VIEWFULLPOST_ID = 2;

    Post selectPost;

    ListView postsListView;
    PostDBController db;
    SimpleCursorAdapter scAdapter;
    
    //RSSParser rssParser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        // открываем подключение к БД
        db = new PostDBController(this);
        db.open();

        // формируем столбцы сопоставления
        String[] from = new String[] { PostDBController.COLUMN_TITLPOST, PostDBController.COLUMN_URLCHANEL, PostDBController.COLUMN_URLPOST, PostDBController.COLUMN_DATEPOST };
        int[] to = new int[] { R.id.postTitle, R.id.postChanel, R.id.postURL, R.id.postDate };

        // создаем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(this, R.layout.postitem, null, from, to, 0);
        postsListView = (ListView) findViewById(R.id.lvData);
        postsListView.setAdapter(scAdapter);

        // добавляем контекстное меню к списку
        registerForContextMenu(postsListView);

        // создаем лоадер для чтения данных
        getSupportLoaderManager().initLoader(0, null, this);
    }

    // обработка нажатия кнопки
    public void refreshListView(View view) {
        // добавляем запись
        //Post newPost =new Post();
        //db.addRec(newPost);
        //String urlChanel = "https://habr.com/ru/rss/best/daily/?fl=ru";
        //updateBD(urlChanel);

        // получаем новый курсор с данными
        getSupportLoaderManager().getLoader(0).forceLoad();
    }

    public  void addPost(View view){
        // добавляем запись
        Post newPost =new Post();
        db.addRec(newPost);

        // получаем новый курсор с данными
        getSupportLoaderManager().getLoader(0).forceLoad();
    }

    public  void addHabrPosts(View view){
        String urlChanel = "https://habr.com/ru/rss/best/daily/?fl=ru";
        updateBD(urlChanel);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
        menu.add(0, CM_VIEWFULLPOST_ID, 0, R.string.open_post_record);
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // получаем из пункта контекстного меню данные по пункту списка
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
                    .getMenuInfo();
            // извлекаем id записи и удаляем соответствующую запись в БД
            db.delRec(acmi.id);
            // получаем новый курсор с данными
            getSupportLoaderManager().getLoader(0).forceLoad();

            return true;
        }
        if (item.getItemId() == CM_VIEWFULLPOST_ID) {
            // получаем из пункта контекстного меню данные по пункту списка
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
                    .getMenuInfo();

            selectPost = db.getPost(acmi.id);

            Intent intent;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectPost.postURL));
            startActivity(intent);

            return true;
        }
        return super.onContextItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
        // закрываем подключение при выходе
        db.close();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bndl) {
        return new MyCursorLoader(this, db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        scAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void updateBD(String URL){
        new ParseRSStoDBTask(this).execute(URL);
    }


}
package com.example.lab08.sqliteopenhelper_dahilikullanimi.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.lab08.sqliteopenhelper_dahilikullanimi.Adapter.ListeleAdapter;
import com.example.lab08.sqliteopenhelper_dahilikullanimi.Database.SimpleDatabaseHelper;
import com.example.lab08.sqliteopenhelper_dahilikullanimi.Model.Not;
import com.example.lab08.sqliteopenhelper_dahilikullanimi.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListeleAdapter adapter;
    ArrayList<Not> notlar;
    SimpleDatabaseHelper databaseHelper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            Intent intent = new Intent(getApplicationContext(), NotEkleaActivity.class);
            startActivity(intent);

        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new SimpleDatabaseHelper(getApplicationContext());
        Cursor c = databaseHelper.getAll("select * from notlar");
        notlar = new ArrayList<>();

        //int id, String notBasligi, String notTarihi, String notIcerigi
        // Select sorgusundan dönen tüm değerleri Cursor'da yakalayıp while döngüsü ile her satırı
        // gezip, arraylist'e ekledik.

        while (c.moveToNext()) {
            notlar.add(new Not(
                    c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("baslik")),
                    c.getString(c.getColumnIndex("tarih")),
                    c.getString(c.getColumnIndex("aciklama"))
            ));
        }


        adapter = new ListeleAdapter(notlar, getApplicationContext());
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}

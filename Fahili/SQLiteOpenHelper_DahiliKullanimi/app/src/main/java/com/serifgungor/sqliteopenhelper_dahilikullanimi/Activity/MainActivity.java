package com.serifgungor.sqliteopenhelper_dahilikullanimi.Activity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.serifgungor.sqliteopenhelper_dahilikullanimi.Adapter.ListeleAdapter;
import com.serifgungor.sqliteopenhelper_dahilikullanimi.Database.SimpleDatabaseHelper;
import com.serifgungor.sqliteopenhelper_dahilikullanimi.Model.Not;
import com.serifgungor.sqliteopenhelper_dahilikullanimi.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListeleAdapter adapter;
    ArrayList<Not> notlar;
    SimpleDatabaseHelper databaseHelper;

    public void listeyiTazele(){
        notlar = new ArrayList<>();
        adapter = new ListeleAdapter(notlar,this);
        verileriListele(notlar,databaseHelper);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_add){
            Intent intent = new Intent(getApplicationContext(),NotEkleActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void verileriListele(ArrayList<Not> notlar,SimpleDatabaseHelper databaseHelper){
        Cursor c = databaseHelper.getAll("select * from notlar");
        //int id, String notBasligi, String notTarihi, String notIcerigi
        // Select sorgusundan dönen tüm değerleri Cursor'da yakalayıp
        // while döngüsü ile her satırı gezip, arraylist'e ekledik.
        while (c.moveToNext()){
            notlar.add(new Not(
                    c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("baslik")),
                    c.getString(c.getColumnIndex("tarih")),
                    c.getString(c.getColumnIndex("aciklama"))
            ));
        }
    }

    public void dialogUret(final int id){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.update_not);
        final EditText baslik = dialog.findViewById(R.id.etUpdateBaslik);
        final EditText icerik = dialog.findViewById(R.id.etUpdateIcerik);
        final EditText tarih = dialog.findViewById(R.id.etUpdateTarih);
        Button btnGuncelle = dialog.findViewById(R.id.btnUpdate);


        Cursor c = databaseHelper.getAll("select * from notlar where id="+id);
        while (c.moveToNext()){
            baslik.setText(c.getString(c.getColumnIndex("baslik")));
            icerik.setText(c.getString(c.getColumnIndex("aciklama")));
            tarih.setText(c.getString(c.getColumnIndex("tarih")));
        }



        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("baslik",baslik.getText().toString());
                cv.put("tarih",tarih.getText().toString());
                cv.put("aciklama",icerik.getText().toString());
                String sart = "id = ?";
                String[] sartDeger = {""+id};
                databaseHelper.update("notlar",cv,sart,sartDeger);
                /*
                update tablo set KOLONLAR VE DEĞERLERİ where id=1
                 */
                listeyiTazele();
                dialog.dismiss();

            }
        });



        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new SimpleDatabaseHelper(getApplicationContext());
        notlar = new ArrayList<>();
        verileriListele(notlar,databaseHelper);
        adapter = new ListeleAdapter(notlar,this);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dialogUret(notlar.get(position).getId());
                return false;
            }
        });



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listeyiTazele();
    }
}

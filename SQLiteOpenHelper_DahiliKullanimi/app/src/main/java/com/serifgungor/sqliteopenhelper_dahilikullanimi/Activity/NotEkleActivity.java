package com.serifgungor.sqliteopenhelper_dahilikullanimi.Activity;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serifgungor.sqliteopenhelper_dahilikullanimi.Database.SimpleDatabaseHelper;
import com.serifgungor.sqliteopenhelper_dahilikullanimi.R;

public class NotEkleActivity extends AppCompatActivity {

    EditText etBaslik,etIcerik,etTarih;
    Button btnEkle;
    SimpleDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);

        etBaslik = findViewById(R.id.etBaslik);
        etIcerik = findViewById(R.id.etIcerik);
        etTarih = findViewById(R.id.etTarih);
        btnEkle = findViewById(R.id.btnEkle);

        databaseHelper = new SimpleDatabaseHelper(getApplicationContext());

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues cv = new ContentValues();
                cv.put("baslik",etBaslik.getText().toString());
                cv.put("aciklama",etIcerik.getText().toString());
                cv.put("tarih",etTarih.getText().toString());

                if(databaseHelper.add("notlar",cv)!=-1){
                    Toast.makeText(getApplicationContext(),"Not eklendi",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Not eklenemedi",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

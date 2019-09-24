package com.serifgungor.sqliteopenhelper_dahilikullanimi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.serifgungor.sqliteopenhelper_dahilikullanimi.Activity.MainActivity;
import com.serifgungor.sqliteopenhelper_dahilikullanimi.Database.SimpleDatabaseHelper;
import com.serifgungor.sqliteopenhelper_dahilikullanimi.Model.Not;
import com.serifgungor.sqliteopenhelper_dahilikullanimi.R;

import java.util.ArrayList;

public class ListeleAdapter extends BaseAdapter {
    ArrayList<Not> notlar;
    Activity activity;
    LayoutInflater layoutInflater;
    SimpleDatabaseHelper databaseHelper;

    public ListeleAdapter(ArrayList<Not> notlar,Activity context){
        this.notlar = notlar;
        this.activity = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        databaseHelper = new SimpleDatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return notlar.size();
    }

    @Override
    public Object getItem(int position) {
        return notlar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       View v = layoutInflater.inflate(R.layout.custom_row,null);
        TextView tvBaslik = v.findViewById(R.id.tvBaslik);
        TextView tvTarih = v.findViewById(R.id.tvTarih);
        ImageView ivDelete = v.findViewById(R.id.ivDelete);

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] values = { notlar.get(position).getId()+"" };
                databaseHelper.delete("notlar","id=?",values);
                notlar.remove(notlar.get(position));
                activity.startActivity(new Intent(activity,MainActivity.class));
                activity.finish();

            }
        });



        tvTarih.setText(notlar.get(position).getNotTarihi()+"");
        tvBaslik.setText(notlar.get(position).getNotBasligi()+"");

       return v;
    }
}

package com.example.lab08.sqliteopenhelper_dahilikullanimi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab08.sqliteopenhelper_dahilikullanimi.Model.Not;
import com.example.lab08.sqliteopenhelper_dahilikullanimi.R;

import java.util.ArrayList;

public class ListeleAdapter extends BaseAdapter {
    ArrayList<Not> notlar;
    Context context;
    LayoutInflater layoutInflater;

    public ListeleAdapter(ArrayList<Not> notlar, Context context) {
        this.notlar = notlar;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.custom_row, null);
        TextView tvBaslik = v.findViewById(R.id.tvBaslik);
        TextView tvTarih = v.findViewById(R.id.tvTarih);

        tvTarih.setText(notlar.get(position).getNotTarihi() + "");
        tvBaslik.setText(notlar.get(position).getNotBasligi() + "");

        return v;
    }
}

package com.example.aseps.caat.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aseps.caat.Features.kuisioner.KuisionerActivity;
import com.example.aseps.caat.Features.observasi.ObservasiActivity;
import com.example.aseps.caat.Features.wawancara.WawancaraActivity;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.metode.ItemMetode;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MetodeAdapter extends RecyclerView.Adapter<MetodeAdapter.MetodeViewHolder> {
    private ArrayList<ItemMetode> itemMetodeArrayList;
    private Context context;

    public MetodeAdapter(ArrayList<ItemMetode> itemMetodeArrayList, Context context) {
        this.itemMetodeArrayList = itemMetodeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MetodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metode, parent, false);
        return new MetodeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MetodeViewHolder holder, int position) {
        final ItemMetode itemMetode = itemMetodeArrayList.get(position);

        holder.nomor.setText("Nomor Form " + itemMetode.getNo_form());
        holder.idform.setText("Id Form " + itemMetode.getId_form());
        holder.nama.setText(itemMetode.getMetode());

        holder.cvMetode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (itemMetode.getMetode()) {
                    case "Questionnaire": {
                        SharedPreferences sp = context.getSharedPreferences("profil", MODE_PRIVATE);
                        SharedPreferences.Editor spedit = sp.edit();
                        spedit.putString("metode", itemMetode.getMetode());
                        spedit.putString("id_form", String.valueOf(itemMetode.getId_form()));
                        spedit.putString("no_form", String.valueOf(itemMetode.getNo_form()));
                        spedit.apply();
                        Intent intent = new Intent(context, KuisionerActivity.class);
                        context.startActivity(intent);
                        break;
                    }
                    case "Interview": {
                        SharedPreferences sp = context.getSharedPreferences("profil", MODE_PRIVATE);
                        SharedPreferences.Editor spedit = sp.edit();
                        spedit.putString("metode", itemMetode.getMetode());
                        spedit.putString("id_form", String.valueOf(itemMetode.getId_form()));
                        spedit.putString("no_form", String.valueOf(itemMetode.getNo_form()));
                        spedit.apply();
                        Intent intent = new Intent(context, WawancaraActivity.class);
                        context.startActivity(intent);
                        break;
                    }
                    case "Observation": {
                        SharedPreferences sp = context.getSharedPreferences("profil", MODE_PRIVATE);
                        SharedPreferences.Editor spedit = sp.edit();
                        spedit.putString("metode", itemMetode.getMetode());
                        spedit.putString("id_form", String.valueOf(itemMetode.getId_form()));
                        spedit.putString("no_form", String.valueOf(itemMetode.getNo_form()));
                        spedit.apply();
                        Intent intent = new Intent(context, ObservasiActivity.class);
                        context.startActivity(intent);
                        break;
                    }
                }
            }
        });
//        holder.im_logo_perusahaan.setImageDrawable(itemHome.getLogo_perusahaan());
    }

    @Override
    public int getItemCount() {
        return this.itemMetodeArrayList.size();
    }

    class MetodeViewHolder extends RecyclerView.ViewHolder {
        TextView nomor, nama, idform;
        CardView cvMetode;

        MetodeViewHolder(View itemView) {
            super(itemView);
            nomor = itemView.findViewById(R.id.tv_nomor_metode);
            idform = itemView.findViewById(R.id.tv_id_form);
            nama = itemView.findViewById(R.id.tv_nama_metode);
            cvMetode = itemView.findViewById(R.id.cv_item_metode);
        }
    }
}
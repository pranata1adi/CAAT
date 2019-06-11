package com.example.aseps.caat.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Features.stackholder.StakeholderActivity;
import com.example.aseps.caat.Temp.home.ItemHome;
import com.example.aseps.caat.Temp.home.ItemPB;

import static com.example.aseps.caat.helper.UrlAkses.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ArrayList<ItemHome> itemHomeArrayList;
    private Context context;
    private ArrayList<ItemHome> itemHomes;
    private ArrayList<ItemPB> itemPBS;

    public HomeAdapter(ArrayList<ItemHome> itemHomeArrayList, Context context, ArrayList<ItemHome> itemHomes, ArrayList<ItemPB> itemPBS) {
        this.itemHomeArrayList = itemHomeArrayList;
        this.context = context;
        this.itemHomes = itemHomes;
        this.itemPBS = itemPBS;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new HomeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        int x = 0, y = 0;

        final ItemHome itemHome = itemHomes.get(position);
//        final ItemPB itemPB = itemPBArrayList.get(position);
        holder.nama.setText(itemHome.getTitle());
        holder.lokasi.setText(itemHome.getAddress());
        holder.deadline.setText(itemHome.getDeadline());
        holder.cvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StakeholderActivity.class);
                intent.putExtra("ITEM", itemHome);
                context.startActivity(intent);
            }
        });
        Glide.with(context)
                .load(gambar + itemHome.getLogo())
                .into(holder.logo);

        for (ItemPB itemHome1 : itemPBS) {
            double np = itemHome1.getNilaiPb();
            double sa = itemHome1.getStatusAll();
            Log.d("npnya", String.valueOf(np));
            Log.d("npnya", String.valueOf(sa));
            Log.d("npnya", String.valueOf(np / sa));
            double hasil = np / sa;
            holder.pb.setProgress(Integer.parseInt(new DecimalFormat("#").format(hasil * 100)));
            holder.nilaipb.setText(new DecimalFormat("#").format(hasil * 100) + " %");
        }
//        String nilai = String.valueOf(sa / np);
//        Log.d("nilaiiiiZ", nilai);
//        }
//        holder.pb.setProgress(Integer.parseInt(new DecimalFormat("#").format(Double.parseDouble(itemHome.getNilaiPB()) * 100)));
//        holder.nilaipb.setText(new DecimalFormat("#").format(Double.parseDouble(itemHome.getNilaiPB()) * 100) + " %");
    }


    @Override
    public int getItemCount() {
        return this.itemHomes.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView nama, lokasi, deadline, nilaipb;
        ImageView logo;
        ProgressBar pb;
        CardView cvHome;

        HomeViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_nama_perusahaan);
            nilaipb = itemView.findViewById(R.id.tvPb);
            lokasi = itemView.findViewById(R.id.tv_lokasi_perusahaan);
            deadline = itemView.findViewById(R.id.tv_deadline_perusahaan);
            logo = itemView.findViewById(R.id.iv_logo_perusahaan);
            pb = itemView.findViewById(R.id.pb_proyek);
            cvHome = itemView.findViewById(R.id.cv_item_home);
        }
    }
}
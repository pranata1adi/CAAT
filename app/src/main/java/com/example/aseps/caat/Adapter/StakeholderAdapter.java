package com.example.aseps.caat.Adapter;

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

import com.example.aseps.caat.Features.metode.MetodeActivity;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.home.ItemHome;
import com.example.aseps.caat.Temp.stackholder.ItemStakeholder;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class StakeholderAdapter extends RecyclerView.Adapter<StakeholderAdapter.StakeholderViewHolder> {
    private ArrayList<ItemStakeholder> itemStakeholderArrayList;
    private Context context;

    public StakeholderAdapter(ArrayList<ItemStakeholder> itemStakeholderArrayList, Context context) {
        this.itemStakeholderArrayList = itemStakeholderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StakeholderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stakeholder, parent, false);
        return new StakeholderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StakeholderViewHolder holder, int position) {
        final ItemStakeholder itemStakeholder = itemStakeholderArrayList.get(position);
        holder.nama.setText(itemStakeholder.getPosition());
        holder.cvStakeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = context.getSharedPreferences("profil", MODE_PRIVATE);
                SharedPreferences.Editor spedit = sp.edit();
                spedit.putString("posisi", itemStakeholder.getPosition());
                spedit.apply();
                Intent intent = new Intent(context, MetodeActivity.class);
                context.startActivity(intent);
            }
        });
//        holder.im_logo_perusahaan.setImageDrawable(itemHome.getLogo_perusahaan());
    }

    @Override
    public int getItemCount() {
        return this.itemStakeholderArrayList.size();
    }

    public class StakeholderViewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        CardView cvStakeholder;

        public StakeholderViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_stakeholder);
            cvStakeholder = itemView.findViewById(R.id.cv_item_stakeholder);
        }
    }
}

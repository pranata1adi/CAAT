package com.example.aseps.caat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.observasi.ItemObservasi;

import java.util.ArrayList;

public class ObservasiAdapter extends RecyclerView.Adapter<ObservasiAdapter.ViewHolder> {
    private ArrayList<ItemObservasi> itemObservasiArrayList;
    private Context context;

    public ObservasiAdapter(ArrayList<ItemObservasi> itemObservasiArrayList, Context context) {
        this.itemObservasiArrayList = itemObservasiArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ObservasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_observasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObservasiAdapter.ViewHolder holder, int position) {
        final  ItemObservasi itemObservasi = itemObservasiArrayList.get(position);
        holder.pertanyaan.setText(itemObservasi.getQuestion());
        holder.jawaban.setText(itemObservasiArrayList.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return this.itemObservasiArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView pertanyaan;
        EditText jawaban;

        ViewHolder(View itemView) {
            super(itemView);
            pertanyaan = itemView.findViewById(R.id.tvPertanyaan);
            jawaban = itemView.findViewById(R.id.etJawaban);
            jawaban.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    itemObservasiArrayList.get(getAdapterPosition()).setAnswer(jawaban.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
    }

    public ArrayList<ItemObservasi> getArrayList() {
        return itemObservasiArrayList;
    }
}
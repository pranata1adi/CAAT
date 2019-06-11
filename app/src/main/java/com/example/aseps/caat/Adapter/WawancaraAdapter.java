package com.example.aseps.caat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.wawancara.ItemWawancara;

import java.util.ArrayList;

public class WawancaraAdapter extends RecyclerView.Adapter<WawancaraAdapter.ViewHolder> {
    private ArrayList<ItemWawancara> itemWawancaraArrayList;
    private Context context;

    public WawancaraAdapter(ArrayList<ItemWawancara> itemWawancaraArrayList, Context context) {
        this.itemWawancaraArrayList = itemWawancaraArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wawancara, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WawancaraAdapter.ViewHolder holder, final int position) {
        final ItemWawancara itemWawancara = itemWawancaraArrayList.get(position);
        holder.pertanyaan.setText(itemWawancara.getQuestion());
        holder.jawaban.setText(itemWawancaraArrayList.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return this.itemWawancaraArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView pertanyaan;
        EditText jawaban;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            pertanyaan = itemView.findViewById(R.id.tvPertanyaan_wwncr);
            cardView = itemView.findViewById(R.id.cv_wawancara);
            jawaban = itemView.findViewById(R.id.etJawaban);
            jawaban.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    itemWawancaraArrayList.get(getAdapterPosition()).setAnswer(jawaban.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
    }

    public ArrayList<ItemWawancara> getArrayList() {
        return itemWawancaraArrayList;
    }
}
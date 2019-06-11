package com.example.aseps.caat.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.kuisioner.ItemKuisioner;

import java.util.ArrayList;

public class KuisionerAdapter extends RecyclerView.Adapter<KuisionerAdapter.ViewHolder> {
    private ArrayList<ItemKuisioner> itemKuisionerArrayList;
    private Context context;
    //    private int nilaiSkg, nilai1 = 0, nilai2 = 0, nilai3 = 0, nilai4 = 0, nilai5 = 0;
    private final OnEditTextChanged onEditTextChanged;

    public KuisionerAdapter(ArrayList<ItemKuisioner> itemKuisionerArrayList, Context context) {
        this.itemKuisionerArrayList = itemKuisionerArrayList;
        this.context = context;
        this.onEditTextChanged = (OnEditTextChanged) context;
    }

    @NonNull
    @Override
    public KuisionerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kuisioner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final KuisionerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final ItemKuisioner itemKuisioner = itemKuisionerArrayList.get(position);
        holder.pertanyaan.setText(itemKuisioner.getQuestion());
        holder.tvNilaiMaksimal.setText(String.valueOf(itemKuisioner.getMaksimal()));
        holder.jawaban1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!s.toString().equals("")) {
                        Log.d("nilai1", s.toString());
                        itemKuisioner.setJawaban1(Integer.parseInt(s.toString()));
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    } else {
                        itemKuisioner.setJawaban1(0);
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.jawaban2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!s.toString().equals("")) {
                        Log.d("nilai2", s.toString());
                        itemKuisioner.setJawaban2(Integer.parseInt(s.toString()));
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    } else {
                        itemKuisioner.setJawaban2(0);
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.jawaban3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!s.toString().equals("")) {
                        Log.d("nilai3", s.toString());
                        itemKuisioner.setJawaban3(Integer.parseInt(s.toString()));
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    } else {
                        itemKuisioner.setJawaban3(0);
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.ibUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemKuisioner.setJalurnya("klik");
                onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                holder.tvFile.setText(itemKuisioner.getJalurnya());
            }
        });

        try {
            if (!itemKuisioner.getFile().equals(""))
                holder.tvFile.setText(itemKuisioner.getFile());

            if (!itemKuisioner.getJalurnya().equals("") && !itemKuisioner.getJalurnya().equals("klik"))
                holder.tvFile.setText(itemKuisioner.getJalurnya());
            else holder.tvFile.setText(itemKuisioner.getFile());

            if (!String.valueOf(itemKuisioner.getJawaban1()).equals(""))
                holder.jawaban1.setText(itemKuisioner.getJawaban1());
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.jawaban4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!s.toString().equals("")) {
                        Log.d("nilai4", s.toString());
                        itemKuisioner.setJawaban4(Integer.parseInt(s.toString()));
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    } else {
                        itemKuisioner.setJawaban4(0);
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.jawaban5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!s.toString().equals("")) {
                        Log.d("nilai5", s.toString());
                        itemKuisioner.setJawaban5(Integer.parseInt(s.toString()));
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    } else {
                        itemKuisioner.setJawaban5(0);
                        onEditTextChanged.onTextChanged(position, itemKuisionerArrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemKuisionerArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton ibUpload;
        TextView pertanyaan, tvNilaiMaksimal, tvFile;
        EditText jawaban1, jawaban2, jawaban3, jawaban4, jawaban5;

        ViewHolder(View itemView) {
            super(itemView);
            tvFile = itemView.findViewById(R.id.tvFile);
            ibUpload = itemView.findViewById(R.id.ibUpload);
            jawaban1 = itemView.findViewById(R.id.etAnswer1);
            jawaban2 = itemView.findViewById(R.id.etAnswer2);
            jawaban3 = itemView.findViewById(R.id.etAnswer3);
            jawaban4 = itemView.findViewById(R.id.etAnswer4);
            jawaban5 = itemView.findViewById(R.id.etAnswer5);
            pertanyaan = itemView.findViewById(R.id.tvPertanyaan);
            tvNilaiMaksimal = itemView.findViewById(R.id.tvNilaiMaksimal);
        }
    }


    public interface OnEditTextChanged {
        void onTextChanged(int position, ArrayList<ItemKuisioner> itemKuisioners);
    }
}

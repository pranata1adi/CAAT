package com.example.aseps.caat.Features.metode;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aseps.caat.Adapter.MetodeAdapter;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.home.ItemHome;
import com.example.aseps.caat.Temp.metode.ItemMetode;

import java.util.ArrayList;
import java.util.Objects;

public class MetodeActivity extends AppCompatActivity implements MetodeView {
    RecyclerView rv_metode;
    MetodeAdapter metodeAdapter;
    ArrayList<ItemMetode> itemMetodeArrayList = new ArrayList<>();
    String tunggu = "Harap Menunggu", idprojek, idaudit, error = "Periksa Koneksi Anda", posisi;

    MetodePresenter metodePresenter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rv_metode = findViewById(R.id.rv_metode);
        metodePresenter = new MetodePresenter(this);
        SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
        idprojek = sp.getString("idprojek", "");
        idaudit = sp.getString("idauditor", "");
        posisi = sp.getString("posisi", "");
        Log.d("posisi", posisi);

        Log.d("sales", idprojek);
        Log.d("sales", idaudit);

        metodePresenter.getMetode(idprojek, idaudit, posisi);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getMetodeStart() {
        Toast.makeText(this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getMetodeCompleted(ArrayList<ItemMetode> itemMetodeArrayList) {
        metodeAdapter = new MetodeAdapter(itemMetodeArrayList, this);
        rv_metode.setAdapter(metodeAdapter);
        rv_metode.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void getMetodeFailed(String message) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
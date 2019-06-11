package com.example.aseps.caat.Features.stackholder;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aseps.caat.Adapter.StakeholderAdapter;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.home.ItemHome;
import com.example.aseps.caat.Temp.stackholder.ItemStakeholder;

import java.util.ArrayList;
import java.util.Objects;

public class StakeholderActivity extends AppCompatActivity implements StakeHolderView {
    RecyclerView rv_stakeholder;
    StakeholderAdapter stakeholderAdapter;
    String tunggu = "Harap Menunggu", idprojek, idaudit, error = "Periksa Koneksi Anda";
    StakeHolderPresenter stakeHolderPresenter;
    ItemHome itemHome;
    ArrayList<ItemHome> itemHomeArrayList = new ArrayList<>();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stakeholder);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rv_stakeholder = findViewById(R.id.rv_stakeholder);
        stakeHolderPresenter = new StakeHolderPresenter(this);
        if (getIntent().getParcelableExtra("ITEM") != null) {
            itemHome = getIntent().getParcelableExtra("ITEM");
            SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
            SharedPreferences.Editor spedit = sp.edit();
            spedit.putString("idprojek", String.valueOf(itemHome.getId_project()));
            spedit.apply();
            idprojek = String.valueOf(itemHome.getId_project());
            idaudit = String.valueOf(itemHome.getId_field_auditor());
            stakeHolderPresenter.getStackHolder(idprojek, idaudit);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getStakeHolderStart() {
        Toast.makeText(this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getStakeHolderCompleted(ArrayList<ItemStakeholder> itemStakeholderArrayList) {
        itemHome = getIntent().getParcelableExtra("ITEM");
        itemHomeArrayList.add(itemHome);
        stakeholderAdapter = new StakeholderAdapter(itemStakeholderArrayList, this);
        rv_stakeholder.setAdapter(stakeholderAdapter);
        rv_stakeholder.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void getStakeHolderFailed(String message) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

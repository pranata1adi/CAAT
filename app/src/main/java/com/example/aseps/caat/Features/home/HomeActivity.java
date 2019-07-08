package com.example.aseps.caat.Features.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aseps.caat.Adapter.HomeAdapter;
import com.example.aseps.caat.Db.MyDatabase;
import com.example.aseps.caat.Features.Login.ChangePassword.GantiPasswordActivity;
import com.example.aseps.caat.Features.Login.Login.LoginActivity;
import com.example.aseps.caat.Features.Login.Profil.MyProfilActivity;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.home.ItemHome;
import com.example.aseps.caat.Temp.home.ItemPB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeView {
    RecyclerView rv_home;
    HomeAdapter homeAdapter;
    HomePresenter homePresenter;
    SwipeRefreshLayout swipeRefreshLayout;
    MyDatabase myDatabase;
    ArrayList<ItemPB> itemPBArrayList = new ArrayList<>();
    String keluar = "Tekan Sekali Lagi Untuk Keluar", error = "Periksa Koneksi Anda",
            tunggu = "Harap Menunggu", idprojek;
//    AVLoadingIndicatorView av;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe);
        rv_home = findViewById(R.id.rv_home);
        homePresenter = new HomePresenter(this);
        myDatabase = new MyDatabase(this);
        itemPBArrayList.addAll(myDatabase.getAll());

//        cekUpdate();

        SharedPreferences sp = this.getSharedPreferences("profil", MODE_PRIVATE);
        idprojek = sp.getString("idauditor", "");
        homePresenter.home(idprojek);
        Log.d("idprojek", idprojek);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                myDatabase.deleteItem();
                SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
                idprojek = sp.getString("idauditor", "");
                homePresenter.home(idprojek);
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

//    private void showLoading() {
//        av.smoothToShow();
//    }
//
//    private void hideLoading() {
//        av.smoothToHide();
//    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            super.onBackPressed();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        } else {
            Toast.makeText(this, keluar, Toast.LENGTH_SHORT).show();
        }

        back_pressed = System.currentTimeMillis();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_my_profile) {

            Intent intent = new Intent(this, MyProfilActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_ganti_password) {

            Intent intent = new Intent(this, GantiPasswordActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor sp =
                    getSharedPreferences("login", MODE_PRIVATE).edit();
            sp.clear();
            sp.apply();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getHomeStart() {
//        showLoading();
        Toast.makeText(this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getHomeCompleted(ArrayList<ItemHome> itemHomeArrayList) {
//        hideLoading();
        myDatabase.deleteItem();
        ArrayList<ItemHome> itemHomes = new ArrayList<>();
        Set<Integer> idprojek = new HashSet<>();
        Collections.sort(itemHomeArrayList, new Comparator<ItemHome>() {
            @Override
            public int compare(ItemHome itemHome, ItemHome itemHome1) {
                int nilai = itemHome.getId_project();
                int nilai1 = itemHome1.getId_project();
                return nilai - nilai1;
            }
        });

        for (ItemHome itemHome1 : itemHomeArrayList) {
            myDatabase.addDB(itemHome1);
            if (idprojek.add(itemHome1.getId_project())) {
                itemHomes.add(itemHome1);
//                Log.d("itemhome",itemHome1.)
            }
        }

        itemPBArrayList.addAll(myDatabase.getAll());

        homeAdapter = new HomeAdapter(itemHomeArrayList, this, itemHomes, itemPBArrayList);
        rv_home.setAdapter(homeAdapter);
        rv_home.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void getHomeFailed(String message) {
//        hideLoading();
        Log.d("messagenya", message);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
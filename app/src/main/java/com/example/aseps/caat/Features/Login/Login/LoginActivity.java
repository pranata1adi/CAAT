package com.example.aseps.caat.Features.Login.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.aseps.caat.Features.home.HomeActivity;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.login.ItemProfil;
import com.example.aseps.caat.helper.CheckConnection;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

public class LoginActivity extends Activity implements LoginView {
    Button btn;
    EditText etuser, etpassword;
    String seterror = "User dan Password ", error = "Tidak Boleh Kosong", tunggu = "Harap Menunggu",
            salah = "Username atau Password Tidak Sesuai";
    LoginPresenter loginPresenter;
    String cekLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.button_login);
        etuser = findViewById(R.id.etUsername);
        etpassword = findViewById(R.id.etPassword);
        loginPresenter = new LoginPresenter(this);
        cekFirstLogin();

        if (cekLogin.equals("login")) {
            Log.d("inilogin",cekLogin);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etuser.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this, seterror + error,
                            Toast.LENGTH_SHORT).show();
                if (etpassword.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this, seterror + error,
                            Toast.LENGTH_SHORT).show();
                if (!etuser.getText().toString().equals("") && !etuser.getText().toString().equals("")) {
                    if (!etuser.getText().toString().equals("") &&
                            !etpassword.getText().toString().equals("")) {
                        if (CheckConnection.hasConnectivity(getApplicationContext())) {
                            loginPresenter.Login(etuser.getText().toString(), etpassword.getText().toString());
                        }
                    }
                }
            }
        });
    }

    public void cekFirstLogin() {
        SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
        cekLogin = sh.getString("inilogin", "");
    }

    @Override
    public void getItemLoginStart() {
        Toast.makeText(LoginActivity.this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getItemLoginCompleted(ArrayList<ItemProfil> itemProfilArrayList) {
        for (ItemProfil itemProfil : itemProfilArrayList) {
            SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
            SharedPreferences.Editor spedit = sp.edit();
            spedit.putString("idauditor", String.valueOf(itemProfil.getId_auditor_lapangan()));
            spedit.putString("name", itemProfil.getName());
            spedit.putString("foto", itemProfil.getFoto());
            spedit.putString("email", itemProfil.getEmail());
            spedit.putString("hp", itemProfil.getHp());
            spedit.putString("password", itemProfil.getPassword());
            spedit.putString("address", itemProfil.getAddress());
            spedit.putString("city", itemProfil.getCity());
            spedit.putString("registered", itemProfil.getRegistered());
            spedit.apply();
            SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor spedit1 = sp1.edit();
            spedit1.putString("inilogin", "login");
            spedit1.apply();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void getItemLoginFailed(String message) {
        Toast.makeText(LoginActivity.this, salah, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}
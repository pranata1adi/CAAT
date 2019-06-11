package com.example.aseps.caat.Features.Login.ChangePassword;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aseps.caat.R;

import java.util.Objects;

public class GantiPasswordActivity extends AppCompatActivity implements GantiPasswordView {
    EditText ganti_password, ganti_password2;
    Button btnSimpan;
    GantiPasswordPresenter gantiPasswordPresenter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ganti_password = findViewById(R.id.ganti_password);
        ganti_password2 = findViewById(R.id.ganti_password2);
        btnSimpan = findViewById(R.id.btnSimpan);
        gantiPasswordPresenter = new GantiPasswordPresenter(getBaseContext(), this);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ganti_password.getText().toString().equals(ganti_password2.getText().toString())) {
                    Toast.makeText(GantiPasswordActivity.this, "Password Baru Tidak Sesuai",
                            Toast.LENGTH_SHORT).show();
                } else {
                    gantiPasswordPresenter.updatePassword(ganti_password.getText().toString());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getGantiPasswordStart() {
        //mau diapain
    }

    @Override
    public void getGantiPasswordCompleted(String password) {
        //mau diapain
    }

    @Override
    public void getGantiPasswordFailed(String message) {
        //mau diapain
    }
}
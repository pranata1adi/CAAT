package com.example.aseps.caat.Features.observasi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aseps.caat.Adapter.ObservasiAdapter;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.observasi.ItemObservasi;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.aseps.caat.helper.UrlAkses.*;

public class ObservasiActivity extends AppCompatActivity implements ObservasiView {
    Button imageButton;
    Button send, btnDownload;
    ObservasiAdapter adapter;
    RecyclerView recyclerView;
    ObservasiPresenter observasiPresenter;
    TextView noForm, metode, tujuan, tvTitle;
    ArrayList<ItemObservasi> itemObservasis = new ArrayList<>();
    String idprojek, idaudit, posisi, metod, idform, jalurnya = "kosong";
    String tunggu = "Harap Menunggu", error = "Periksa Koneksi Anda", dwl = "Download Berhasil",
            berhasil = "Upload Data Berhasil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observasi);
        observasiPresenter = new ObservasiPresenter(this);

        tvTitle = findViewById(R.id.tvTitle);
        btnDownload = findViewById(R.id.download);
        noForm = findViewById(R.id.tv_nmr_form_wwncr);
        metode = findViewById(R.id.nm_metode_wwncr);
        tujuan = findViewById(R.id.tv_tujuan_metode);
        recyclerView = findViewById(R.id.rvObservasi);
        send = findViewById(R.id.btnSend);
        imageButton = findViewById(R.id.ibInput);

        SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
        idprojek = sp.getString("idprojek", "");
        idaudit = sp.getString("idauditor", "");
        posisi = sp.getString("posisi", "");
        metod = sp.getString("metode", "");
        idform = sp.getString("id_form", "");
        final String SourceFilname = "api/form/download/" + idform;

        observasiPresenter.getObservasi(idprojek, idaudit, posisi, metod, idform);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("haha", "haha");
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("method", "Observation");

                String[] fileType = {
                        "application/pdf"
                };

                client.post(URL + download + idform, params, new BinaryHttpResponseHandler(fileType) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                        try {
                            Log.d("haha", "haha");
                            String DestinationName = SourceFilname.substring(SourceFilname.lastIndexOf('/') + 1) + ".pdf";
                            File _f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), DestinationName);
                            FileOutputStream output = new FileOutputStream(_f);
                            output.write(binaryData);
                            output.close();
                            Toast.makeText(ObservasiActivity.this, dwl, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                        Log.d("errordi", String.valueOf(error));
                    }
                });
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int permissionCheck = ContextCompat.checkSelfPermission(ObservasiActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ObservasiActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        Toast.makeText(ObservasiActivity.this, "File gagal", Toast.LENGTH_LONG).show();
                    } else {
                        getFile();
                    }
                } else {
                    RequestRunTimePermission();
                }
            }
        });

        kirim();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REQUEST_CODE_PICK_FILE) {
            if (resultCode == RESULT_OK) {
                ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                jalurnya = list.get(0).getPath();
                tvTitle.setText(list.get(0).getName());
            }
        }
    }

    public void RequestRunTimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            getFile();
        }
    }

    public void getFile() {
        Intent intent4 = new Intent(this, NormalFilePickActivity.class);
        intent4.putExtra(Constant.MAX_NUMBER, 1);
        intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"xlsx", "xls", "doc", "docx",
                "ppt", "pptx", "pdf", "zip"});
        startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
    }

    @Override
    public void getObservasiStart() {
        Toast.makeText(this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getObservasiCompleted(ArrayList<ItemObservasi> itemObservasiArrayList) {
        SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
        noForm.setText("Nomor Form : " + sp.getString("no_form", ""));
        metode.setText("Metode : " + sp.getString("metode", ""));

        for (ItemObservasi itemObservasi : itemObservasiArrayList) {
            SharedPreferences.Editor spedit = sp.edit();
            spedit.putString("goal", itemObservasi.getGoal());
            spedit.apply();
            tujuan.setText("Tujuan : " + itemObservasi.getGoal());
            tvTitle.setText(itemObservasi.getFile());
        }

        Toast.makeText(ObservasiActivity.this, berhasil, Toast.LENGTH_SHORT).show();
        adapter = new ObservasiAdapter(itemObservasiArrayList, this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getObservasiFailed(String message) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postObservasiStart() {
    }

    @Override
    public void postObservasiCompleted(String message) {
        Toast.makeText(this, berhasil, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postObservasiFailed(String message) {
    }

    public void kirim() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemObservasis = adapter.getArrayList();
                SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
                String nama = sp.getString("name", "");
                observasiPresenter.postObservasi(idform, nama, itemObservasis, jalurnya);
            }
        });
    }
}
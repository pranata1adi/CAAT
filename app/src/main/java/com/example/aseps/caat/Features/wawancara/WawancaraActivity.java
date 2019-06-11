package com.example.aseps.caat.Features.wawancara;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
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

import com.example.aseps.caat.Adapter.WawancaraAdapter;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.wawancara.ItemWawancara;
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

public class WawancaraActivity extends AppCompatActivity implements WawancaraView {
    Button imageButton;
    WawancaraAdapter adapter;
    Button send, btnDownload;
    RecyclerView recyclerView;
    WawancaraPresenter wawancaraPresenter;
    TextView noForm, metode, tujuan, tvTitle;
    ArrayList<ItemWawancara> itemWawancaraArrayList = new ArrayList<>();
    String idprojek, idaudit, posisi, metod, idform, jalurnya = "kosong", SourceFilname;
    String tunggu = "Harap Menunggu", error = "Periksa Koneksi Anda",
            berhasil = "Upload Data Berhasil", dnl = "Download Form Berhasil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wawancara);
        wawancaraPresenter = new WawancaraPresenter(this);

        send = findViewById(R.id.btnSend);
        tvTitle = findViewById(R.id.tvTitle);
        imageButton = findViewById(R.id.ibInput);
        btnDownload = findViewById(R.id.download);
        metode = findViewById(R.id.nm_metode_wwncr);
        tujuan = findViewById(R.id.tv_tujuan_metode);
        noForm = findViewById(R.id.tv_nmr_form_wwncr);
        recyclerView = findViewById(R.id.rv_wawancara);

        SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
        idprojek = sp.getString("idprojek", "");
        idaudit = sp.getString("idauditor", "");
        posisi = sp.getString("posisi", "");
        metod = sp.getString("metode", "");
        idform = sp.getString("id_form", "");
        SourceFilname = "api/form/download/" + idform;

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("haha", "haha");
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("method", "Interview");

                String[] fileType = {
                        "application/pdf"
                };

                Log.d("urlnyaaaaaa", URL + download + idform);
                client.post(URL + download + idform, params, new BinaryHttpResponseHandler(fileType) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                        try {
                            Log.d("haha", "hahah");
                            String DestinationName = SourceFilname.substring(SourceFilname.lastIndexOf('/') + 1) + ".pdf";
                            File _f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), DestinationName);
                            FileOutputStream output = new FileOutputStream(_f);
                            output.write(binaryData);
                            output.close();
                            Toast.makeText(WawancaraActivity.this, dnl, Toast.LENGTH_SHORT).show();
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
                    int permissionCheck = ContextCompat.checkSelfPermission(WawancaraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(WawancaraActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        Toast.makeText(WawancaraActivity.this, "File gagal", Toast.LENGTH_LONG).show();
                    } else {
                        getFile();
                    }
                } else {
                    RequestRunTimePermission();
                }
            }
        });

        wawancaraPresenter.getWawancara(idprojek, idaudit, posisi, metod, idform);

        kirim();
    }

    public void getFile() {
        Intent intent4 = new Intent(this, NormalFilePickActivity.class);
        intent4.putExtra(Constant.MAX_NUMBER, 1);
        intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"xlsx", "xls", "doc", "docx",
                "ppt", "pptx", "pdf", "zip"});
        startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
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

    @Override
    public void onRequestPermissionsResult(int RC, @NonNull String[] per,
                                           @NonNull int[] Result) {
        if (RC == 1) {
            if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission Canceled", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void getWawancaraStart() {
        Toast.makeText(this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getWawancaraCompleted(ArrayList<ItemWawancara> itemWawancaraArrayList) {
        SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
        noForm.setText("Nomor Form : " + sp.getString("no_form", ""));
        metode.setText("Metode : " + sp.getString("metode", ""));

        for (ItemWawancara itemWawancara : itemWawancaraArrayList) {
            SharedPreferences.Editor spedit = sp.edit();
            spedit.putString("goal", itemWawancara.getGoal());
            spedit.apply();
            tujuan.setText("Tujuan : " + itemWawancara.getGoal());
            tvTitle.setText(itemWawancara.getFile());
        }

        adapter = new WawancaraAdapter(itemWawancaraArrayList, this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getWawancaraFailed(String message) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postWawancataStart() {
        Toast.makeText(this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postWawancaraCompleted(String message) {
        Toast.makeText(this, berhasil, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postWawancaraFailed(String message) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void kirim() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemWawancaraArrayList = adapter.getArrayList();
                SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
                String nama = sp.getString("name", "");
                wawancaraPresenter.postWawancara(idform, nama, itemWawancaraArrayList, jalurnya);
            }
        });
    }
}
package com.example.aseps.caat.Features.kuisioner;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aseps.caat.Adapter.KuisionerAdapter;
import com.example.aseps.caat.R;
import com.example.aseps.caat.Temp.kuisioner.ItemKuisioner;
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

import static com.example.aseps.caat.helper.UrlAkses.URL;
import static com.example.aseps.caat.helper.UrlAkses.download;

public class KuisionerActivity extends AppCompatActivity implements KuisionerView, KuisionerAdapter.OnEditTextChanged {
    EditText etMaksimal;
    String SourceFilname;
    int maksimal, position;
    KuisionerAdapter adapter;
    RecyclerView recyclerView;
    Button btnTampilkan, btnSimpan;
    KuisionerPresenter kuisionerPresenter;
    TextView noForm, metode, tujuan, btnDownload;
    String idprojek, idaudit, posisi, metod, idform, jalurnya;
    ArrayList<ItemKuisioner> itemKuisioners = new ArrayList<>();
    String tunggu = "Harap Menunggu", error = "Periksa Koneksi Anda", dwl = "Download Berhasil",
            berhasil = "Upload Data Berhasil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuisioner);

        noForm = findViewById(R.id.tvNoForm);
        metode = findViewById(R.id.tvNamaMetode);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnDownload = findViewById(R.id.download);
        etMaksimal = findViewById(R.id.etMaksimal);
        tujuan = findViewById(R.id.tvTujuanMetode);
        btnTampilkan = findViewById(R.id.btnTampilkan);
        recyclerView = findViewById(R.id.rv_kuisioner);
        kuisionerPresenter = new KuisionerPresenter(this);

        SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
        idprojek = sp.getString("idprojek", "");
        idaudit = sp.getString("idauditor", "");
        posisi = sp.getString("posisi", "");
        metod = sp.getString("metode", "");
        idform = sp.getString("id_form", "");

        btnTampilkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(etMaksimal.getText().toString()) != 0) {
                    kuisionerPresenter.getKuisioner(idprojek, idaudit, posisi, metod, idform);
                    maksimal = Integer.parseInt(etMaksimal.getText().toString());
                } else
                    Toast.makeText(KuisionerActivity.this, "Harap Isi Nilai Maksimal",
                            Toast.LENGTH_SHORT).show();
            }
        });

        setDownload();
    }

    @Override
    public void getKuisionerStart() {
        Toast.makeText(this, tunggu, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getKuisionerCompleted(ArrayList<ItemKuisioner> itemKuisionerArrayList) {
        SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
        noForm.setText("Nomor Form : " + sp.getString("no_form", ""));
        metode.setText("Metode : " + sp.getString("metode", ""));

        for (ItemKuisioner itemKuisioner : itemKuisionerArrayList) {
            SharedPreferences.Editor spedit = sp.edit();
            spedit.putString("goal", itemKuisioner.getGoal());
            spedit.apply();
            tujuan.setText("Tujuan : " + itemKuisioner.getGoal());
            itemKuisioner.setMaksimal(maksimal);
            Log.d("maksimal", String.valueOf(maksimal));
        }

        adapter = new KuisionerAdapter(itemKuisionerArrayList, KuisionerActivity.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void upload() {
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                Toast.makeText(this, "File gagal", Toast.LENGTH_LONG).show();
            } else {
                getFile();
            }
        } else {
            RequestRunTimePermission();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.REQUEST_CODE_PICK_FILE:
                if (resultCode == RESULT_OK) {
                    ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                    jalurnya = list.get(0).getPath();
                    Log.d("jalurnya", jalurnya);
                    ItemKuisioner itemKuisioner = itemKuisioners.get(position);
                    itemKuisioner.setJalurnya(jalurnya);
                    adapter.notifyDataSetChanged();
//                    tvTitle.setText(list.get(0).getName());
                }
                break;
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
    public void onRequestPermissionsResult(int RC, @NonNull String per[],
                                           @NonNull int[] Result) {
        switch (RC) {
            case 1:
                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Canceled", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void setDownload() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("haha", "haha");
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("method", "Questionnaire");

                String[] fileType = {
                        "application/pdf"
                };

                client.post(URL + download + idform, params, new BinaryHttpResponseHandler(fileType) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                        try {
                            Log.d("haha", "hahas");
                            SourceFilname = "api/form/download/" + idform;

                            String DestinationName = SourceFilname.substring(SourceFilname.
                                    lastIndexOf('/') + 1) + ".pdf";
                            File _f = new File(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DOWNLOADS), DestinationName);
                            FileOutputStream output = new FileOutputStream(_f);
                            output.write(binaryData);
                            output.close();
                            Toast.makeText(KuisionerActivity.this, dwl, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] binaryData,
                                          Throwable error) {
                        Log.d("errordi", String.valueOf(error));
                    }
                });
            }
        });
    }

    @Override
    public void getKuisionerFailed(String message) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postKuisionerStart() {

    }

    @Override
    public void postKuisionerCompleted(String message) {
        Toast.makeText(this, berhasil, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postKuisionerFailed(String message) {

    }

    @Override
    public void onTextChanged(int position, ArrayList<ItemKuisioner> itemKuisioners) {
        Log.d("posisinyaaa", String.valueOf(position));
        this.itemKuisioners = itemKuisioners;
        this.position = position;
        simpan(itemKuisioners);

        for (ItemKuisioner itemKuisioner : itemKuisioners) {
            if (itemKuisioner.getJalurnya().equals("klik")) {
                upload();
            } else simpan(itemKuisioners);

            Log.d("itemkuisiones", "haha");
            Log.d("itemkuisiones1", String.valueOf(itemKuisioner.getJawaban1()));
            Log.d("itemkuisiones2", String.valueOf(itemKuisioner.getJawaban2()));
            Log.d("itemkuisiones3", String.valueOf(itemKuisioner.getJawaban3()));
            Log.d("itemkuisiones4", String.valueOf(itemKuisioner.getJawaban4()));
            Log.d("itemkuisiones5", String.valueOf(itemKuisioner.getJawaban5()));

        }
    }

    private void simpan(final ArrayList<ItemKuisioner> itemKuisioners) {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    for (ItemKuisioner itemKuisioner : itemKuisioners) {

                        Log.d("itemkuisiones", "haha");
                        Log.d("itemkuisiones11", String.valueOf(itemKuisioner.getJawaban1()));
                        Log.d("itemkuisiones21", String.valueOf(itemKuisioner.getJawaban2()));
                        Log.d("itemkuisiones31", String.valueOf(itemKuisioner.getJawaban3()));
                        Log.d("itemkuisiones41", String.valueOf(itemKuisioner.getJawaban4()));
                        Log.d("itemkuisiones51", String.valueOf(itemKuisioner.getJawaban5()));

                        if (itemKuisioner.getJawaban1() + itemKuisioner.getJawaban2() +
                                itemKuisioner.getJawaban3() + itemKuisioner.getJawaban4() +
                                itemKuisioner.getJawaban5() > maksimal) {
                            Toast.makeText(KuisionerActivity.this, "Melebihi nilai maksimal", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("posberhasil", "berhasil");
                            SharedPreferences sp = getSharedPreferences("profil", MODE_PRIVATE);
                            String nama = sp.getString("name", "");
                            kuisionerPresenter.postKuisioner(idform, nama, itemKuisioners);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
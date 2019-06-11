package com.example.aseps.caat.Features.observasi;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.aseps.caat.Temp.observasi.ItemObservasi;
import com.example.aseps.caat.helper.UrlAkses;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import cz.msebera.android.httpclient.Header;

import static com.example.aseps.caat.helper.UrlAkses.URL;
import static com.example.aseps.caat.helper.UrlAkses.form;
import static com.example.aseps.caat.helper.UrlAkses.projek;
import static com.example.aseps.caat.helper.UrlAkses.submit;
import static com.example.aseps.caat.helper.UrlAkses.stackHolder;

class ObservasiPresenter {
    private ObservasiView observasiView;
    private int id_form;
    private String deadline, goal, question, answer, file;

    ObservasiPresenter(ObservasiView observasiView) {
        this.observasiView = observasiView;
    }

    void getObservasi(String idprojek, String idaudit, String stakholder, String metode, String idform) {
        Log.d("observasinya", "start");
        this.observasiView.getObservasiStart();
        LinkedHashMap<String, String> wawancara = new LinkedHashMap<>();
        wawancara.put("stakeholder", stakholder);
        wawancara.put("method", metode);
        wawancara.put("id_form", idform);
        RequestParams params = new RequestParams(wawancara);
        final ArrayList<ItemObservasi> itemObservasis = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        Log.d("observasinya", URL + projek + stackHolder + metode + form + idprojek + "/" + idaudit);
        client.post(URL + projek + stackHolder + UrlAkses.metode + form + idprojek + "/" + idaudit, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject object = new JSONObject(new String(responseBody));
                    Log.d("observasinya", String.valueOf(object));
                    JSONObject result1 = object.getJSONObject("result1");
                    JSONArray data = result1.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Log.d("wawan", "aray");
                        JSONObject jsonObject = data.getJSONObject(i);
                        goal = jsonObject.getString("goal");
                        Log.d("goal", goal);
                        deadline = jsonObject.getString("deadline");
                    }
                    JSONObject result2 = object.getJSONObject("result2");
                    JSONArray data1 = result2.getJSONArray("data");
                    for (int x = 0; x < data1.length(); x++) {
                        JSONObject jsonObject1 = data1.getJSONObject(x);
                        id_form = jsonObject1.getInt("id_qna");
                        if (jsonObject1.getString("question") != null)
                            question = jsonObject1.getString("question");
                        if (jsonObject1.getString("answer") != null)
                            answer = jsonObject1.getString("answer");
                        if (jsonObject1.getString("file") != null)
                            file = jsonObject1.getString("file");
                        ItemObservasi itemObservasi = new ItemObservasi(goal, deadline, id_form,
                                question, answer, file);
                        itemObservasis.add(itemObservasi);
                    }
                    observasiView.getObservasiCompleted(itemObservasis);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                observasiView.getObservasiFailed("Error: " + statusCode + ", Message: " + error.getMessage());
            }
        });
    }

    void postObservasi(String idform, String nama, ArrayList<ItemObservasi> itemObservasis, String jalur) {
        this.observasiView.postObservasiStart();
        for (ItemObservasi itemWawancara : itemObservasis) {
            RequestParams params = new RequestParams();
            params.put("method", "Interview");
            params.put("name", nama);
//            params.put("location", "jepang");
            params.put("question[]", itemWawancara.getQuestion());
            params.put("answer[]", itemWawancara.getAnswer());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            String date = sdf.format(new Date());
            params.put("time", date);
            params.put("id_qna[]", String.valueOf(itemWawancara.getId_pertanyaan()));

            try {
//                Log.d("jalur",jalur);
                if (!jalur.equals("kosong")) {
                    File file = new File(jalur);
                    params.put("file", file);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            AsyncHttpClient client = new AsyncHttpClient();
            client.post(URL + "/" + form + submit + idform, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("poswawan", "sukses");
                    try {
                        JSONObject object = new JSONObject(new String(responseBody));
                        String tru = object.getString("success");
                        Log.d("possukses", tru);
                        observasiView.postObservasiCompleted(tru);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    observasiView.postObservasiFailed(("Error: " + statusCode + ", Message: " + error.getMessage()));
                    Log.d("eror", String.valueOf(error));
                }
            });
        }
    }
}
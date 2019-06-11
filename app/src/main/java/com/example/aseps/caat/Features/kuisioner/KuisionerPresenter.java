package com.example.aseps.caat.Features.kuisioner;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.aseps.caat.Temp.kuisioner.ItemKuisioner;
import com.example.aseps.caat.helper.UrlAkses;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import cz.msebera.android.httpclient.Header;

import static com.example.aseps.caat.helper.UrlAkses.URL;
import static com.example.aseps.caat.helper.UrlAkses.form;
import static com.example.aseps.caat.helper.UrlAkses.projek;
import static com.example.aseps.caat.helper.UrlAkses.stackHolder;
import static com.example.aseps.caat.helper.UrlAkses.submit;

class KuisionerPresenter {
    private KuisionerView kuisionerView;
    private int id_form;
    private String deadline, goal, question, answer, file;

    KuisionerPresenter(KuisionerView kuisionerView) {
        this.kuisionerView = kuisionerView;
    }

    void getKuisioner(String idprojek, String idaudit, String stakholder, String metode, String idform) {
        Log.d("kuisnyaaaa", "start");
        LinkedHashMap<String, String> kuisioner = new LinkedHashMap<>();
        kuisioner.put("stakeholder", stakholder);
        kuisioner.put("method", metode);
        kuisioner.put("id_form", idform);
        RequestParams params = new RequestParams(kuisioner);
        final ArrayList<ItemKuisioner> itemKuisionerArrayList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(URL + projek + stackHolder + UrlAkses.metode + form + idprojek + "/" + idaudit, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Log.d("kuis", "mulai");
                    JSONObject object = new JSONObject(new String(responseBody));
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
                        ItemKuisioner itemKuisioner = new ItemKuisioner(goal, deadline, id_form, question, answer, file);
                        itemKuisionerArrayList.add(itemKuisioner);
                    }
                    kuisionerView.getKuisionerCompleted(itemKuisionerArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                kuisionerView.getKuisionerFailed("Error: " + statusCode + ", Message: " + error.getMessage());
            }
        });
    }

    void postKuisioner(String idform, String nama, ArrayList<ItemKuisioner> itemKuisioners) {
        Log.d("kuisssss", "berhasil");
        this.kuisionerView.postKuisionerStart();
        RequestParams params = new RequestParams();
        try {
            for (ItemKuisioner itemKuisioner : itemKuisioners) {
                Log.d("itemkuis1", String.valueOf(itemKuisioner.getJawaban1()));
                Log.d("itemkuis2", String.valueOf(itemKuisioner.getJawaban2()));
                Log.d("itemkuis3", String.valueOf(itemKuisioner.getJawaban3()));
                Log.d("itemkuis4", String.valueOf(itemKuisioner.getJawaban4()));
                Log.d("itemkuis5", String.valueOf(itemKuisioner.getJawaban5()));
                Log.d("itemkuis5", String.valueOf(itemKuisioner.getId_pertanyaan()));

                params.put("method", "Questionnaire");
                params.put("name", nama);
//            params.put("location", "jepang");
                params.put("question[]", itemKuisioner.getQuestion());
                params.put("answer", "");

                if (!String.valueOf(itemKuisioner.getJawaban1()).equals(""))
                    params.put("weight1[0]", String.valueOf(itemKuisioner.getJawaban1()));
                if (!String.valueOf(itemKuisioner.getJawaban2()).equals(""))
                    params.put("weight2[0]", String.valueOf(itemKuisioner.getJawaban2()));
                if (!String.valueOf(itemKuisioner.getJawaban3()).equals(""))
                    params.put("weight3[0]", String.valueOf(itemKuisioner.getJawaban3()));
                if (!String.valueOf(itemKuisioner.getJawaban4()).equals(""))
                    params.put("weight4[0]", String.valueOf(itemKuisioner.getJawaban4()));
                if (!String.valueOf(itemKuisioner.getJawaban5()).equals(""))
                    params.put("weight5[0]", String.valueOf(itemKuisioner.getJawaban5()));

                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                String date = sdf.format(new Date());
                params.put("time", date);
                params.put("id_qna[]", String.valueOf(itemKuisioner.getId_pertanyaan()));

                try {
                    if (!itemKuisioner.getJalurnya().equals("klik") && !itemKuisioner.getJalurnya().equals("")) {
                        Log.d("jalurterakhir", itemKuisioner.getJalurnya());
                        File file = new File(itemKuisioner.getJalurnya());
                        params.put("file", file);
                        Log.d("filenya", file.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("erorposkuis", e.toString());
                }

            }

            Log.d("urlnye", URL + "/" + form + submit + idform);
            AsyncHttpClient client = new AsyncHttpClient();
            client.post(URL + "/" + form + submit + idform, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("poswawan", "sukses");
                    try {
                        JSONObject object = new JSONObject(new String(responseBody));
                        Log.d("possukses", String.valueOf(object));
                        String tru = object.getString("success");
                        Log.d("possukses", tru);
                        kuisionerView.postKuisionerCompleted(tru);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    kuisionerView.postKuisionerFailed(("Error: " + statusCode + ", Message: " + error.getMessage()));
                    Log.d("eror", String.valueOf(error));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
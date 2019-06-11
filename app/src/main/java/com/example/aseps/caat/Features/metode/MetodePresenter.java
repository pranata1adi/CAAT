package com.example.aseps.caat.Features.metode;

import android.content.Context;
import android.util.Log;

import com.example.aseps.caat.Temp.metode.ItemMetode;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cz.msebera.android.httpclient.Header;

import static com.example.aseps.caat.helper.UrlAkses.*;


public class MetodePresenter {
    private MetodeView metodeView;
    private Context context;
    private int id_project, id_field_auditor, no_form, id_form;
    private String stakeholder, metod;

    public MetodePresenter(MetodeView metodeView) {
        this.metodeView = metodeView;
    }

    public void getMetode(final String idprojek, String id_auditor,String posisi) {
        this.metodeView.getMetodeStart();
        Log.d("sale", URL + projek + stackHolder + metode + idprojek + "/" + id_auditor);
        final ArrayList<ItemMetode> itemMetodeArrayList = new ArrayList<>();
        LinkedHashMap<String, String> karyawan = new LinkedHashMap<String, String>();
        karyawan.put("stakeholder", posisi);
        RequestParams params = new RequestParams(karyawan);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(URL + projek + stackHolder + metode + idprojek + "/" + id_auditor,params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Log.d("sale", "url bener");
                    JSONObject object = new JSONObject(new String(responseBody));
                    JSONObject result = object.getJSONObject("result");
                    Log.d("sale", "ampe result");
                    JSONArray data = result.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Log.d("sale", "aray");
                        JSONObject jsonObject = data.getJSONObject(i);
                        id_project = jsonObject.getInt("id_project");
                        Log.d("sale", String.valueOf(id_project));
                        id_field_auditor = jsonObject.getInt("id_field_auditor");
                        Log.d("sale", String.valueOf(id_field_auditor));
                        no_form = jsonObject.getInt("no_form");
                        Log.d("sale", String.valueOf(no_form));
                        id_form = jsonObject.getInt("id_form");
                        Log.d("sale", String.valueOf(id_form));
                        stakeholder = jsonObject.getString("stakeholder");
                        Log.d("sale", stakeholder);
                        metod = jsonObject.getString("metode");
                        Log.d("sale", metod);
                        ItemMetode itemMetode = new ItemMetode(id_project, id_field_auditor,
                                no_form, id_form, stakeholder, metod);
                        itemMetodeArrayList.add(itemMetode);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                metodeView.getMetodeCompleted(itemMetodeArrayList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                metodeView.getMetodeFailed("Error: " + statusCode + ", Message: " + error.getMessage());
            }
        });
    }
}
package com.example.aseps.caat.Features.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.aseps.caat.Temp.home.ItemHome;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.aseps.caat.helper.UrlAkses.*;


public class HomePresenter {
    private HomeView homeView;
    private Context context;
    private int id, id_project, no_form, id_form, id_field_auditor, status;
    private String logo, title, address, deadline;

    HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    public void home(final String idprojek) {
        this.homeView.getHomeStart();
        Log.d("mulai", "home start");
        final ArrayList<ItemHome> itemHomeArrayList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        Log.d("diprojek", URL + projek + idprojek);
        client.get(URL + projek + idprojek, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Log.d("mulai", "mulai");
                    JSONObject object = new JSONObject(new String(responseBody));
                    Log.d("mulai", "jalan");
                    JSONObject result = object.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Log.d("mulai", "aray");
                        JSONObject jsonObject = data.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        Log.d("homejos", String.valueOf(id));
                        id_project = jsonObject.getInt("id_project");
                        Log.d("homejos", String.valueOf(id_project));
                        no_form = jsonObject.getInt("no_form");
                        Log.d("homejos", String.valueOf(no_form));
                        id_form = jsonObject.getInt("id_form");
                        Log.d("homejos", String.valueOf(id_form));
                        id_field_auditor = jsonObject.getInt("id_field_auditor");
                        Log.d("homejos", String.valueOf(id_field_auditor));
                        logo = jsonObject.getString("logo");
                        Log.d("homejos", logo);
                        title = jsonObject.getString("title");
                        Log.d("homejos", title);
                        address = jsonObject.getString("address");
                        Log.d("homejos", address);
                        status = jsonObject.getInt("status");
                        Log.d("homejosstat", String.valueOf(status));
                        deadline = jsonObject.getString("deadline");
                        Log.d("homejos", deadline);
                        ItemHome itemHome = new ItemHome(id, id_project, no_form, id_form, id_field_auditor,
                                logo, title, address, status, deadline);
                        itemHomeArrayList.add(itemHome);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                homeView.getHomeCompleted(itemHomeArrayList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                homeView.getHomeFailed("Error: " + statusCode + ", Message: " + error.getMessage());
            }
        });
    }
}
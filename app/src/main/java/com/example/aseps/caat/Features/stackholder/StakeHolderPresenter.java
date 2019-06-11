package com.example.aseps.caat.Features.stackholder;

import android.content.Context;
import android.util.Log;

import com.example.aseps.caat.Temp.stackholder.ItemStakeholder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.aseps.caat.helper.UrlAkses.*;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class StakeHolderPresenter {
    private StakeHolderView stakeHolderView;
    private Context context;
    private int id_project;
    private String position;

    StakeHolderPresenter(StakeHolderView stakeHolderView) {
        this.stakeHolderView = stakeHolderView;
    }

    void getStackHolder(final String idprojek, String id_auditor) {
        Log.d("sule", "start");
        Log.d("sule", URL + projek + stackHolder + idprojek + "/" + id_auditor);
        this.stakeHolderView.getStakeHolderStart();
        final ArrayList<ItemStakeholder> itemStakeholderArrayList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL + projek + stackHolder + idprojek + "/" + id_auditor, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Log.d("sule", "mulai");
                    JSONObject object = new JSONObject(new String(responseBody));
                    JSONObject result = object.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Log.d("sule", "aray");
                        JSONObject jsonObject = data.getJSONObject(i);
                        id_project = jsonObject.getInt("id_project");
                        Log.d("position", String.valueOf(id_project));
                        position = jsonObject.getString("position");
                        Log.d("position", position);
                        ItemStakeholder itemStakeholder = new ItemStakeholder(id_project, position);
                        itemStakeholderArrayList.add(itemStakeholder);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stakeHolderView.getStakeHolderCompleted(itemStakeholderArrayList);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                stakeHolderView.getStakeHolderFailed("Error: " + statusCode + ", Message: " + error.getMessage());
            }
        });
    }
}
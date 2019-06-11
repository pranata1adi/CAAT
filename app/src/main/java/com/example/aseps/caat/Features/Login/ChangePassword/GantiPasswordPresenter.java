package com.example.aseps.caat.Features.Login.ChangePassword;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.LinkedHashMap;

import cz.msebera.android.httpclient.Header;

import static com.example.aseps.caat.helper.UrlAkses.*;

class GantiPasswordPresenter {
    private Context context;
    private GantiPasswordView gantiPasswordView;

    GantiPasswordPresenter(Context context, GantiPasswordView gantiPasswordView) {
        this.context = context;
        this.gantiPasswordView = gantiPasswordView;
    }

    void updatePassword(String password) {
        this.gantiPasswordView.getGantiPasswordStart();
        String idaudit;
        SharedPreferences sp2 = context.getSharedPreferences("profil", 0);
        idaudit = sp2.getString("idauditor", "");
        Log.d("idaudit", idaudit);
        Log.d("idaudit", URL + home + profil + update + "/" + idaudit);
        LinkedHashMap<String, String> wawancara = new LinkedHashMap<>();
        wawancara.put("new_password1", password);
        wawancara.put("new_password2", password);
        RequestParams params = new RequestParams(wawancara);
        AsyncHttpClient client = new AsyncHttpClient();

        client.post(URL + home + profil + update + "/" + idaudit, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(context, "sukses", Toast.LENGTH_SHORT).show();
                //kalo berhasil apa
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //mau di apain kalo error
            }
        });
    }
}
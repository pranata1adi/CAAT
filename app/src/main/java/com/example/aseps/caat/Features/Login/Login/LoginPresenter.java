package com.example.aseps.caat.Features.Login.Login;

import android.content.Context;
import android.util.Log;

import com.example.aseps.caat.Temp.login.ItemProfil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.example.aseps.caat.helper.UrlAkses.*;

import cz.msebera.android.httpclient.Header;

public class LoginPresenter {
    private LoginView loginView;
    private Context context;
    private int id_auditor_lapangan;
    private String name, foto, email, hp, password, address, city, registered;

    LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void Login(final String susername, String spassword) {
        this.loginView.getItemLoginStart();
        Log.d("sampe","start");
        LinkedHashMap<String, String> user = new LinkedHashMap<String, String>();
        final ArrayList<ItemProfil> itemProfilArrayList = new ArrayList<>();
        user.put("username", susername);
        user.put("password", spassword);
        RequestParams params = new RequestParams(user);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(URL + login, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("sampe","manggil");
                try {
                    Log.d("sampe","sini");
                    JSONObject object = new JSONObject(new String(responseBody));
                    Log.d("sampe","sinii");
                    JSONObject result = object.getJSONObject("result");
                    Log.d("sampe","siniii");
                    JSONObject data = result.getJSONObject("data");
                    Log.d("sampe","siniiii");
                    id_auditor_lapangan = data.getInt("id_field_auditor");
                    Log.d("idfild", String.valueOf(id_auditor_lapangan));
                    name = data.getString("name");
                    Log.d("namanyaaaaa", name);
                    foto = data.getString("foto");
                    email = data.getString("email");
                    hp = data.getString("hp");
                    password = data.getString("password");
                    address= data.getString("address");
                    city = data.getString("city");
                    registered = data.getString("registered");
                    ItemProfil itemProfil = new ItemProfil(id_auditor_lapangan,name, foto, email, hp,
                            password, address, city, registered);
                    itemProfilArrayList.add(itemProfil);
                    loginView.getItemLoginCompleted(itemProfilArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                loginView.getItemLoginFailed("Error: " + statusCode + ", Message: " + error.getMessage());
                Log.d("eror", String.valueOf(error));
            }
        });
    }
}
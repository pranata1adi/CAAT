package com.example.aseps.caat.Features.Login.Login;

import com.example.aseps.caat.Temp.login.ItemProfil;

import java.util.ArrayList;

public interface LoginView {
    void getItemLoginStart();
    void getItemLoginCompleted(ArrayList<ItemProfil> itemProfilArrayList);
    void getItemLoginFailed(String message);
}

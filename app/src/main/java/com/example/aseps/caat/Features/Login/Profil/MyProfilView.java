package com.example.aseps.caat.Features.Login.Profil;

import com.example.aseps.caat.Temp.login.ItemProfil;

import java.util.ArrayList;

public interface MyProfilView {
    void getProfilStart();
    void getProfilCompleted(ArrayList<ItemProfil> itemProfilArrayList);
    void getProfilFailed(String message);
}

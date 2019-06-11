package com.example.aseps.caat.Features.kuisioner;

import com.example.aseps.caat.Temp.kuisioner.ItemKuisioner;

import java.util.ArrayList;

public interface KuisionerView {
    void getKuisionerStart();
    void getKuisionerCompleted(ArrayList<ItemKuisioner> itemKuisionerArrayList);
    void getKuisionerFailed(String message);
    void postKuisionerStart();
    void postKuisionerCompleted(String message);
    void postKuisionerFailed(String message);

}

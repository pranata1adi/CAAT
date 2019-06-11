package com.example.aseps.caat.Features.wawancara;

import com.example.aseps.caat.Temp.wawancara.ItemWawancara;

import java.util.ArrayList;

public interface WawancaraView {
    void getWawancaraStart();
    void getWawancaraCompleted(ArrayList<ItemWawancara> itemWawancaraArrayList);
    void getWawancaraFailed(String message);
    void postWawancataStart();
    void postWawancaraCompleted(String message);
    void postWawancaraFailed(String message);
}
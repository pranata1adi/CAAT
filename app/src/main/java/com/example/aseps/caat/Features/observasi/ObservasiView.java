package com.example.aseps.caat.Features.observasi;


import com.example.aseps.caat.Temp.observasi.ItemObservasi;

import java.util.ArrayList;

public interface ObservasiView {
    void getObservasiStart();
    void getObservasiCompleted(ArrayList<ItemObservasi> itemObservasiArrayList);
    void getObservasiFailed(String message);
    void postObservasiStart();
    void postObservasiCompleted(String message);
    void postObservasiFailed(String message);
}
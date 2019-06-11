package com.example.aseps.caat.Features.metode;

import com.example.aseps.caat.Temp.metode.ItemMetode;

import java.util.ArrayList;

public interface MetodeView {
    void getMetodeStart();
    void getMetodeCompleted(ArrayList<ItemMetode> itemMetodeArrayList);
    void getMetodeFailed(String message);

}

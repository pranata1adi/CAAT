package com.example.aseps.caat.Features.home;

import com.example.aseps.caat.Temp.home.ItemHome;

import java.util.ArrayList;

public interface HomeView {
    void getHomeStart();
    void getHomeCompleted(ArrayList<ItemHome> itemHomeArrayList);
    void getHomeFailed(String message);
}

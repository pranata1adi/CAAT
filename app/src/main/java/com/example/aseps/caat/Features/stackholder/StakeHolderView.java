package com.example.aseps.caat.Features.stackholder;

import com.example.aseps.caat.Temp.stackholder.ItemStakeholder;

import java.util.ArrayList;

public interface StakeHolderView {
    void getStakeHolderStart();
    void getStakeHolderCompleted(ArrayList<ItemStakeholder> itemStakeholderArrayList);
    void getStakeHolderFailed(String message);
}

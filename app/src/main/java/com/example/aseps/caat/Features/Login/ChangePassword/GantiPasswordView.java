package com.example.aseps.caat.Features.Login.ChangePassword;

import com.example.aseps.caat.Temp.login.ItemGantiPassword;

import java.util.ArrayList;

public interface GantiPasswordView {
    void getGantiPasswordStart();
    void getGantiPasswordCompleted(String message);
    void getGantiPasswordFailed(String message);
}
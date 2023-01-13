package com.example.a0683_sharedpreferences.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static PreferencesHelper INSTANCE;
    private SharedPreferences sharedPreferences;

    private PreferencesHelper(Context context) {
        sharedPreferences = context
                .getApplicationContext()
                .getSharedPreferences("com.example.myapplication", Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new PreferencesHelper(context);
        }
        return INSTANCE;
    }

    public SharedPreferences preferences() {
        return sharedPreferences;
    }

    //untuk menyimpan data yang diinputkan user
    //isLogin adalah key-nya
    public void setLogin(boolean isLogin){
        sharedPreferences.edit().putBoolean("isLogin", isLogin).apply();
    }

    //asalkan dia login walaupun tanpa isi tetep dianggep true
    public Boolean isLogin() {
        return sharedPreferences.getBoolean("isLogin", false);
    }

    //untuk menyimpan data yang telah diisi user
    public void setNama(String nama){
        sharedPreferences.edit().putString("nama",nama).apply();
    }

    //function untuk mendapatkan data yang telah diisi user
    public String getNama() {
        return sharedPreferences.getString("nama", "");
    }

    //function untuk menyimpan data username yang diisi user
    public void setUsername(String isNama) {
        sharedPreferences.edit().putString("Username", isNama).apply();
    }

    //get = untuk mendapatkan data
    public String getUsername() {
        return sharedPreferences.getString("Username", "");
    }

    //set = untuk menyimpan data
    public void setPassword(String isPassword) {
        sharedPreferences.edit().putString("Password", isPassword).apply();
    }

    //get = untuk mendapatkan data
    public String getPassword() {
        return sharedPreferences.getString("Password", "");
    }
}


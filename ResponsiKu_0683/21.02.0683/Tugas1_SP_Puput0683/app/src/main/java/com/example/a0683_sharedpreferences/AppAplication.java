package com.example.a0683_sharedpreferences;

import android.app.Application;

import androidx.room.Room;

import com.example.a0683_sharedpreferences.room.AppDatabase;

public class AppAplication extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
    }
}

package com.example.myapplication;

import android.app.Application;

import androidx.room.Room;

import com.example.myapplication.room.AppDatabase;
import com.example.myapplication.room.DataBaseMigrations;

public class AppApplication extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"mahasiswa")
                .addMigrations(
                        DataBaseMigrations.MIGRATION_1_TO_3
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

    }

}

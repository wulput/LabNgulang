package com.example.myapplication.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Mahasiswa.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MahasiswaDao userDao();
}

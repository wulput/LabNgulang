package com.example.a0683_sharedpreferences.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MahasiswaDao userDao();
}

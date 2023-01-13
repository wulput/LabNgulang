package com.example.responsiku_0683.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pengguna.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PenggunaDao userDao();
    public static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "mi-database.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;

    }
}

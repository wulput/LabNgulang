package com.example.myapplication.room;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DataBaseMigrations {
    public static final Migration MIGRATION_1_TO_3 = new Migration(1, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE mahasiswa ADD COLUMN gambar TEXT DEFAULT ''");
        }
    };
}

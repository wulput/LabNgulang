package com.example.responsiku_0683.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PenggunaDao {

    @Query("SELECT * FROM pengguna WHERE email LIKE :email and password LIKE :password")
    Pengguna userDao(String email, String password);

    @Insert
    void insertAll(Pengguna pengguna);

    @Delete
    public void deleteUsers(Pengguna users);

    @Update
    public void update(Pengguna pengguna);

    @Delete
    public void deleteAll(Pengguna user1, Pengguna user2);

    @Query("SELECT * FROM pengguna")
    List<Pengguna> getAllPengguna();
}

package com.example.responsiku_0683.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "pengguna")
public class Pengguna implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "confirm_password")
    String confirm_password;

    public Pengguna(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswd() {
        return password;
    }
    public void setPasswd(String password) {
        this.password = password;
    }
    public String getConfPasswd() {
        return confirm_password;
    }
    public void setConfPasswd(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pengguna{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

package com.example.a0683_sharedpreferences;

import static com.example.a0683_sharedpreferences.AppAplication.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.a0683_sharedpreferences.R;
import com.example.a0683_sharedpreferences.activity.RecyclerViewUserAdapter;
import com.example.a0683_sharedpreferences.room.AppDatabase;
import com.example.a0683_sharedpreferences.room.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewUserAdapter recyclerAdapter;
    List<Mahasiswa> listMahasiswa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recycleView);
        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
        listMahasiswa = db.userDao().getAll();

        //just checking data from db
        for (int i = 0; i <listMahasiswa.size(); i++) {
            Log.e("Aplikasi", listMahasiswa.get(i).getAlamat()+i);
            Log.e("Aplikasi", listMahasiswa.get(i).getKejuruan()+i);
            Log.e("Aplikasi", listMahasiswa.get(i).getNama()+i);
            Log.e("Aplikasi", listMahasiswa.get(i).getNim()+i);
        }
        Log.e("cek list", ""+listMahasiswa.size());
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerAdapter = new RecyclerViewUserAdapter(this,listMahasiswa);
    }

    private void setAdapter() {
        recyclerView.setAdapter(recyclerAdapter);
    }

}
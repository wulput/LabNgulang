package com.example.myapplication.activity.mahasiswa;

import static com.example.myapplication.AppApplication.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.room.AppDatabase;
import com.example.myapplication.room.DataBaseMigrations;
import com.example.myapplication.room.Mahasiswa;
import com.example.myapplication.util.OnClickAdapterItem;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerviewUserAdapter recycleAdapter;
    List<Mahasiswa> listMahasiswas = new ArrayList<>();

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
                AppDatabase.class,"mahasiswa")
                .addMigrations(
                        DataBaseMigrations.MIGRATION_1_TO_3
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        listMahasiswas = db.userDao().getAll();

        //just checking data from db
        for (int i = 0 ;i <listMahasiswas.size();i++){
            Log.e("Aplikasi",listMahasiswas.get(i).getAlamat()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getKejuruan()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNama()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNim()+i);
        }
        Log.e("cek list",""+listMahasiswas.size());
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recycleAdapter =new RecyclerviewUserAdapter(this,listMahasiswas);
        recycleAdapter.OnClickAdapterItem(new OnClickAdapterItem() {
            @Override
            public void clickItem(int id, int position) {
                new AlertDialog.Builder(UserActivity.this)
                        .setTitle("Delete Data")
                        .setMessage("Are you sure you want to delete this data?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                listMahasiswas.remove(position);
                                recycleAdapter.notifyItemChanged(position);
                                db.userDao().deleteUsers(db.userDao().findById(id));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    private void setAdapter() {
        recyclerView.setAdapter(recycleAdapter);
    }
}
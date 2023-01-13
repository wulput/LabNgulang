package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.activity.mahasiswa.AddUserActivity;
import com.example.myapplication.activity.mahasiswa.UserActivity;
import com.example.myapplication.util.PreferencesHelper;

public class HomeActivity extends AppCompatActivity {

    private Button btnActivity, btnFragment, btnRoomDatabase, btnAddUser;
    private EditText etNama;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        btnActivity = findViewById(R.id.btnActivity);
        btnFragment = findViewById(R.id.btnFragment);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnRoomDatabase = findViewById(R.id.btnRoomDatabase);
        etNama = findViewById(R.id.etNama);

        btnActivity.setOnClickListener(v -> {
            preferencesHelper.setLogin(true);
            preferencesHelper.setNama(etNama.getText().toString());
            Intent homeIntent = new Intent(HomeActivity.this, FirstActivity.class);
//            homeIntent.putExtra("nama", etNama.getText().toString());
            startActivity(homeIntent);
        });

        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(HomeActivity.this, SecondActivity.class);
                startActivity(secondIntent);
            }
        });

        btnRoomDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(secondIntent);
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(HomeActivity.this, AddUserActivity.class);
                startActivity(secondIntent);
            }
        });

    }
}
package com.example.a0683_sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a0683_sharedpreferences.activity.RecyclerViewUserAdapter;

public class SecondActivity extends AppCompatActivity {

    public Button btTambah;
    public Button btTampil;
    public Button btEdit;
    public Button btTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btTambah = findViewById(R.id.btTambah);
        btTampil = findViewById(R.id.btTampil);
        btEdit = findViewById(R.id.btEdit);
        btTimer = findViewById(R.id.btTimer);

        btTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambahIntent = new Intent(SecondActivity.this, AddUserActivity.class);
                startActivity(tambahIntent);
            }
        });

        btTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tampilIntent = new Intent(SecondActivity.this, UserActivity.class);
                startActivity(tampilIntent);
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(SecondActivity.this, RecyclerViewUserAdapter.class);
                startActivity(editIntent);
            }
        });

        btTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timerIntent = new Intent(SecondActivity.this, TimerActivity.class);
                startActivity(timerIntent);
            }
        });

    }
}

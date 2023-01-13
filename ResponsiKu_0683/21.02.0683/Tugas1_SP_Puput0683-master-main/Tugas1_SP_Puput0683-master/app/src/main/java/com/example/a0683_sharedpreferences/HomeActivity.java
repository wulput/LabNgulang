package com.example.a0683_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.a0683_sharedpreferences.util.PreferencesHelper;

public class HomeActivity extends AppCompatActivity {

    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        TextView txt1_home = findViewById(R.id.txt1_home);

        //untuk mendapatkan teks yang telah diinputkan
        txt1_home.setText(preferencesHelper.getNama());
    }
}
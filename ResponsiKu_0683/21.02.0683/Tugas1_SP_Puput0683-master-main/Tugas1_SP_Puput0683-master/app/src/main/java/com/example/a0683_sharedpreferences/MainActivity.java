package com.example.a0683_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.a0683_sharedpreferences.util.PreferencesHelper;

public class MainActivity extends AppCompatActivity {

    private int waktu_loading=4000;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //kondisinya
                //jika dia isLogin, nilai yang diberikan adalah true
                //logic dari if adalah nilainya true
//                if(preferencesHelper.isLogin()) {
//                    Intent login = new Intent(MainActivity.this, HomeActivity.class);
//                    startActivity(login);
//                }else{
//                    Intent home = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(home);
//                }

                Intent second = new Intent(MainActivity.this, SecondActivity.class);

            }
        }, waktu_loading);
    }
}
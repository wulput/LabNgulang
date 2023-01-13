package com.example.aplikasiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplikasiactivity.util.PreferencesHelper;

public class HomeActivity extends AppCompatActivity {

    private Button btnActivity, btnFragment;
    private EditText etNama;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        btnActivity = findViewById(R.id.btnActivity);
        btnFragment = findViewById(R.id.btnFragment);
        etNama = findViewById(R.id.etNama);

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesHelper.setLogin(true);
                preferencesHelper.setNama(etNama.getText().toString());
                Intent homeIntent = new Intent(HomeActivity.this, FirstActivity.class);
  //              homeIntent.putExtra("nama", etNama.getText().toString());
                startActivity(homeIntent);
            }
        });

        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    Intent homeIntent = new Intent(HomeActivity.this, FirstActivity.class);
                    homeIntent.putExtra("nama", etNama.getText().toString());
                    startActivity(homeIntent);
            }
        });
    }
}
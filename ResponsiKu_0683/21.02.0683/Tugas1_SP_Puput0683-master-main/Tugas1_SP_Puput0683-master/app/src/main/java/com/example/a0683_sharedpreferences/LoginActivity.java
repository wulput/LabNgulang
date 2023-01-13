package com.example.a0683_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a0683_sharedpreferences.util.PreferencesHelper;

public class LoginActivity extends AppCompatActivity {

    //diinisialisasi supaya bisa diakses di kelas ini
    private EditText ent_name;
    private Button btn_login;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        btn_login = findViewById(R.id.btn_login);
        ent_name = findViewById(R.id.ent_name);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //untuk menyimpan data apakah user sudah login atau belum
                preferencesHelper.setLogin(true);
                Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                preferencesHelper.setNama(ent_name.getText().toString());
                startActivity(loginIntent);
            }
        });
    }
}
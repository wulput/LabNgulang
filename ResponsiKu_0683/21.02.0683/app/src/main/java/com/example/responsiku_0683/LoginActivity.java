package com.example.responsiku_0683;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.responsiku_0683.room.AppDatabase;
import com.example.responsiku_0683.room.Pengguna;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etEmail2;
    private EditText etPasswd2;
    private TextView tvHere_forgot;
    private TextView tvHere_regis;

    AppDatabase db;
    Pengguna pengguna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        etEmail2 = findViewById(R.id.etEmail2);
        etPasswd2 = findViewById(R.id.etPasswd2);
        tvHere_forgot = findViewById(R.id.tvHere_forgot);
        tvHere_regis = findViewById(R.id.tvHere_regis);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail2.getText().toString();
                String password = etPasswd2.getText().toString();

                // cek email dan password tidak boleh kosong
                if (!email.isEmpty() && !password.isEmpty()) {
                    try {
                        db = AppDatabase.getDbInstance(getApplicationContext());
                        pengguna = db.userDao().userDao(email, password);

                        // just debug
                        List<Pengguna> p= db.userDao().getAllPengguna();
                        if(p != null)

                            if (pengguna != null) {
                                Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                                home.putExtra("Pengguna", pengguna);
                                startActivity(home);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Email atau Password tidak sesuai", Toast.LENGTH_SHORT).show();
                            }
                    } catch (RuntimeException ex) {
                        Toast.makeText(getApplicationContext(), "Something Happend", Toast.LENGTH_SHORT).show();
                    } finally {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan data dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvHere_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RecoveryActivity.class));
            }
        });

        tvHere_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
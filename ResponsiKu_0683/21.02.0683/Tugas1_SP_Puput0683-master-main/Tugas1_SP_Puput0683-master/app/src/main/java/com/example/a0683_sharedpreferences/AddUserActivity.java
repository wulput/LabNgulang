package com.example.a0683_sharedpreferences;

import static com.example.a0683_sharedpreferences.AppAplication.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a0683_sharedpreferences.room.Mahasiswa;

public class AddUserActivity extends AppCompatActivity {

    private Button insertData;
    private EditText etAlamat;
    private EditText etKejuruan;
    private EditText etNama;
    private EditText etNim;
    Mahasiswa mahasiswa;
    private View etId;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        insertData = findViewById(R.id.btInsert);
        etAlamat = findViewById(R.id.etAlamat);
        etKejuruan = findViewById(R.id.etKejuruan);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        //etId = findViewById(R.id.etId);

//        (getIntent() != null) {
//            Intent intent = getIntent();
//            String nama = intent.getStringExtra("nama");
//            String alamat = intent.getStringExtra("alamat");
//            String nim = intent.getStringExtra("nim");
//            String kejuruan = intent.getStringExtra("kejuruan");
//            String id = intent.getStringExtra("id");
//            status = intent.getStringExtra("status");
//
//            etAlamat.setText(alamat);
//            etKejuruan.setText(kejuruan);
//            etNama.setText(nama);
//            etNim.setText(nim);
//            etAlamat.setText(id);
//            insertData.setText((status != null) ? status : "Insert");
//
//        }

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            if (status != null){
//
//
//            }
              if
              (!etAlamat.getText().toString().isEmpty()&&!etKejuruan.getText().toString().isEmpty()&&
              !etNama.getText().toString().isEmpty()&&!etNim.getText().toString().isEmpty()) {

                  mahasiswa = new Mahasiswa();
                  mahasiswa.setAlamat(etAlamat.getText().toString());
                  mahasiswa.setKejuruan(etKejuruan.getText().toString());
                  mahasiswa.setNama(etNama.getText().toString());
                  mahasiswa.setNim(etNim.getText().toString());

                  //insert data in database
                  db.userDao().insertAll(mahasiswa);
                  startActivity(new Intent(AddUserActivity.this, UserActivity.class));
              } else {
                  Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar",
                          Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}
package com.example.myapplication.activity.mahasiswa;

import static com.example.myapplication.AppApplication.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.R;
import com.example.myapplication.dialog.CustomDialogImageOptions;
import com.example.myapplication.room.Mahasiswa;
import com.example.myapplication.util.OnClickAdapterItem;
import com.example.myapplication.util.DialogImageOptionsListener;
import java.io.File;
import java.io.IOException;

public class AddUserActivity extends AppCompatActivity implements DialogImageOptionsListener {

    public final static String TAG_DATA_MAHASISWA = "data_mahasiswa";
    public final static int REQUEST_CAMERA = 101;
    public final static int REQUEST_GALLERY = 202;
    public final static int PICK_CAMERA = 1001;
    public final static int PICK_GALLERY = 2002;
    private Button insertData;
    private EditText etAlamat;
    private EditText etKejuruan;
    private EditText etNama;
    private EditText etNim;
    private String status;
    private int id;
    private ImageView imageView;
    private ImageView addImage;
    private RequestOptions requestOptions;
    private File fileImage;
    Mahasiswa mahasiswa;
    private NotificationManager mNotifyManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        insertData = findViewById(R.id.btInsert);
        etAlamat = findViewById(R.id.etAlamat);
        etKejuruan = findViewById(R.id.etKejuruan);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        imageView = findViewById(R.id.image);
        addImage = findViewById(R.id.add_image);

        if (getIntent() != null) {
            int id = getIntent().getIntExtra(TAG_DATA_MAHASISWA, 0);
            mahasiswa = db.userDao().findById(id);
        }
        if (mahasiswa == null) {
            mahasiswa = new Mahasiswa();
        }

//        if (getIntent() != null) {
//            id = getIntent().getIntExtra("id", 0);
//            mahasiswa = db.userDao().findById(id);
//            insertData.setText("Update");
//        }
//
//        if (mahasiswa == null) {
//            mahasiswa = new Mahasiswa();
//        }


        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etAlamat.getText().toString().isEmpty()&&!etKejuruan.getText().toString().isEmpty()&&
                        !etNama.getText().toString().isEmpty()&&!etNim.getText().toString().isEmpty()){

                    mahasiswa.setAlamat(etAlamat.getText().toString());
                    mahasiswa.setKejuruan(etKejuruan.getText().toString());
                    mahasiswa.setNama(etNama.getText().toString());
                    mahasiswa.setNim(etNim.getText().toString());

                    if (mahasiswa.getId() > 0) {
                        mahasiswa.setId(mahasiswa.getId());
                        db.userDao().update(mahasiswa);
                    } else {
                        db.userDao().insertAll(mahasiswa);
                    }

                    startActivity(new Intent(AddUserActivity.this,UserActivity.class));
                    sendNotification();
                }else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }


            }
        });

        requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(false)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_account_circle_24);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomDialogImageOptions(AddUserActivity.this,
                        AddUserActivity.this)
                        .show();
            }
        });


    }

    public void createNotification(String aMessage, Context context)
    {
       final int NOTIFY_ID = 0; //  ID untuk notifikasi
        String id = "TestChannel"; //default_channel_id
        String title = "MyApp";
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if(mNotifyManager == null) {
            mNotifyManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = mNotifyManager
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mahasiswa.getId() > 0) {
            etNama.setText(mahasiswa.getNama());
            etNim.setText(mahasiswa.getNim());
            etKejuruan.setText(mahasiswa.getKejuruan());
            etAlamat.setText(mahasiswa.getAlamat());
            loadImage(new File(mahasiswa.getGambar()));
            insertData.setText("Update");
        }
    }

    // Picture
    @Override
    public void onCameraClick() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    @Override
    public void onGalleryClick() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            openCamera();
        } else if (requestCode == REQUEST_GALLERY) {
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String path;
            if (requestCode == PICK_GALLERY) {
                path = getRealPathFromUri(data.getData());
            } else {
                path = fileImage.getAbsolutePath();
            }

            mahasiswa.setGambar(path);
            loadImage(new File(path));
        }
    }

    //function / method untuk open kamera
    private void openCamera() {
        try {
            fileImage = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg",
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            Uri imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", fileImage);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, PICK_CAMERA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //function / methode untuk open galeri
    @SuppressLint("IntentReset")
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_GALLERY);
    }

    private void loadImage(File image) {
        if (image == null) return;

        //set imagenya menggunakan Glide
        Glide.with(this)
                .asBitmap()
                .apply(requestOptions)
                .load(image)
                .into(imageView);
    }

    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null
                , MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

}
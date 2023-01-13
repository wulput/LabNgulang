package com.example.a0683_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a0683_sharedpreferences.service.TimeServices;

public class TimerActivity extends AppCompatActivity {

     private TextView mTextTimer;
     private Button btStart, btStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        mTextTimer = findViewById(R.id.tv_timer);
        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              registerReceiver(broadcastReceiver, new IntentFilter(TimeServices.BROADCAST_ACTION));
              Intent serviceIntent = new Intent(getApplicationContext(), TimeServices.class);
              startService(serviceIntent);
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), TimeServices.class);
                stopService(serviceIntent);
                mTextTimer.setText(0 + ":" + String.format("%02d", 0));
            }
        });
    }

    //method untuk mengambil data dari TimeServices
    private void updateUi(Intent intent) {
        int mins = intent.getIntExtra("mins", 0);
        int secs = intent.getIntExtra("secs", 0);

        mTextTimer.setText(mins + ":" + String.format("%02d", secs));
    }

    //untuk mengecek services nya itu running atau tidak
    private boolean isMyServiceRunning(Class<TimeServices> timeServices) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (timeServices.getName().equals(serviceInfo.service.getClassName())) {
                return true;
            }
            return false;
        }
        return false;
    }

    //dari onReceive akan mengirimkan ke updateUI nya
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUi(intent);
        }
    };
}
package com.example.a0683_sharedpreferences.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import java.security.Provider;

public class TimeServices extends Service {

    public static final String BROADCAST_ACTION = "com.example.a0683_sharedpreferences.service";
    private long timeInMilliseconds = 0L;
    private Intent intent;
    private Handler handler = new Handler();
    private long initial_time;

    //function yang akan nge-Count
    private Runnable sendUpdatesToUI = new Runnable() {
        @Override
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 1000);
        }
    };

    //method untuk ngirim data ke intent
    private void DisplayLoggingInfo() {
        timeInMilliseconds = SystemClock.uptimeMillis() - initial_time;

        //
        int timer = (int) (timeInMilliseconds / 1000);
        int mins = timer / 60;
        int secs = timer % 60;

        //dikirim dengan intent.putExtra
        intent.putExtra("mins", mins);
        intent.putExtra("secs", secs);

        sendBroadcast(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initial_time = SystemClock.uptimeMillis();
        intent = new Intent(BROADCAST_ACTION);
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startService(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(sendUpdatesToUI); //hanya menghapus
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

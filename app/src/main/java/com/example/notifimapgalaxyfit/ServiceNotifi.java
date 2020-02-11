package com.example.notifimapgalaxyfit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ServiceNotifi extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");


        NotificationUtil.showNotification(this, "AAAAAAAAAaaaa", "bbbbbbbb");


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}

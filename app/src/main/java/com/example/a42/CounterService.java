package com.example.a42;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

public class CounterService extends Service {

    private static String TAG = "SVC";
    private int tick = 0;
    private Handler handler;
    private Runnable tickRunnable;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        handler = new Handler(Looper.getMainLooper());
        tickRunnable = new Runnable() {
            @Override
            public void run() {
                tick++;
                Log.d(TAG, "Тик " + tick);
                handler.postDelayed(this, 1000);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        handler.removeCallbacks(tickRunnable);
        tick = 0;
        handler.post(tickRunnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        handler.removeCallbacks(tickRunnable);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

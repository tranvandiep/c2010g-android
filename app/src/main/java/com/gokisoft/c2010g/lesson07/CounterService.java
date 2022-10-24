package com.gokisoft.c2010g.lesson07;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service {
    public static final String ACTION_COUNTER = "ACTION_COUNTER";

    Handler mHandler = null;
    int count = 0;

    public CounterService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(CounterService.class.getName(), "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(CounterService.class.getName(), "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler();
        startCounting();

        Log.d(CounterService.class.getName(), "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    void startCounting() {
        if(mHandler == null) return;

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent();
                i.setAction(ACTION_COUNTER);
                i.putExtra("count", ++count);

                sendBroadcast(i);

                startCounting();
            }
        }, 1000);
    }

    @Override
    public void onDestroy() {
        Log.d(CounterService.class.getName(), "onDestroy");
        mHandler = null;
        super.onDestroy();
    }
}
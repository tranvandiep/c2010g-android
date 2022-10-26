package com.gokisoft.c2010g.lesson07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gokisoft.c2010g.R;

public class CounterActivity extends AppCompatActivity implements View.OnClickListener{
    Button startBtn, stopBtn, threadStartBtn, threadStopBtn, asyncStartBtn, asyncStopBtn;
    TextView counterTxt;
    Intent intentService = null;
    CounterThread currentThread = null;

    CounterAsyncTask asyncTask = null;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case CounterService.ACTION_COUNTER:
                    int count = intent.getIntExtra("count", 0);

                    counterTxt.setText(count + "");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        startBtn = findViewById(R.id.ac_start_btn);
        stopBtn = findViewById(R.id.ac_stop_btn);
        counterTxt = findViewById(R.id.ac_counter_text);

        threadStartBtn = findViewById(R.id.ac_thread_start_btn);
        threadStopBtn = findViewById(R.id.ac_thread_stop_btn);

        asyncStartBtn = findViewById(R.id.ac_async_start_btn);
        asyncStopBtn = findViewById(R.id.ac_async_stop_btn);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        threadStartBtn.setOnClickListener(this);
        threadStopBtn.setOnClickListener(this);
        asyncStartBtn.setOnClickListener(this);
        asyncStopBtn.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(CounterService.ACTION_COUNTER);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onClick(View view) {
        if(startBtn.equals(view)) {
            startBtn.setVisibility(View.GONE);
            stopBtn.setVisibility(View.VISIBLE);

            if(intentService == null) {
                intentService = new Intent(CounterActivity.this, CounterService.class);
                startService(intentService);
            }
        } else if(stopBtn.equals(view)) {
            startBtn.setVisibility(View.VISIBLE);
            stopBtn.setVisibility(View.GONE);

            if(intentService != null) {
                stopService(intentService);
                intentService = null;
            }
        } else if(threadStartBtn.equals(view)) {
            threadStopBtn.setVisibility(View.VISIBLE);
            threadStartBtn.setVisibility(View.GONE);

            if(currentThread == null) {
                currentThread = new CounterThread(this);
                currentThread.setTextView(counterTxt);
                currentThread.start();
            }
        } else if(threadStopBtn.equals(view)) {
            threadStartBtn.setVisibility(View.VISIBLE);
            threadStopBtn.setVisibility(View.GONE);

            if(currentThread != null) {
                if(currentThread.isAlive()) {
                    currentThread.setRunning(false);
                }
                currentThread = null;
            }
        } else if(asyncStartBtn.equals(view)) {
            asyncStopBtn.setVisibility(View.VISIBLE);
            asyncStartBtn.setVisibility(View.GONE);

            if(asyncTask == null) {
                asyncTask = new CounterAsyncTask(this, counterTxt);
                asyncTask.execute();
            }
        } else if(asyncStopBtn.equals(view)) {
            asyncStartBtn.setVisibility(View.VISIBLE);
            asyncStopBtn.setVisibility(View.GONE);

            if(asyncTask != null) {
                asyncTask.setRunning(false);
                asyncTask = null;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();

        if(mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }
}
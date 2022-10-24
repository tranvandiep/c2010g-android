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
    Button startBtn, stopBtn;
    TextView counterTxt;
    Intent intentService = null;

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

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

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
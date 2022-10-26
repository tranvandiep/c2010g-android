package com.gokisoft.c2010g.lesson07;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class CounterThread extends Thread{
    Activity mActivity;
    TextView mTextView;
    int i;

    boolean running = true;

    public CounterThread(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setTextView(TextView textView) {
        this.mTextView = textView;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        for (i=1;i<=10 && isRunning();i++) {
//            Intent intent = new Intent();
//            intent.setAction(CounterService.ACTION_COUNTER);
//            intent.putExtra("count", i);
//            mActivity.sendBroadcast(intent);
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText(i + "");
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

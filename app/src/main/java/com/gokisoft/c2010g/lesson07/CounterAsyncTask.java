package com.gokisoft.c2010g.lesson07;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

public class CounterAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity activity;
    TextView textView;
    boolean running = true;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public CounterAsyncTask(Activity activity, TextView textView) {
        this.activity = activity;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i=1;i<=10 && isRunning();i++) {
//            Intent intent = new Intent();
//            intent.setAction(CounterService.ACTION_COUNTER);
//            intent.putExtra("count", i);
//            activity.sendBroadcast(intent);
            publishProgress(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int count = values[0];

        textView.setText(count + "");
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }
}

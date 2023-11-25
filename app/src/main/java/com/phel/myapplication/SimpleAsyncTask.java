package com.phel.myapplication;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private TextView myTextView;
    private ProgressBar progressBar;

    public SimpleAsyncTask(TextView tv, ProgressBar pb) {
        myTextView = tv;
        progressBar = pb;
    }
    protected void onPreExecute() {
        // Show the progress bar before starting the task
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);

        int totalSleepTime = n * 200;
        int interval = totalSleepTime / 100;

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Update the progress in the UI thread based on the percentage of completion
            publishProgress(i);
        }

        try {
            Thread.sleep(totalSleepTime - (interval * 100)); // Sleep for the remaining time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + totalSleepTime + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // Update the progress bar with the percentage
        progressBar.setProgress(values[0]);
    }

    protected void onPostExecute(String result) {
        // Hide the progress bar after the task is completed
        progressBar.setVisibility(View.INVISIBLE);
        myTextView.setText(result);
    }
}

package com.phel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView myTextView = null;
    private static final String TEXT_STATE = "currentText";
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.textView1);
        progressBar = findViewById(R.id.progressBar);



        if(savedInstanceState!=null){
            myTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }
    public void startTask(View view){
        myTextView.setText(R.string.napping);

        new SimpleAsyncTask(myTextView, progressBar).execute();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, myTextView.getText().toString());
    }
}
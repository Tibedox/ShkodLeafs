package com.example.leafs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    float screenWidth, screenHeight;

    ConstraintLayout screen;
    int nLeafs = 25; // количество листьев
    Leaf[] leaf = new Leaf[nLeafs];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectScreenSize();

        screen = findViewById(R.id.scr);
        for (int i = 0; i < nLeafs; i++) {
            leaf[i] = new Leaf(this);
        }
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < nLeafs; i++) {
                    leaf[i].move();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10);
    }

    void detectScreenSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }
}
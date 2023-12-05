package com.example.leafs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    float screenWidth, screenHeight;

    int n = 2;
    ImageView[] leaf = new ImageView[n];
    float[] x = new float[n], y = new float[n];
    float[] speedX = new float[n], speedY = new float[n];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        leaf[0] = findViewById(R.id.leaf0);
        leaf[1] = findViewById(R.id.leaf1);
        speedX[0] = 5;
        speedX[1] = 4;
        speedY[0] = 5;
        speedY[1] = 3;
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < n; i++) {
                    x[i] = leaf[i].getX() + speedX[i];
                    if (x[i] > screenWidth) x[i] = -leaf[i].getWidth();
                    leaf[i].setX(x[i]);
                    y[i] = leaf[i].getY() + speedY[i];
                    if (y[i] > screenHeight) y[i] = -leaf[i].getHeight();
                    leaf[i].setY(y[i]);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10);
    }
}
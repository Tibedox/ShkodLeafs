package com.example.leafs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    float screenWidth, screenHeight;

    ConstraintLayout screen;
    int nLeafs = 25; // количество листьев
    ImageView[] leaf = new ImageView[nLeafs];

    float[] x = new float[nLeafs];
    float[] y = new float[nLeafs];
    float[] speedX = new float[nLeafs];
    float[] speedY = new float[nLeafs];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectScreenSize();
        screen = findViewById(R.id.scr);

        Random rnd = new Random();
        for (int i = 0; i < nLeafs; i++) {
            speedX[i] = rnd.nextFloat()*2;
            speedY[i] = rnd.nextFloat()*5+2;

            leaf[i] = new ImageView(this);
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(200, 200);
            leaf[i].setLayoutParams(params);
            leaf[i].setX(rnd.nextFloat()*(screenWidth+200)-200);
            leaf[i].setY(rnd.nextFloat()*(screenHeight+200)-200);
            leaf[i].setImageResource(R.drawable.leaf);
            screen.addView(leaf[i]);
        }
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < nLeafs; i++) {
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

    void detectScreenSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }
}
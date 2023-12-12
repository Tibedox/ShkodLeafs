package com.example.leafs;

import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class Leaf {
    MainActivity main;
    float x, y;
    float speedX, speedY;
    int width, height;
    ImageView imageLeaf;

    Leaf(MainActivity main){
        this.main = main;
        Random rnd = new Random();
        speedX = rnd.nextFloat()*2;
        speedY = rnd.nextFloat()*5+2;

        imageLeaf = new ImageView(main);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(200, 200);
        imageLeaf.setLayoutParams(params);
        x = rnd.nextFloat()*(main.screenWidth+200)-200;
        y = rnd.nextFloat()*(main.screenHeight+200)-200;
        imageLeaf.setX(x);
        imageLeaf.setY(y);
        imageLeaf.setImageResource(R.drawable.leaf);
        main.screen.addView(imageLeaf);
    }

    void move(){
        x += speedX;
        if (x > main.screenWidth) x = -imageLeaf.getWidth();
        imageLeaf.setX(x);
        y += speedY;
        if (y > main.screenHeight) y = -imageLeaf.getHeight();
        imageLeaf.setY(y);
    }
}

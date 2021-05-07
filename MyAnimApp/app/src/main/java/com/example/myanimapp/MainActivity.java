package com.example.myanimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton startImageButton = findViewById(R.id.startIb);
        ImageButton ballImageButton = findViewById(R.id.ballIb);
        ImageView imageView = findViewById(R.id.imageView);

        Button spinBtn = findViewById(R.id.spinBtn);
        Button jumpBtn = findViewById(R.id.jumpBtn);
        Button turnAroundBtn = findViewById(R.id.turnAroundBtn);

        startImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.star);
            }
        });

        ballImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.ball);
            }
        });

        spinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType") Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.layout.spin_animation);
                imageView.startAnimation(rotate);
            }
        });

        jumpBtn.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                imageView.startAnimation(AnimationUtils.loadAnimation(
                   getApplicationContext(),
                   R.anim.move
                ));
            }
        });

        turnAroundBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageView.animate().setDuration(1000).rotationYBy(360f);
//                        .withEndAction(() -> {
//                    imageView.animate().setDuration(1000).rotationYBy(360f);
//                }).start();
            }
        });
    }
}
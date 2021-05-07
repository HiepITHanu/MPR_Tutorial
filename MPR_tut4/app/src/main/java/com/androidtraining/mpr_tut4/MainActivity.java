package com.androidtraining.mpr_tut4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText linkTv = findViewById(R.id.link_tv);
        ImageButton playBnt = findViewById(R.id.play_btn);

        playBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = linkTv.getText().toString();

                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra("LINK", link);
                startActivity(intent);
            }
        });
    }
}

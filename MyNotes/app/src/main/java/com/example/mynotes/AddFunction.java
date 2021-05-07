package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddFunction extends AppCompatActivity {
    private EditText editText;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        Intent intent = getIntent();
        editText = findViewById(R.id.addNote);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("CONTENT",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}

package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.db.DbHelper;
import com.example.mynotes.models.Note;

public class EditFunction extends AppCompatActivity {
    private EditText editText;
    private NoteAdapter noteAdapter;
    private DbHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState,PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.edit_note);
        dbHelper = new DbHelper(getApplicationContext(), null, null, 3);

        Intent intent = getIntent();
        Note note = (Note) intent.getExtras().getSerializable("INSTANCE");

        editText = findViewById(R.id.editNote);
        editText.setText(note.getText());
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setText(editText.getText().toString());
                intent.putExtra("INSTANCE", note);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

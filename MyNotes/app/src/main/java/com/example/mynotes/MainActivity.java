package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.db.DbHelper;
import com.example.mynotes.models.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String text;
    private DbHelper dbHelper;
    private ArrayList<Note> listNote;
    private TextView textView;
    private NoteAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listNote = new ArrayList<>();
        dbHelper = new DbHelper(this, null, null, 3);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new NoteAdapter(listNote, this, MainActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent = new Intent(this, AddFunction.class);
            intent.putExtra("CONTENT",text);
            startActivityForResult(intent,101);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){

            if(resultCode == Activity.RESULT_OK){
                text = data.getSerializableExtra("CONTENT").toString();
                Note note = new Note();
                note.setText(text);
                dbHelper.insertNote(note);
                listNote.clear();

                listNote.addAll(dbHelper.resolveAllNotes());
                myAdapter.notifyDataSetChanged();

                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode==102){
            if(resultCode==Activity.RESULT_OK){
                Note note= (Note) data.getSerializableExtra("INSTANCE");
                myAdapter.editAdapter(note);
                myAdapter.notifyDataSetChanged();
                listNote.set(note.getId(),note);
                Toast.makeText(this,listNote.size() +"",Toast.LENGTH_LONG).show();

            }
        }
    }
}
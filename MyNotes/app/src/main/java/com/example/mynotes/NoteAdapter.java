package com.example.mynotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.db.DbHelper;
import com.example.mynotes.models.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<Note> listNote;
    private Activity activity;
    private Context context;

    public NoteAdapter(ArrayList<Note> listNote, Activity activity, Context context) {
        this.listNote = listNote;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(listNote.get(position).getText());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditFunction.class);
                Note note = new Note();
                note.setText(holder.textView.getText().toString());
                note.setId(position);
                intent.putExtra("INSTANCE",note);
                activity.startActivityForResult(intent,102);
            }
        });

        holder.textView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirm")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DbHelper dbHelper = new DbHelper(context, null, null, 3);
                                dbHelper.deleteNote(listNote.get(position).getId());
                                listNote.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

    public void editAdapter(Note note){
        DbHelper dbHelper = new DbHelper(context, null, null, 3);
        dbHelper.updateNote(note);
        notifyDataSetChanged();
    }

    public void addAdapter(Note note){
        DbHelper dbHelper = new DbHelper(context, null, null, 3);
        dbHelper.insertNote(note);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.editNote);
        }
    }
}

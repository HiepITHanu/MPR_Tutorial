package com.example.mynotes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mynotes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mpr_tut7.db";

    public DbHelper(@Nullable Context context,
                    @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DbSchema.NoteTable.Cols.TABLE_NAME + "("
                + DbSchema.NoteTable.Cols.COLUMN_ID
                + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DbSchema.NoteTable.Cols.COLUMN_TEXT + " TEXT " + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DbSchema.NoteTable.Cols.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public List<Note> resolveAllNotes(){
        List<Note> result = new ArrayList<Note>();
        String query = "SELECT * FROM " + DbSchema.NoteTable.Cols.TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Note note = new Note(cursor.getInt(0), cursor.getString(1));
                result.add(note);
            }while (cursor.moveToNext());
        }

        return result;
    }

    public Note resolveNoteById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                DbSchema.NoteTable.Cols.TABLE_NAME,
                new String[]{DbSchema.NoteTable.Cols.COLUMN_ID, DbSchema.NoteTable.Cols.COLUMN_TEXT},
                DbSchema.NoteTable.Cols.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();
            Note note = new Note();
            note.setId(Integer.parseInt(cursor.getString(0)));
            note.setText(cursor.getString(1));
            return note;
    }

    public void insertNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(DbSchema.NoteTable.Cols.COLUMN_TEXT, note.getText());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(DbSchema.NoteTable.Cols.TABLE_NAME, null, values);
        db.close();
    }

    public int updateNote(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbSchema.NoteTable.Cols.COLUMN_ID, note.getId() + 1);
        values.put(DbSchema.NoteTable.Cols.COLUMN_TEXT, note.getText());
        return db.update(DbSchema.NoteTable.Cols.TABLE_NAME,
                values,
                DbSchema.NoteTable.Cols.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId() + 1)});
    }

    public void deleteNote(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(
                DbSchema.NoteTable.Cols.TABLE_NAME,
                DbSchema.NoteTable.Cols.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}

package com.example.notesapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesDatabase extends SQLiteOpenHelper {
    private static String DATABASE_NAME="notesApp.db";
    private static int DATABASE_VERSION=1;
    private static String TABLE_NAME="allNotes";
    private static String COLUMN_ID="id";
    private static String COLUMN_TITLE="title";
    private static String COLUMN_DOCUMENT="document";
    private static String COLUMN_DATE="date";

    public NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE + " TEXT,"
                + COLUMN_DOCUMENT + " TEXT," + COLUMN_DATE+" TEXT"+")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void insertNote(Notes notes){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE,notes.getTitle());
        contentValues.put(COLUMN_DOCUMENT,notes.getDocument());
        contentValues.put(COLUMN_DATE,notes.getDate());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }
    public List<Notes> getAllNotes(){
        List<Notes> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select *from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String document = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCUMENT));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));

            Notes notes= new Notes(id,title,document,date);
            list.add(notes);
        }
        cursor.close();
        db.close();
        return list;
    }
    public void deleteNote(Notes notes){
        SQLiteDatabase db = getWritableDatabase();
        String whereClaus = COLUMN_ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(notes.getId())};
        db.delete(TABLE_NAME,whereClaus,whereArgs);
        db.close();
    }
    public void updateNotes(Notes notes){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE,notes.getTitle());
        contentValues.put(COLUMN_DOCUMENT,notes.getDocument());
        String whereClaus = COLUMN_ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(notes.getId())};
        db.update(TABLE_NAME,contentValues,whereClaus,whereArgs);
        db.close();
    }
    public Notes getNodeFromId(int id){
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select *from "+TABLE_NAME+" where "+COLUMN_ID+" = "+id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int idNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
        String document = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCUMENT));
        cursor.close();
        db.close();
        return new Notes(idNumber,title,document);
    }
}

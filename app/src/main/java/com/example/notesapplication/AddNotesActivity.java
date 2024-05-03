package com.example.notesapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.notesapplication.databinding.ActivityAddNotesBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddNotesActivity extends AppCompatActivity {
    private ActivityAddNotesBinding binding;
    private Notes notes;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notes = new Notes();
        notesDatabase = new NotesDatabase(this);
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                addNotes();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNotes(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formatDate  = date.format(formatter);
        String title = binding.idNoteTitle.getText().toString().trim();
        String document = binding.idNoteDoc.getText().toString().trim();
        notes.setTitle(title);
        notes.setDocument(document);
        notes.setDate(formatDate);
        notesDatabase.insertNote(notes);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finishAffinity();
    }
}
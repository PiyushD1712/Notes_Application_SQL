package com.example.notesapplication.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.notesapplication.databinding.ActivityAddNotesBinding;
import com.example.notesapplication.model.Notes;
import com.example.notesapplication.repository.NotesDatabase;
import com.example.notesapplication.viewmodel.MyViewModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {
    private ActivityAddNotesBinding binding;
    private Notes notes;
    private NotesDatabase notesDatabase;
    private MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notes = new Notes();
        model = new ViewModelProvider(this).get(MyViewModel.class);
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotes();
            }
        });
    }
    public void addNotes(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
        String formatDate  = dateFormat.format(date);
        String title = binding.idNoteTitle.getText().toString().trim();
        String document = binding.idNoteDoc.getText().toString().trim();
        notes.setTitle(title);
        notes.setDocument(document);
        notes.setDate(formatDate);
        model.insertNote(notes);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finishAffinity();
    }
}
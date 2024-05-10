package com.example.notesapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notesapplication.databinding.ActivityUpdateNoteBinding;
import com.example.notesapplication.model.Notes;
import com.example.notesapplication.repository.NotesDatabase;
import com.example.notesapplication.viewmodel.MyViewModel;
import com.example.notesapplication.views.MainActivity;

public class UpdateNoteActivity extends AppCompatActivity {
    private ActivityUpdateNoteBinding binding;
    private Intent intent;
    private MyViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(MyViewModel.class);
        updateNote();
    }
    public void updateNote() {
        intent = getIntent();
        int id = intent.getIntExtra("note_id", -1);

        if (id == -1) {
            finish();
        }
        Notes note = model.getNoteId(id);
        binding.idNoteTitle.setText(note.getTitle());
        binding.idNoteDoc.setText(note.getDocument());
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upTitle = binding.idNoteTitle.getText().toString();
                String upDoc = binding.idNoteDoc.getText().toString();
                Notes notesUp = new Notes(id, upTitle, upDoc);
                model.updateNotes(notesUp);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
            }
        });
    }
}
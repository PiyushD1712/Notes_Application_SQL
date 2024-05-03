package com.example.notesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notesapplication.databinding.ActivityUpdateNoteBinding;

public class UpdateNoteActivity extends AppCompatActivity {
    private ActivityUpdateNoteBinding binding;
    private NotesDatabase notesDatabase;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesDatabase = new NotesDatabase(this);
        updateNote();
    }
    public void updateNote() {
        intent = getIntent();
        int id = intent.getIntExtra("note_id", -1);

        if (id == -1) {
            finish();
        }
        Notes note = notesDatabase.getNodeFromId(id);
        binding.idNoteTitle.setText(note.getTitle());
        binding.idNoteDoc.setText(note.getDocument());
        binding.textView2.setText("Edit Note");
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upTitle = binding.idNoteTitle.getText().toString();
                String upDoc = binding.idNoteDoc.getText().toString();
                Notes notesUp = new Notes(id, upTitle, upDoc);
                notesDatabase.updateNotes(notesUp);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
            }
        });
    }
}
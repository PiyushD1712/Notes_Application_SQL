package com.example.notesapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notesapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotesAdapter notesAdapter;
    private NotesDatabase database;
    private List<Notes> notesList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.floatingActionButton.setOnClickListener(v->{
            startActivity(new Intent(this,AddNotesActivity.class));
        });
        fetchData();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position= viewHolder.getAdapterPosition();
                Notes note = notesList.get(position);
                database.deleteNote(note);
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                notesList.remove(position);
                notesAdapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notesAdapter.notifyDataSetChanged();
    }
    private void fetchData(){
        database = new NotesDatabase(this);
        notesList = database.getAllNotes();
        notesAdapter =  new NotesAdapter(this,notesList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();
    }
}
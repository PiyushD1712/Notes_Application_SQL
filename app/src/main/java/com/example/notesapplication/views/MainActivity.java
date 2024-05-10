package com.example.notesapplication.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notesapplication.adapters.NotesAdapter;
import com.example.notesapplication.databinding.ActivityMainBinding;
import com.example.notesapplication.model.Notes;
import com.example.notesapplication.repository.NotesDatabase;
import com.example.notesapplication.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotesAdapter notesAdapter;
    private MyViewModel model;
    private List<Notes> notesList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(MyViewModel.class);
        binding.floatingActionButton.setOnClickListener(v->{
            startActivity(new Intent(this, AddNotesActivity.class));
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
                model.deleteNote(note);
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
        notesList = model.getNotes();
        notesAdapter =  new NotesAdapter(this,notesList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();
    }
}
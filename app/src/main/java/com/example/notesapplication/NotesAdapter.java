package com.example.notesapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapplication.databinding.NoteListBinding;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Context context;
    private final List<Notes> notesList;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteListBinding noteListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.note_list,
                parent,
                false);
        return new MyViewHolder(noteListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notes notes = notesList.get(position);
        holder.noteListBinding.setNotes(notes);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final NoteListBinding noteListBinding;
        public MyViewHolder(NoteListBinding noteListBinding) {
            super(noteListBinding.getRoot());
            this.noteListBinding = noteListBinding;
            noteListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Notes note = notesList.get(position);
                    Intent intent = new Intent(v.getContext(),UpdateNoteActivity.class);
                    intent.putExtra("note_id",note.getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

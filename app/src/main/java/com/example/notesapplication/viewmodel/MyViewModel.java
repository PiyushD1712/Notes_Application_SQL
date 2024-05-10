package com.example.notesapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.notesapplication.model.Notes;
import com.example.notesapplication.repository.NotesDatabase;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private NotesDatabase repositoryDB;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repositoryDB =  new NotesDatabase(application);
    }

    public void insertNote(Notes notes){
        repositoryDB.insertNote(notes);
    }
    public List<Notes> getNotes(){
        return repositoryDB.getAllNotes();
    }
    public void deleteNote(Notes notes){
        repositoryDB.deleteNote(notes);
    }
    public void updateNotes(Notes notes){
        repositoryDB.updateNotes(notes);
    }
    public Notes getNoteId(int id){
        return repositoryDB.getNodeFromId(id);
    }
}

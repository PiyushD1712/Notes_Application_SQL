package com.example.notesapplication;

public class Notes {
    private int id;
    private String title;
    private String document;
    private String date;

    public Notes(int id, String title, String document,String date) {
        this.id = id;
        this.title = title;
        this.document = document;
        this.date = date;
    }

    public Notes(int id, String title, String document) {
        this.id = id;
        this.title = title;
        this.document = document;
    }

    public Notes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", document='" + document + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

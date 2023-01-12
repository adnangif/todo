package com.example.todojava;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "is_done")
    private int is_done;

    @ColumnInfo(name = "title")
    private String title;

    public Task(int id, int is_done, String title) {
        this.id = id;
        this.is_done = is_done;
        this.title = title;
    }


    @Ignore
    public Task(int is_done, String title) {
        this.is_done = is_done;
        this.title = title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int is_done) {
        this.is_done = is_done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

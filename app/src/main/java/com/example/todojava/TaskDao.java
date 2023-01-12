package com.example.todojava;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("select * from tasks_table")
    LiveData<List<Task>> getAll();

    @Update
    void updateOne(Task t);

    @Delete
    void deleteOne(Task t);

    @Insert
    void insertOne(Task t);
}

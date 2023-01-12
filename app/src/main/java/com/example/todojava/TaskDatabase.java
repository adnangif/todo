package com.example.todojava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;

@Database(entities = {Task.class},exportSchema = false,version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    public static final String DB_NAME = "Task_db";
    public static TaskDatabase instance;


    public static TaskDatabase getInstance(Context context){
        if(instance == null ) {
            instance = Room.databaseBuilder(context,TaskDatabase.class,DB_NAME)
                    .build();
        }
        return instance;
    }

    public abstract TaskDao taskDao();
}

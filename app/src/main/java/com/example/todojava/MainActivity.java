package com.example.todojava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextInputEditText task_description;
    MaterialButton btn_add;
    TaskDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        task_description =findViewById(R.id.et_task_description);
        btn_add =findViewById(R.id.btn_add);
        taskDatabase = TaskDatabase.getInstance(MainActivity.this);

        TaskAdapter taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        taskDatabase.taskDao().getAll().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                taskAdapter.setTaskList(tasks);
                taskAdapter.notifyDataSetChanged();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!task_description.getText().toString().trim().isEmpty()){
                    Task t = new Task( 0, task_description.getText().toString().trim());
                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            taskDatabase.taskDao().insertOne(t);
                        }
                    });

                    taskAdapter.notifyItemInserted(taskAdapter.sizeOfTaskList());
                    Toast.makeText(MainActivity.this, "Added!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Empty task!", Toast.LENGTH_SHORT).show();
                }
            }
        });








    }











}
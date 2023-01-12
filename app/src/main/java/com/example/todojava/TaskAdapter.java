package com.example.todojava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList = new ArrayList<>();
    private TaskDatabase taskDatabase;

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int sizeOfTaskList(){
        return taskList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskslist_item_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text_title.setText(taskList.get(position).getTitle());
        holder.task_state.setChecked(taskList.get(position).getIs_done() == 1);

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task t = taskList.get(holder.getAdapterPosition());
                taskDatabase = TaskDatabase.getInstance(view.getContext());

                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        taskDatabase.taskDao().deleteOne(t);
                    }
                });
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        MaterialCheckBox task_state;
        TextView text_title;
        MaterialButton btn_delete;
        //added delete confirmation dialogue
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            task_state = itemView.findViewById(R.id.task_state);
            text_title = itemView.findViewById(R.id.task_title);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}

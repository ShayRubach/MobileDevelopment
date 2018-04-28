package com.pwnz.www.mobileapplicaiton.controller;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pwnz.www.mobileapplicaiton.R;
import com.pwnz.www.mobileapplicaiton.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> implements View.OnClickListener {

    private ArrayList<Task> mTaskList;
    private RecyclerView mRecyclerView;

    public TaskAdapter(ArrayList<Task> tasksList) {
        this.mTaskList = tasksList;

    }

    @Override
    public void onClick(View view) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, dueDate, id;

        public MyViewHolder(ViewGroup view) {
            super(view);
            name = view.findViewById(R.id.title);
            id = view.findViewById(R.id.genre);
            dueDate =  view.findViewById(R.id.year);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewGroup itemView =(ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_row, parent, false);
        itemView.setOnClickListener(this);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = mTaskList.get(position);
        holder.name.setText(task.getTitle());
        holder.id.setText(task.getGenre());
        holder.dueDate.setText(task.getYear());

    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }
}
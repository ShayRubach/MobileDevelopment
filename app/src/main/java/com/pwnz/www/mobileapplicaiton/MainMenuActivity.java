package com.pwnz.www.mobileapplicaiton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pwnz.www.mobileapplicaiton.controller.TaskAdapter;
import com.pwnz.www.mobileapplicaiton.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private ArrayList<Task> mTaskList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mAdapter = new TaskAdapter(mTaskList);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        for (int i = 1; i <= 20; i++) {
            mTaskList.add(new Task("Task title ", "#" + String.valueOf(i), "1/1/18"));
        }

        mAdapter.notifyDataSetChanged();
    }
}

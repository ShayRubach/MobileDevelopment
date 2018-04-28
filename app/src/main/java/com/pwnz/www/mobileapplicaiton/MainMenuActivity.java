package com.pwnz.www.mobileapplicaiton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pwnz.www.mobileapplicaiton.controller.RecyclerTouchListener;
import com.pwnz.www.mobileapplicaiton.controller.TaskAdapter;
import com.pwnz.www.mobileapplicaiton.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private ArrayList<Task> mTaskList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;

    public static final String ACT_CALCULATOR = "Simple Calculator";
    public static final String ACT_BIRTHDAYS = "Birthday List";


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


        mTaskList.add(new Task("Simple Calculator", "#1", "1/1/18"));
        mTaskList.add(new Task("Birthday List", "#2", "1/1/18"));
        mTaskList.add(new Task("Future Task", "#3", "1/1/18"));


        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Task task = mTaskList.get(position);
                //Toast.makeText(getApplicationContext(), task.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                switch(mTaskList.get(position).getTitle()){
                    case ACT_CALCULATOR:
                       startActivity(new Intent(MainMenuActivity.this, CalculatorActivity.class));
                        break;
                    case ACT_BIRTHDAYS:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        mAdapter.notifyDataSetChanged();
    }
}

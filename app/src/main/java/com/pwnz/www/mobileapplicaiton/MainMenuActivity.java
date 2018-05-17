package com.pwnz.www.mobileapplicaiton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pwnz.www.mobileapplicaiton.controller.RecyclerTouchListener;
import com.pwnz.www.mobileapplicaiton.controller.TaskAdapter;
import com.pwnz.www.mobileapplicaiton.model.Task;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    private ArrayList<Task> mTaskList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;


    public static final String ACT_CALCULATOR = "Simple Calculator";
    public static final String ACT_BIRTHDAYS = "Birthday List";
    public static final String ACT_LLG_ANIMATION = "LLG Animation";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mTaskAdapter = new TaskAdapter(mTaskList);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mTaskAdapter);


        mTaskList.add(new Task(ACT_CALCULATOR, "#"+String.valueOf(mTaskList.size()+1), "100%", R.drawable.calc));
        mTaskList.add(new Task(ACT_BIRTHDAYS,  "#"+String.valueOf(mTaskList.size()+1), "100%", R.drawable.birthday));
        mTaskList.add(new Task(ACT_LLG_ANIMATION,  "#"+String.valueOf(mTaskList.size()+1), "20%", R.drawable.nyan_cat));


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
                        startActivity(new Intent(MainMenuActivity.this, BirthdayListActivity.class));
                        break;
                    case ACT_LLG_ANIMATION:
                        startActivity(new Intent(MainMenuActivity.this, LlgAnimationActivity.class));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mTaskAdapter.notifyDataSetChanged();
    }
}

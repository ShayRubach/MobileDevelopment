package com.pwnz.www.mobileapplicaiton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pwnz.www.mobileapplicaiton.controller.BirthdayAdapter;
import com.pwnz.www.mobileapplicaiton.controller.TaskAdapter;
import com.pwnz.www.mobileapplicaiton.model.Birthday;
import com.pwnz.www.mobileapplicaiton.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BirthdayListActivity extends AppCompatActivity {

    private ArrayList<Birthday> mBirthdayList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BirthdayAdapter mBirthdayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_list);

        mBirthdayAdapter = new BirthdayAdapter(mBirthdayList);

        mRecyclerView = findViewById(R.id.bday_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBirthdayAdapter);



        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            mBirthdayList.add(new Birthday(sdf.parse("29/4/1922"), "Shay Rubach", R.drawable.avatar1));
            mBirthdayList.add(new Birthday(sdf.parse("1/1/2000"), "Ran Shoshan", R.drawable.avatar2));
            mBirthdayList.add(new Birthday(sdf.parse("14/2/1990"), "Reut Leib", R.drawable.avatar3));

        } catch (ParseException e) {
            e.printStackTrace();
        }




    }
}

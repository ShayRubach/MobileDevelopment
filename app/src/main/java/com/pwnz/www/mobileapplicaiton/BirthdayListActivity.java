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

        mBirthdayList.add(new Birthday("29/4/1922", "Tiny Cox", R.drawable.avatar1));
        mBirthdayList.add(new Birthday("1/1/2000", "Dick Weiner", R.drawable.avatar2));
        mBirthdayList.add(new Birthday("14/2/1990", "Rick Titball", R.drawable.avatar3));
        mBirthdayList.add(new Birthday("14/2/1990", "Moe Lester", R.drawable.avatar4));
        mBirthdayList.add(new Birthday("14/2/1990", "Ben Dover", R.drawable.avatar5));
        mBirthdayList.add(new Birthday("29/4/1922", "Dixie Normous", R.drawable.avatar6));
        mBirthdayList.add(new Birthday("1/1/2000", "Jack Goff", R.drawable.avatar7));
        mBirthdayList.add(new Birthday("14/2/1990", "Justin Sider", R.drawable.avatar8));
        mBirthdayList.add(new Birthday("14/2/1990", "Thomas Fister", R.drawable.avatar9));
        mBirthdayList.add(new Birthday("14/2/1990", "Wendy Wacko", R.drawable.avatar10));
        mBirthdayList.add(new Birthday("14/2/1990", "Brownie Shytles", R.drawable.avatar11));
        mBirthdayList.add(new Birthday("14/2/1990", "Willie Stroker", R.drawable.avatar12));
        mBirthdayList.add(new Birthday("14/2/1990", "Candy Cummings", R.drawable.avatar13));

    }
}

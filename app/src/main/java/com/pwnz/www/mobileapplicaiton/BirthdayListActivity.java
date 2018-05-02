package com.pwnz.www.mobileapplicaiton;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        FloatingActionButton btnAddBday = findViewById(R.id.btnAddBirthday);




        mBirthdayAdapter = new BirthdayAdapter(mBirthdayList);

        mRecyclerView = findViewById(R.id.bday_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBirthdayAdapter);

        btnAddBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(BirthdayListActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_birthday, null);

                final EditText mPersonName = mView.findViewById(R.id.dialogPersonName);
                final EditText mDateDay = mView.findViewById(R.id.dialogDateDay);
                final EditText mDateMonth = mView.findViewById(R.id.dialogDateMonth);
                final EditText mDateYear = mView.findViewById(R.id.dialogDateYear);
                final Button mBtnDone = mView.findViewById(R.id.btnAddDone);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mBtnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //todo: add the bday to list
                        //todo: sort the list
                        if(!mPersonName.getText().toString().isEmpty() &&
                                !mDateDay.getText().toString().isEmpty() &&
                                !mDateMonth.getText().toString().isEmpty() &&
                                !mDateYear.getText().toString().isEmpty()){

                            Toast.makeText(BirthdayListActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                            dialog.hide();
                        }
                        else {
                            Toast.makeText(BirthdayListActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        addPremadeBdays(mBirthdayList);


    }

    void addPremadeBdays(ArrayList<Birthday> list){
        //some premade bdays:
//        list.add(new Birthday("29/4/1922", "Tiny Cox", R.drawable.avatar1));
//        list.add(new Birthday("1/1/2000", "Dick Weiner", R.drawable.avatar2));
//        list.add(new Birthday("14/2/1990", "Rick Titball", R.drawable.avatar3));
        list.add(new Birthday("14/2/1990", "Moe Lester", R.drawable.avatar4));
        list.add(new Birthday("14/2/1990", "Ben Dover", R.drawable.avatar5));
        list.add(new Birthday("29/4/1922", "Dixie Normous", R.drawable.avatar6));
        list.add(new Birthday("1/1/2000", "Jack Goff", R.drawable.avatar7));
        list.add(new Birthday("14/2/1990", "Justin Sider", R.drawable.avatar8));
//        list.add(new Birthday("14/2/1990", "Thomas Fister", R.drawable.avatar9));
//        list.add(new Birthday("14/2/1990", "Wendy Wacko", R.drawable.avatar10));
//        list.add(new Birthday("14/2/1990", "Brownie Shytles", R.drawable.avatar11));
//        list.add(new Birthday("14/2/1990", "Willie Stroker", R.drawable.avatar12));
    }
}





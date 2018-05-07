package com.pwnz.www.mobileapplicaiton;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import com.pwnz.www.mobileapplicaiton.model.BirthdayComparator;
import com.pwnz.www.mobileapplicaiton.model.BirthdayDb;
import com.pwnz.www.mobileapplicaiton.model.BirthdayEntity;

import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BirthdayListActivity extends AppCompatActivity {

    private ArrayList<BirthdayEntity> mBirthdayEntityList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BirthdayAdapter mBirthdayAdapter;
    public static BirthdayEntity bday = null;
    public static final String DRAWABLE_PATH = "../../res/drawable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_list);
        FloatingActionButton btnAddBday = findViewById(R.id.btnAddBirthday);

        mBirthdayAdapter = new BirthdayAdapter(mBirthdayEntityList);

        mRecyclerView = findViewById(R.id.bday_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBirthdayAdapter);

        //add some pre made birthdays for a better LAF (Look And Feel):
        //addPremadeBdays(mBirthdayEntityList);

        //displayStoragedEntities();

        // Create the observer which updates the UI.
        final Observer<List<BirthdayEntity>> bdayObserver = new Observer<List<BirthdayEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(@Nullable List<BirthdayEntity> birthdayEntities) {
                if(birthdayEntities.isEmpty()){
                    Toast.makeText(BirthdayListActivity.this, R.string.nobody_has_birthday, Toast.LENGTH_LONG).show();
                }
                for(BirthdayEntity bday : birthdayEntities){
                    if(!isBdayAlreadyDisplayed(bday)){
                        addBdayToList(bday);
                        System.out.println("\n===========LIST===========\n" + mBirthdayEntityList);
                        mBirthdayAdapter.notifyDataSetChanged();
                    }
                }
            }
        };

        LiveData<List<BirthdayEntity>> captainsLogEntityLiveData = BirthdayDb.getInstance(this).readBirthdays();

        captainsLogEntityLiveData.observe(this, bdayObserver);

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

                        //todo: sort the list
                        if(!mPersonName.getText().toString().isEmpty() &&
                                !mDateDay.getText().toString().isEmpty() &&
                                !mDateMonth.getText().toString().isEmpty() &&
                                !mDateYear.getText().toString().isEmpty()){

                            String name =  mPersonName.getText().toString();
                            String date = fixDateFormat(
                                    mDateDay.getText().toString(),
                                    mDateMonth.getText().toString(),
                                    mDateYear.getText().toString());

                            bday = new BirthdayEntity(name, date, randAvatar());

                            //add the record to db:
                            BirthdayDb.getInstance(BirthdayListActivity.this).writeToBirthdays(bday);

                            //on-screen msg
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

        //tell rv about the change:
        mBirthdayAdapter.notifyDataSetChanged();
    }

    private int randAvatar() {
        return R.drawable.avatar13;
    }

    private boolean isBdayAlreadyDisplayed(BirthdayEntity bday) {
        for(BirthdayEntity bde : mBirthdayEntityList){
            if(bde.getId() == bday.getId()){
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addBdayToList(BirthdayEntity bday) {
        mBirthdayEntityList.add(bday);
        Collections.sort(mBirthdayEntityList, new BirthdayComparator());
    }

    void addPremadeBdays(ArrayList<BirthdayEntity> list){
        //some premade bdays:
//        list.add(new BirthdayEntity("29/4/1922", "Tiny Cox", R.drawable.avatar1));
//        list.add(new BirthdayEntity("1/1/2000", "Dick Weiner", R.drawable.avatar2));
//        list.add(new BirthdayEntity("14/2/1990", "Rick Titball", R.drawable.avatar3));
        list.add(new BirthdayEntity("Moe Lester", "14/2/1990", R.drawable.avatar4));
        list.add(new BirthdayEntity("Ben Dover", "14/2/1990", R.drawable.avatar5));
        list.add(new BirthdayEntity("Dixie Normous", "29/4/1922", R.drawable.avatar6));
        list.add(new BirthdayEntity("Jack Goff", "1/1/2000", R.drawable.avatar7));
        list.add(new BirthdayEntity("Justin Sider", "14/2/1990", R.drawable.avatar8));
//        list.add(new BirthdayEntity("14/2/1990", "Thomas Fister", R.drawable.avatar9));
//        list.add(new BirthdayEntity("14/2/1990", "Wendy Wacko", R.drawable.avatar10));
//        list.add(new BirthdayEntity("14/2/1990", "Brownie Shytles", R.drawable.avatar11));
//        list.add(new BirthdayEntity("14/2/1990", "Willie Stroker", R.drawable.avatar12));
    }

    String fixDateFormat(String day, String month, String year){
        StringBuilder fixedDate = new StringBuilder(day);
        fixedDate.append("/");
        fixedDate.append(month);
        fixedDate.append("/");
        fixedDate.append(year);
        return fixedDate.toString();
    }
}





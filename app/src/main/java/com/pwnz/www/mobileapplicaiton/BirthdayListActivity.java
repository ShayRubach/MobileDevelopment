package com.pwnz.www.mobileapplicaiton;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
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
import com.pwnz.www.mobileapplicaiton.model.BirthdayDao;
import com.pwnz.www.mobileapplicaiton.model.BirthdayDb;
import com.pwnz.www.mobileapplicaiton.model.BirthdayEntity;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
        addPremadeBdays(mBirthdayEntityList);

        displayStoragedEntities();

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

                            String date = fixDateFormat(
                                    mDateDay.getText().toString(),
                                    mDateMonth.getText().toString(),
                                    mDateYear.getText().toString());

                            //todo: create bday DAO and add to Room DB
                            bday = new BirthdayEntity(
                                    mPersonName.getText().toString(),
                                    date,
                                    R.drawable.avatar13
                            );

                            BirthdayDb.getInstance(BirthdayListActivity.this).writeToBirthdays(bday);
                            addBdayToList(bday);
                            mBirthdayAdapter.notifyDataSetChanged();

                            Toast.makeText(BirthdayListActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                            dialog.hide();
                        }
                        else {
                            Toast.makeText(BirthdayListActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                        }
                        System.out.println(bday);
                    }
                });
            }
        });

        mBirthdayAdapter.notifyDataSetChanged();
    }

    private void displayStoragedEntities() {
        LiveData<List<BirthdayEntity>> list = BirthdayDb.getInstance(this).readBirthdays();

        if(list != null){
            //TODO: load all entities from db.
//            for (BirthdayEntity birthday : list.getValue()){
//                if(list.getValue() != null )
//                    System.out.println(birthday);
//            }
        }
    }

    private void addBdayToList(BirthdayEntity bday) {
        mBirthdayEntityList.add(bday);
    }

//    public void onDoneClicked(View view) {
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(BirthdayListActivity.this);
//        View mView = getLayoutInflater().inflate(R.layout.dialog_add_birthday, null);
//
//        EditText mPersonName = mView.findViewById(R.id.dialogPersonName);
//        EditText mDateDay = mView.findViewById(R.id.dialogDateDay);
//        EditText mDateMonth = mView.findViewById(R.id.dialogDateMonth);
//        EditText mDateYear = mView.findViewById(R.id.dialogDateYear);
//        Button mBtnDone = mView.findViewById(R.id.btnAddDone);
//
//        mBuilder.setView(mView);
//        final AlertDialog dialog = mBuilder.create();
//        dialog.show();
//
//
//        //todo: add the bday to list
//        //todo: sort the list
//        if(!mPersonName.getText().toString().isEmpty() &&
//            !mDateDay.getText().toString().isEmpty() &&
//            !mDateMonth.getText().toString().isEmpty() &&
//            !mDateYear.getText().toString().isEmpty()){
//
//            String date = fixDateFormat(
//                mDateDay.getText().toString(),
//                mDateMonth.getText().toString(),
//                mDateYear.getText().toString());
//
//                    //todo: create bday DAO and add to Room DB
//                    bday = new BirthdayEntity(
//                            mPersonName.getText().toString(),
//                            date,
//                            R.drawable.avatar13
//                    );
//
//                    Toast.makeText(BirthdayListActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
//                    dialog.hide();
//                }
//                else {
//                    Toast.makeText(BirthdayListActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
//                }
//
//
//    }

//    int randAvatar() {
//        File folder = new File(DRAWABLE_PATH);
//        File[] listOfFiles = folder.listFiles();
//        String avatar = null;
//
//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
//            } else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
//        }
//
//        return R.drawable.avatar1;
//        //return getResId(avatar, Drawable.class);
//    }

//    public static int getResId(String resName, Class<?> c) {
//        try {
//            Field idField = c.getDeclaredField(resName);
//            return idField.getInt(idField);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }

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





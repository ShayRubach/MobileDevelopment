package com.pwnz.www.mobileapplicaiton.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.pwnz.www.mobileapplicaiton.R;
import com.pwnz.www.mobileapplicaiton.model.Birthday;
import junit.framework.Assert;
import java.util.ArrayList;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.MyViewHolder> implements View.OnClickListener {

    private ArrayList<Birthday> mBirthdayList;
    private RecyclerView mRecyclerView;

    public BirthdayAdapter(ArrayList<Birthday> birthdayList) {
        this.mBirthdayList = birthdayList;

    }

    @Override
    public void onClick(View view) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView personName, birthdate;
        public ImageView personImage;

        public MyViewHolder(ViewGroup view) {
            super(view);
            personName = view.findViewById(R.id.personName);
            birthdate = view.findViewById(R.id.birthdate);
            personImage = view.findViewById(R.id.personImage);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewGroup itemView =(ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.birthday_list_row, parent, false);
        itemView.setOnClickListener(this);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Birthday bday = mBirthdayList.get(position);
        holder.personName.setText(bday.getName());
        holder.birthdate.setText(bday.getDate().toString());
        holder.personImage.setImageResource(bday.getmImageId());
    }

    @Override
    public int getItemCount() {
        return mBirthdayList.size();
    }

    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }
}
package com.pwnz.www.mobileapplicaiton.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "birthdays")
public class BirthdayEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "birthday_id")
    private int id;

    @ColumnInfo (name = "birth_date")
    private String mDate;

    @ColumnInfo (name = "person_name")
    private String mName;

    @ColumnInfo (name = "image_id")
    public int mImageId;


    public BirthdayEntity(String date, String name, int imageId) {
        setDate(date);
        setName(name);
        setmImageId(imageId);
    }

    @Override
    public String toString() {
        return "BirthdayEntity{" +
                "id=" + id +
                ", mDate='" + mDate + '\'' +
                ", mName='" + mName + '\'' +
                ", mImageId=" + mImageId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }


    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }
}

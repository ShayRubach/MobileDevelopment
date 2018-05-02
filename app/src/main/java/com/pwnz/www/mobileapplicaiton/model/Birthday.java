package com.pwnz.www.mobileapplicaiton.model;

public class Birthday {
    private String mDate, mName;
    private int mImageId;

    public Birthday(String date, String name, int imageId) {
        this.mDate= date;
        this.mName= name;
        this.mImageId = imageId;
    }

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
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
}

package com.pwnz.www.mobileapplicaiton.model;

import java.util.Date;

public class Birthday {
    private Date date;
    private String name;
    private int mImageId;

    public Birthday(Date date, String name, int imageId) {
        this.date = date;
        this.name = name;
        this.mImageId = imageId;
    }

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

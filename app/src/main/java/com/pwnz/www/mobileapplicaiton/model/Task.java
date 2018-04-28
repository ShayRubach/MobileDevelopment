package com.pwnz.www.mobileapplicaiton.model;

public class Task {
    private String mTitle, mId, mDueDate;

    public Task() {
    }

    public Task(String title, String id, String dueDate) {
        this.mTitle = title;
        this.mId = id;
        this.mDueDate = dueDate;

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String name) {
        this.mTitle = name;
    }

    public String getYear() {
        return mDueDate;
    }

    public void setYear(String year) {
        this.mDueDate = year;
    }

    public String getGenre() {
        return mId;
    }

    public void setGenre(String genre) {
        this.mId = genre;
    }
}

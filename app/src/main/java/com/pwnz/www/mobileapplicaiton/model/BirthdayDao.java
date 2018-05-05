package com.pwnz.www.mobileapplicaiton.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BirthdayDao {

    @Query(QueryHolder.QUERY_GET_ALL_BDAYS)
    LiveData<List<BirthdayEntity>> loadBirthdays();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BirthdayEntity bdayRecord);

}

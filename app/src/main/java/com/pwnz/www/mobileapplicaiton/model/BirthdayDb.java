package com.pwnz.www.mobileapplicaiton.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import java.util.List;

@Database(entities = {BirthdayEntity.class}, version=2)
public abstract class BirthdayDb extends RoomDatabase {

    private static BirthdayDb instance;

    public static BirthdayDb getInstance(Context context) {
        synchronized (BirthdayDb.class) {
            if (instance == null) {
                // notice getApplicationContext
                // -- it prevents the memory leak that would happen if the activity was passed
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        BirthdayDb.class, QueryHolder.DB_NAME)
                        .addMigrations(MIGRATION_1_2) // placeholder for future db versions
                        .build();
            }
            return instance;
        }
    }

    public abstract BirthdayDao getBirthdayDao();

    public LiveData<List<BirthdayEntity>> readBirthdays() {
        LiveData<List<BirthdayEntity>> list = getBirthdayDao().loadBirthdays();
        return list;
    }

    public void writeToBirthdays(final BirthdayEntity bday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getBirthdayDao().insert(bday);
            }
        }).start();
    }

    //use full queries instead of constants representing them (due to android dev. docs for migration)
    //see here: https://developer.android.com/training/data-storage/room/migrating-db-versions
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS birthdays (" +
                    "birthday_id INTEGER," +
                    "birth_date VARCHAR(128), " +
                    "person_name VARCHAR(128), " +
                    "image_id   INTEGER, " +
                    "PRIMARY KEY(birthday_id))"
            );
        }
    };

}

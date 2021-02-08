package com.test.deepak.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.test.deepak.data.local.dao.ShadiDao;
import com.test.deepak.data.local.entity.ShadiModel;

@Database(entities = {ShadiModel.class}, version = 1,  exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ShadiDao shadiDao();
    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "Shaadi.db")
                .allowMainThreadQueries().build();
    }
}

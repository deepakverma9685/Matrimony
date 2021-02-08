package com.test.deepak.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;


import com.test.deepak.data.local.AppDatabase;
import com.test.deepak.data.local.dao.ShadiDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "Shaadi.db")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    ShadiDao provideMovieDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.shadiDao();
    }


}

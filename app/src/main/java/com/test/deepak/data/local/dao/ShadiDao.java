package com.test.deepak.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.deepak.data.local.entity.ShadiModel;

import java.util.List;

import io.reactivex.Flowable;



@Dao
public interface ShadiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<ShadiModel> shadiModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMovie(ShadiModel shadiModel);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateMovie(ShadiModel shadiModel);

    @Query("SELECT * FROM `ShadiModel`")
    List<ShadiModel> getAllCards();
}

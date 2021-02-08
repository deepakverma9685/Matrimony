package com.test.deepak.data.remote.api;

import com.test.deepak.data.remote.models.ResultsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UrlServices {

    @GET("api/?results=10")
    Observable<ResultsModel> getAllCards();
}

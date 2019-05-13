package com.mrgama.nychighschools.services;

import com.google.gson.JsonArray;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface SchoolsApi {

    @GET("s3k6-pzi2.json")
    Observable<JsonArray> getSchools(@Header("X-App-Token") String token);

    @GET("f9bf-2cp4.json?")
    Observable<JsonArray> getSatScores(@Header("X-App-Token") String token, @Query("dbn") String dbn);
}
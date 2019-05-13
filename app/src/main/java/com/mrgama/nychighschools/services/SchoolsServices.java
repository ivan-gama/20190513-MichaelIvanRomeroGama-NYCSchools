package com.mrgama.nychighschools.services;

import com.google.gson.JsonArray;

import io.reactivex.Observable;

public class SchoolsServices {

    private SchoolsApi mSchoolsApi;

    public SchoolsServices(SchoolsApi schoolsApi) {
        mSchoolsApi = schoolsApi;
    }

    public Observable<JsonArray> getSchools(String token) {
        return mSchoolsApi.getSchools( token);
    }

    public Observable<JsonArray> getSatScores(String token, String dbn) {
        return mSchoolsApi.getSatScores(token, dbn);
    }

}
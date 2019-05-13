package com.mrgama.nychighschools.di;

import com.mrgama.nychighschools.services.SchoolsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {ServicesModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public SchoolsApi provideSchoolsApi(Retrofit retrofit) {
        return retrofit.create(SchoolsApi.class);
    }

}

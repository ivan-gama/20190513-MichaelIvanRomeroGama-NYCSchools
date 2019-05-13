package com.mrgama.nychighschools.di;

import com.mrgama.nychighschools.presenters.SchoolsMvpPresenter;
import com.mrgama.nychighschools.presenters.SchoolsPresenter;
import com.mrgama.nychighschools.services.SchoolsApi;
import com.mrgama.nychighschools.services.SchoolsServices;
import com.mrgama.nychighschools.view.SchoolsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = {ApiModule.class})
public class SchoolsModule {
    @Provides
    @Singleton
    public SchoolsServices provideSchoolsService(SchoolsApi schoolsApi) {
        return new SchoolsServices(schoolsApi);
    }

    @Provides
    public SchoolsMvpPresenter<SchoolsView> provideSchoolsPresenter(
            SchoolsPresenter<SchoolsView> presenter) {
        return presenter;
    }
}
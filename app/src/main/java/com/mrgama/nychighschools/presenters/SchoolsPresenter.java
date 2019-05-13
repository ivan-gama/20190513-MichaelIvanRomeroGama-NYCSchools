package com.mrgama.nychighschools.presenters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.mrgama.nychighschools.model.NYCError;
import com.mrgama.nychighschools.model.SatScore;
import com.mrgama.nychighschools.model.School;
import com.mrgama.nychighschools.presenters.base.BasePresenter;
import com.mrgama.nychighschools.services.SchoolsServices;
import com.mrgama.nychighschools.view.SchoolsView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class SchoolsPresenter<V extends SchoolsView> extends BasePresenter<SchoolsView>
        implements SchoolsMvpPresenter<V> {

    private static final String mToken = "1GjmtHFxe2Tw7fQCSfAd0KbOS";
    private SchoolsServices mSchoolsServices;

    @Inject
    public SchoolsPresenter(SchoolsServices mSchoolsServices) {
        this.mSchoolsServices = mSchoolsServices;
    }


    @Override
    public void getSchools() {

        getMvpView().showLoading();

        final Observable<JsonArray> observable = mSchoolsServices.getSchools(mToken);

        Disposable disposable = observable.compose(applySchedulers())
                .subscribeWith(new DisposableObserver<JsonArray>() {
                    @Override
                    public void onNext(JsonArray response) {
                        getMvpView().hideLoading();

                        if (response != null) {
                            getMvpView().onSuccessGetSchools(new Gson().fromJson(response, new TypeToken<List<School>>() {
                            }.getType()));

                        } else
                            getMvpView().onServiceError(new NYCError(200, "",
                                    "La consulta no obtuvo resultados"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleServiceError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        unsubscribeOnDestroy(disposable);
    }

    @Override
    public void getSatScores(String dbn) {

        getMvpView().showLoading();

        final Observable<JsonArray> observable = mSchoolsServices.getSatScores(mToken,dbn);

        Disposable disposable = observable.compose(applySchedulers())
                .subscribeWith(new DisposableObserver<JsonArray>() {
                    @Override
                    public void onNext(JsonArray response) {
                        getMvpView().hideLoading();

                        if (response != null) {
                            getMvpView().onSuccessGetSatScores(new Gson().fromJson(response, new TypeToken<List<SatScore>>() {
                            }.getType()));

                        } else
                            getMvpView().onServiceError(new NYCError(200, "",
                                    "La consulta no obtuvo resultados"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleServiceError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        unsubscribeOnDestroy(disposable);
    }
}
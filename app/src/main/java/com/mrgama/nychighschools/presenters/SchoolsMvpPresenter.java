package com.mrgama.nychighschools.presenters;

import com.mrgama.nychighschools.presenters.base.MvpPresenter;
import com.mrgama.nychighschools.view.SchoolsView;

public interface SchoolsMvpPresenter<V extends SchoolsView> extends MvpPresenter<SchoolsView> {

    void getSchools();

    void getSatScores(String dbn);

}

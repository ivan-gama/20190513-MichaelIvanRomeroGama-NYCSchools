package com.mrgama.nychighschools.view;


import com.mrgama.nychighschools.model.SatScore;
import com.mrgama.nychighschools.model.School;

import java.util.List;

public interface SchoolsView extends MvpView {
    String TAG = "SchoolsView";

    void onSuccessGetSchools(List<School> response);

    void onSuccessGetSatScores(List<SatScore> response);

}

package com.mrgama.nychighschools.ui.schools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mrgama.nychighschools.NYCApplication;
import com.mrgama.nychighschools.R;
import com.mrgama.nychighschools.databinding.FragmentSchoolDetailBinding;
import com.mrgama.nychighschools.model.SatScore;
import com.mrgama.nychighschools.model.School;
import com.mrgama.nychighschools.presenters.SchoolsMvpPresenter;
import com.mrgama.nychighschools.ui.base.BaseFragment;
import com.mrgama.nychighschools.view.SchoolsView;

import java.util.List;

import javax.inject.Inject;

public class SchoolDetailFragment extends BaseFragment implements SchoolsView{
    public static final String TAG = "SchoolDetailFragment";

    private FragmentSchoolDetailBinding binding;
    private School mSchool;

    // Inject Schools Presenter
    @Inject
    SchoolsMvpPresenter<SchoolsView> mSchoolsPresenter;
    private List<SatScore> mSatScores;

    public static SchoolDetailFragment newInstance(String schoolDbn, School school) {
        SchoolDetailFragment fragment = new SchoolDetailFragment();
        Bundle args = new Bundle();
        args.putString(SchoolsListFragment.ARGUMENT_SCHOOL_DBN, schoolDbn);
        args.putParcelable(SchoolsListFragment.ARGUMENT_SCHOOL, school);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NYCApplication.getAppComponent().inject(this);

        if (getArguments() != null) {
            String schoolDbn = getArguments().getString(SchoolsListFragment.ARGUMENT_SCHOOL_DBN);
            mSchool = getArguments().getParcelable(SchoolsListFragment.ARGUMENT_SCHOOL);

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_school_detail,
                container, false);

        mSchoolsPresenter.onAttach(this);

        binding.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigateUp();
            }
        });

        if (mSatScores == null) {
            mSchoolsPresenter.getSatScores(mSchool.getDbn());
        }
        else {
            setSatScores(mSatScores);
        }

        setSchoolDetailInfo();
        return binding.getRoot();
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mSchoolsPresenter.onDetach();
    }

    private void setSchoolDetailInfo() {
        binding.setSchool(mSchool);
    }

    private void setSatScores(List<SatScore> mSatScores) {
        binding.setSatScore(mSatScores.get(0));
    }

    @Override
    public void onSuccessGetSchools(List<School> schoolList) {

    }

    @Override
    public void onSuccessGetSatScores(List<SatScore> satScoreList) {
        if (satScoreList != null && satScoreList.size() > 0)
            setSatScores(satScoreList);

    }
}

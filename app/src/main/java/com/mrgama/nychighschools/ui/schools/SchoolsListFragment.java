package com.mrgama.nychighschools.ui.schools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.mrgama.nychighschools.NYCApplication;
import com.mrgama.nychighschools.R;
import com.mrgama.nychighschools.adapters.SchoolsListAdapter;
import com.mrgama.nychighschools.databinding.FragmentSchoolsListBinding;
import com.mrgama.nychighschools.model.NYCError;
import com.mrgama.nychighschools.model.SatScore;
import com.mrgama.nychighschools.model.School;
import com.mrgama.nychighschools.presenters.SchoolsMvpPresenter;
import com.mrgama.nychighschools.ui.base.BaseFragment;
import com.mrgama.nychighschools.view.SchoolsView;

import java.util.List;

import javax.inject.Inject;


public class SchoolsListFragment extends BaseFragment implements SchoolsListAdapter.SchoolItemListener,
        SchoolsView {
    public static final String TAG = "SchoolsListFragment";

    static final String ARGUMENT_SCHOOL_DBN = "ARGUMENT_SCHOOL_DBN";
    static final String ARGUMENT_SCHOOL = "ARGUMENT_SCHOOL";
    // Inject Schools Presenter
    @Inject
    SchoolsMvpPresenter<SchoolsView> mSchoolsPresenter;
    private FragmentSchoolsListBinding mBinding;
    private FragmentActivity mActivity;
    private List<School> mSchoolsList;

    public static SchoolsListFragment newInstance() {
        Bundle args = new Bundle();
        SchoolsListFragment fragment = new SchoolsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = requireActivity();
        NYCApplication.getAppComponent().inject(this);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_schools_list,
                container, false);

        mSchoolsPresenter.onAttach(this);
        /*ArrayList<School> schoolArrayList = JsonUtils.parseSchoolsFromRawFile(requireActivity(),
                R.raw.school_test);
        initSchoolListRecycler(schoolArrayList);*/

        mBinding.swipeRefresh.setOnRefreshListener(() -> {
            mBinding.swipeRefresh.setRefreshing(false);
            mSchoolsPresenter.getSchools();
        });

        if (mSchoolsList == null)
            mSchoolsPresenter.getSchools();
        else
            initSchoolListRecycler(mSchoolsList);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSchoolsPresenter.onDetach();
    }

    private void initSchoolListRecycler(List<School> schoolArrayList) {
        final SchoolsListAdapter schoolsListAdapter =
                new SchoolsListAdapter(schoolArrayList, this);

        mBinding.recyclerSchools.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mBinding.recyclerSchools.setAdapter(schoolsListAdapter);
    }


    @Override
    public void onSchoolSelected(School school) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_SCHOOL_DBN, school.getDbn()); // TODO use this value to recover from local database
        bundle.putParcelable(ARGUMENT_SCHOOL, school);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.openSchoolDetailFragment, bundle);
    }

    @Override
    public void onSuccessGetSchools(List<School> schoolList) {
        mSchoolsList = schoolList;
        initSchoolListRecycler(schoolList);
    }

    @Override
    public void onSuccessGetSatScores(List<SatScore> satScoreList) {

    }

    @Override
    public void showLoading() {
        super.showLoading();
        mBinding.progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        mBinding.progressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onServiceError(NYCError error) {
        Snackbar snackbar = Snackbar
                .make(mBinding.coordinator, error.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", view -> {
                    mSchoolsPresenter.getSchools();
                });

        snackbar.show();
    }


}

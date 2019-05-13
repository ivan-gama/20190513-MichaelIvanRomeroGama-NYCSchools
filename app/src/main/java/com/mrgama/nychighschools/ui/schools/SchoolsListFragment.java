package com.mrgama.nychighschools.ui.schools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mrgama.nychighschools.R;
import com.mrgama.nychighschools.adapters.SchoolsListAdapter;
import com.mrgama.nychighschools.databinding.FragmentSchoolsListBinding;
import com.mrgama.nychighschools.model.School;
import com.mrgama.nychighschools.utils.JsonUtils;

import java.util.ArrayList;


public class SchoolsListFragment extends Fragment implements SchoolsListAdapter.SchoolItemListener {
    public static final String TAG = "SchoolsListFragment";

    static final String ARGUMENT_SCHOOL_DBN = "ARGUMENT_SCHOOL_DBN";
    static final String ARGUMENT_SCHOOL = "ARGUMENT_SCHOOL";

    private FragmentSchoolsListBinding mBinding;

    private FragmentActivity mActivity;

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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_schools_list,
                container, false);

        ArrayList<School> schoolArrayList = JsonUtils.parseSchoolsFromRawFile(requireActivity(),
                R.raw.school_test);

        initSchoolListRecycler(schoolArrayList);
        return mBinding.getRoot();
    }


    private void initSchoolListRecycler(ArrayList<School> schoolArrayList) {
        final SchoolsListAdapter schoolsListAdapter =
                new SchoolsListAdapter(schoolArrayList, this);

        mBinding.recyclerSchools.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mBinding.recyclerSchools.setAdapter(schoolsListAdapter);
    }


    @Override
    public void onSchoolSelected(School school) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_SCHOOL_DBN, school.getDbn());
        bundle.putParcelable(ARGUMENT_SCHOOL, school);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.openSchoolDetailFragment, bundle);
    }
}

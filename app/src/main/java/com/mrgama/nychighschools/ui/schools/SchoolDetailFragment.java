package com.mrgama.nychighschools.ui.schools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mrgama.nychighschools.R;
import com.mrgama.nychighschools.databinding.FragmentSchoolDetailBinding;
import com.mrgama.nychighschools.model.School;

public class SchoolDetailFragment extends Fragment {
    public static final String TAG = "SchoolDetailFragment";

    private FragmentSchoolDetailBinding binding;
    private School mSchool;

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

        binding.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigateUp();
            }
        });

        setSchoolDetailInfo();
        return binding.getRoot();
    }

    private void setSchoolDetailInfo() {
        binding.setSchool(mSchool);
    }

}

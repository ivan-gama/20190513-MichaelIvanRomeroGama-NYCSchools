package com.mrgama.nychighschools.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mrgama.nychighschools.R;
import com.mrgama.nychighschools.model.NYCError;
import com.mrgama.nychighschools.view.MvpView;

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onServiceError(NYCError error) {
        if (mActivity != null) {
            mActivity.onServiceError(error);
        }
    }


    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }


    public BaseActivity getBaseActivity() {
        return mActivity;
    }



    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}

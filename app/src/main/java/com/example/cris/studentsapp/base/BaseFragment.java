package com.example.cris.studentsapp.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.utils.FragmentUtils;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment implements IBaseNoInternetViewDelegate{


    public void addFragment(Fragment fragment, @IdRes int layoutId) {
        if (getActivity() != null) {
            FragmentUtils.replaceFragment(getActivity(), layoutId, fragment);
        }
    }

    @Override
    public void onNoInternetConnection() {
        Toast.makeText(getContext(), getString(R.string.alert_no_internet_connection), Toast.LENGTH_SHORT).show();
    }
}

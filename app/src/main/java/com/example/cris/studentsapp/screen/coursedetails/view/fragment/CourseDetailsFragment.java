package com.example.cris.studentsapp.screen.coursedetails.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.coursedetails.presenter.ICourseDetailsPresenter;
import com.example.cris.studentsapp.screen.coursedetails.view.delegate.ICourseDetailsViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_ID;

public class CourseDetailsFragment extends BaseFragment implements
        ICourseDetailsViewDelegate {

    private String mCourseId = "";

    @Inject
    ICourseDetailsPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        mCourseId = getArguments().getString(COURSE_ID);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.course_details);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, true, false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onError(String errorMessage) {

    }

    public static CourseDetailsFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(COURSE_ID, id);
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {

    }
}

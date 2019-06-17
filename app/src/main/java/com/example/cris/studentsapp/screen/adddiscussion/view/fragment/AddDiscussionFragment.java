package com.example.cris.studentsapp.screen.adddiscussion.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.adddiscussion.presenter.IAddDiscussionPresenter;
import com.example.cris.studentsapp.screen.adddiscussion.view.delegate.IAddDiscussionViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;

import javax.inject.Inject;

public class AddDiscussionFragment extends BaseFragment implements IAddDiscussionViewDelegate {

    private ProgressBar mProgressBar;

    @Inject
    IAddDiscussionPresenter mPresenter;

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
        return inflater.inflate(R.layout.fragment_add_discussion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mCourseDetailsItems = new ArrayList<>();
//
//        mCourseId = getArguments().getString(COURSE_ID);
//        mCourseName = getArguments().getString(COURSE_NAME);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.add_discussion);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, false, false);

        initView(view);

        //mPresenter.getCourseDetails(mCourseId);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String errorMessage) {
        AlertUtils.alert(getContext(), R.string.alert_title, errorMessage);
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);
        
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
    }
}

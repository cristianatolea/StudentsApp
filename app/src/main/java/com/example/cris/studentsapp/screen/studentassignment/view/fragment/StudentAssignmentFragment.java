package com.example.cris.studentsapp.screen.studentassignment.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.studentassignment.presenter.IStudentAssignmentPresenter;
import com.example.cris.studentsapp.screen.studentassignment.view.delegate.IStudentAssignmentViewDelegate;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_ID;
import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;
import static com.example.cris.studentsapp.utils.Constants.DEADLINE_ID;
import static com.example.cris.studentsapp.utils.Constants.DEADLINE_NAME;
import static com.example.cris.studentsapp.utils.Constants.USER_ID;
import static com.example.cris.studentsapp.utils.Constants.USER_NAME;

public class StudentAssignmentFragment extends BaseFragment implements
        IStudentAssignmentViewDelegate {

    private ProgressBar mProgressBar;

    private String mDeadlineId = "", mDeadlineName = "", mUserId = "", mUsername = "";

    @Inject
    IStudentAssignmentPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_assignment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mEnrolledList = new ArrayList<>();
//
        mDeadlineId = getArguments().getString(DEADLINE_ID);
        mDeadlineName = getArguments().getString(DEADLINE_NAME);
        mUserId = getArguments().getString(USER_ID);
        mUsername = getArguments().getString(USER_NAME);

        initView(view);

        //mPresenter.getEnrolledStudents(mCourseId);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.assignments);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, false, false);
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

    public static StudentAssignmentFragment newInstance(String deadlineId,
                                                        String deadlineName,
                                                        String userId,
                                                        String username) {
        Bundle args = new Bundle();
        args.putString(DEADLINE_ID, deadlineId);
        args.putString(DEADLINE_NAME, deadlineName);
        args.putString(USER_ID, userId);
        args.putString(USER_NAME, username);
        StudentAssignmentFragment fragment = new StudentAssignmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        TextView textStudent = view.findViewById(R.id.text_student_name);
        TextView textDeadline = view.findViewById(R.id.text_deadline_name);

        textStudent.setText(mUsername);
        textDeadline.setText(mDeadlineName);

    }
}

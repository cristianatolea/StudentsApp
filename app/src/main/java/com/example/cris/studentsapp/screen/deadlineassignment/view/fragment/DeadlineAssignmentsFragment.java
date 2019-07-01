package com.example.cris.studentsapp.screen.deadlineassignment.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.coursedetails.view.fragment.CourseDetailsFragment;
import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.EnrolledUserEntity;
import com.example.cris.studentsapp.screen.deadlineassignment.presenter.IDeadlineAssignmentsPresenter;
import com.example.cris.studentsapp.screen.deadlineassignment.view.adapter.DeadlineEnrolledAdapter;
import com.example.cris.studentsapp.screen.deadlineassignment.view.delegate.IDeadlineAssignmentsViewDelegate;
import com.example.cris.studentsapp.screen.forumspercourse.view.adapter.SimpleDividerItemDecoration;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.studentassignment.view.fragment.StudentAssignmentFragment;
import com.example.cris.studentsapp.utils.AlertUtils;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_ID;
import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;
import static com.example.cris.studentsapp.utils.Constants.DEADLINE_ID;
import static com.example.cris.studentsapp.utils.Constants.DEADLINE_NAME;

public class DeadlineAssignmentsFragment extends BaseFragment implements
        IDeadlineAssignmentsViewDelegate,
        DeadlineEnrolledAdapter.OnStudentItemClickListener {

    private ProgressBar mProgressBar;
    private TextView mTextCourseName, mTextDeadlineName;

    private String mCourseId = "", mCourseName = "", mDeadlineName = "", mDeadlineId = "";
    private List<EnrolledUserEntity> mEnrolledList;
    private DeadlineEnrolledAdapter mAdapter;
    private boolean mIsCurrentUserTeacher;

    @Inject
    IDeadlineAssignmentsPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deadline_assignments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEnrolledList = new ArrayList<>();
        mIsCurrentUserTeacher = false;

        mCourseId = getArguments().getString(COURSE_ID);
        mCourseName = getArguments().getString(COURSE_NAME);
        mDeadlineName = getArguments().getString(DEADLINE_NAME);
        mDeadlineId = getArguments().getString(DEADLINE_ID);

        initView(view);

        mPresenter.getEnrolledStudents(mCourseId);

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

    @Override
    public void onGetEnrolledStudentsSuccess(List<EnrolledUserEntity> list,
                                             boolean isCurrentUserTeacher) {
        mEnrolledList.clear();
        mEnrolledList.addAll(list);
        mAdapter.notifyDataSetChanged();
        mIsCurrentUserTeacher = isCurrentUserTeacher;
    }

    @Override
    public void OnGetEnrolledFailed() {

    }

    @Override
    public void onStudentClick(int position) {
        if (mIsCurrentUserTeacher) {
            StudentAssignmentFragment fragment = StudentAssignmentFragment
                    .newInstance(mDeadlineId,
                            mDeadlineName,
                            mEnrolledList.get(position).getUserId(),
                            mEnrolledList.get(position).getFullname());
            addFragment(fragment, R.id.frame_main_content);
        } else {
            if (mEnrolledList.get(position).getUserId().equals(LocalSaving.getUserId(getContext()))) {
                StudentAssignmentFragment fragment = StudentAssignmentFragment
                        .newInstance(mDeadlineId,
                                mDeadlineName,
                                mEnrolledList.get(position).getUserId(),
                                mEnrolledList.get(position).getFullname());
                addFragment(fragment, R.id.frame_main_content);
            }
        }
    }

    public static DeadlineAssignmentsFragment newInstance(String courseId,
                                                          String courseName,
                                                          String deadlineName,
                                                          String deadlineId) {
        Bundle args = new Bundle();
        args.putString(COURSE_ID, courseId);
        args.putString(COURSE_NAME, courseName);
        args.putString(DEADLINE_NAME, deadlineName);
        args.putString(DEADLINE_ID, deadlineId);
        DeadlineAssignmentsFragment fragment = new DeadlineAssignmentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mTextCourseName = view.findViewById(R.id.text_course_name);
        mTextDeadlineName = view.findViewById(R.id.text_deadline_name);
        RecyclerView rvEnrolled = view.findViewById(R.id.rv_enrolled_students);

        mTextCourseName.setText(mCourseName);
        mTextDeadlineName.setText(mDeadlineName);

        SimpleDividerItemDecoration dividerItemDecoration =
                new SimpleDividerItemDecoration(rvEnrolled.getContext());
        rvEnrolled.addItemDecoration(dividerItemDecoration);
        rvEnrolled.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new DeadlineEnrolledAdapter(getContext(), mEnrolledList, this);
        rvEnrolled.setAdapter(mAdapter);
    }
}

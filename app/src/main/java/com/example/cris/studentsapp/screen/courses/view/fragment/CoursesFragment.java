package com.example.cris.studentsapp.screen.courses.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.coursedetails.view.fragment.CourseDetailsFragment;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.courses.presenter.ICoursesPresenter;
import com.example.cris.studentsapp.screen.courses.view.adapter.CoursesAdapter;
import com.example.cris.studentsapp.screen.courses.view.delegate.ICoursesViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CoursesFragment extends BaseFragment implements
        ICoursesViewDelegate,
        CoursesAdapter.OnCourseItemClick {

    private CoursesAdapter mAdapter;
    private List<CourseEntity> mCoursesList;
    private ProgressBar mProgressBar;

    @Inject
    ICoursesPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCoursesList = new ArrayList<>();
        initView(view);
        mPresenter.getCourses();

        ((MainActivity) getActivity()).setToolbarTitle(R.string.courses);
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
    public void onGetCoursesSuccess(List<CourseEntity> list) {
        mCoursesList.clear();
        mCoursesList.addAll(list);
        mCoursesList.remove(0);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCourseItemClick(int position) {
        CourseDetailsFragment courseDetailsFragment =
                CourseDetailsFragment.newInstance(mCoursesList.get(position).getId(),
                        mCoursesList.get(position).getFullname());
        ((MainActivity) getActivity())
                .changeFocusOnMenu(0, false, false);
        addFragment(courseDetailsFragment, R.id.frame_main_content);
    }

    private void initView(View view) {
        RecyclerView rvCourses = view.findViewById(R.id.rv_courses);
        mAdapter = new CoursesAdapter(getContext(), mCoursesList, this);
        mProgressBar = getActivity().findViewById(R.id.progress_bar);

        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCourses.setAdapter(mAdapter);
    }
}

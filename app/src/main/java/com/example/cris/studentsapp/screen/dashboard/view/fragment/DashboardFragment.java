package com.example.cris.studentsapp.screen.dashboard.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.courses.view.fragment.CoursesFragment;
import com.example.cris.studentsapp.screen.dashboard.model.entity.DashboardItem;
import com.example.cris.studentsapp.screen.dashboard.presenter.IDashboardPresenter;
import com.example.cris.studentsapp.screen.dashboard.view.adapter.DashboardAdapter;
import com.example.cris.studentsapp.screen.dashboard.view.delegate.IDashboardViewDelegate;
import com.example.cris.studentsapp.screen.deadlines.view.fragment.DeadlinesFragment;
import com.example.cris.studentsapp.screen.forumspercourse.view.fragment.ForumsFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.schedule.view.fragment.ScheduleFragment;
import com.example.cris.studentsapp.utils.AlertUtils;
import com.example.cris.studentsapp.utils.FragmentUtils;

import java.util.List;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements
        IDashboardViewDelegate,
        DashboardAdapter.OnDashboardItemClickListener {

    public static final String BUNDLE_FRAGMENT_NO = "bundle-dashboard-item";

    private ProgressBar mProgressBar;
    private RecyclerView mRvDashboard;
    private DashboardAdapter mAdapter;

    @Inject
    IDashboardPresenter mPresenter;

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
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mPresenter.populateDashboard(view);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.dashboard);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, true, false);
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
    public void updateDashboardScreen(List<DashboardItem> list) {
        mAdapter = new DashboardAdapter(getContext(), list, this);
        mRvDashboard.setAdapter(mAdapter);
    }

    @Override
    public void onDashboardItemClick(int position) {
        switch (position) {
            case 0:
                FragmentUtils.replaceFragment(getActivity(), R.id.frame_main_content, new CoursesFragment());
                break;
            case 1:
                FragmentUtils.replaceFragment(getActivity(), R.id.frame_main_content, new ForumsFragment());
                break;
            case 2:
                FragmentUtils.replaceFragment(getActivity(), R.id.frame_main_content, new ScheduleFragment());
                break;
            case 3:
                FragmentUtils.replaceFragment(getActivity(), R.id.frame_main_content, new DeadlinesFragment());
                break;
        }
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);

        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mRvDashboard = view.findViewById(R.id.rv_dashboard);
        mRvDashboard.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}

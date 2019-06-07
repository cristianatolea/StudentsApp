package com.example.cris.studentsapp.screen.schedule.view.fragment;

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
import com.example.cris.studentsapp.screen.dayschedule.view.fragment.DayScheduleFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;
import com.example.cris.studentsapp.screen.schedule.presenter.ISchedulePresenter;
import com.example.cris.studentsapp.screen.schedule.view.adapter.weekdays.WeekDayAdapter;
import com.example.cris.studentsapp.screen.schedule.view.delegate.IScheduleViewDelegate;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ScheduleFragment extends BaseFragment implements
        IScheduleViewDelegate,
        WeekDayAdapter.OnItemDetailsClickListener {

    private ProgressBar mProgressBar;
    private TextView mTextError;

    public static List<WeekDayEntity> mWeekDays;
    private WeekDayAdapter mDaysAdapter;

    @Inject
    ISchedulePresenter mPresenter;

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
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWeekDays = new ArrayList<>();

        mPresenter.prepareDays();

        ((MainActivity) getActivity()).setToolbarTitle(R.string.schedule);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, false, false);

        initView(view);
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
    public void onGetNamedDaysSuccess(List<WeekDayEntity> list) {
        mWeekDays.clear();
        mWeekDays.addAll(list);
        mTextError.setVisibility(View.GONE);
        mDaysAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetNamedDaysFailed() {
        mTextError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(int position) {
        DayScheduleFragment dayScheduleFragment = DayScheduleFragment.newInstance(position);
        ((MainActivity) getActivity())
                .changeFocusOnMenu(0, false, false);
        addFragment(dayScheduleFragment, R.id.frame_main_content);
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mTextError = view.findViewById(R.id.text_error);
        RecyclerView rvWeekDays = view.findViewById(R.id.rv_week_days);

        rvWeekDays.setLayoutManager(new LinearLayoutManager(getContext()));
        mDaysAdapter = new WeekDayAdapter(getContext(), mWeekDays, this);
        rvWeekDays.setAdapter(mDaysAdapter);

        mTextError.setVisibility(View.GONE);
    }
}

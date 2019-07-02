package com.example.cris.studentsapp.screen.schedule.view.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.alarmservice.AlarmReceiver;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;
import com.example.cris.studentsapp.screen.dayschedule.view.fragment.DayScheduleFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;
import com.example.cris.studentsapp.screen.schedule.presenter.ISchedulePresenter;
import com.example.cris.studentsapp.screen.schedule.view.adapter.WeekDayAdapter;
import com.example.cris.studentsapp.screen.schedule.view.delegate.IScheduleViewDelegate;
import com.example.cris.studentsapp.utils.AlertUtils;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class ScheduleFragment extends BaseFragment implements
        IScheduleViewDelegate,
        WeekDayAdapter.OnItemDetailsClickListener {

    private ProgressBar mProgressBar;
    private TextView mTextError;
    private RecyclerView mRvWeekDays;

    //    public static List<WeekDayEntity> mWeekDays;
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

//        mWeekDays = new ArrayList<>();

        setAlarms();

        if (LocalSaving.getEventsList(getContext()) == null
                || LocalSaving.getEventsList(getContext()).isEmpty())
            mPresenter.prepareDays();

        if (LocalSaving.getTypeList(getContext()) == null
                || LocalSaving.getTypeList(getContext()).isEmpty())
            mPresenter.createTypeList();

        if (LocalSaving.getRecurrenceList(getContext()) == null
                || LocalSaving.getRecurrenceList(getContext()).isEmpty())
            mPresenter.createRecurrenceList();

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
        mTextError.setVisibility(View.GONE);
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

    private void setAlarms(){

        for (WeekDayEntity weekDay:LocalSaving.getEventsList(getContext())){
            for (DayElementEntity dayEvent:weekDay.getDayElements()){

            }
        }

        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Date dat = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY,14);
        cal_alarm.set(Calendar.MINUTE,25);
        cal_alarm.set(Calendar.SECOND,0);
        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }

        Intent myIntent = new Intent(getContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, myIntent, 0);

        manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);

        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mTextError = view.findViewById(R.id.text_error);
        mRvWeekDays = view.findViewById(R.id.rv_week_days);

        mRvWeekDays.setLayoutManager(new LinearLayoutManager(getContext()));

        mDaysAdapter = new WeekDayAdapter(getContext(),
                LocalSaving.getEventsList(getContext()),
                this);
        mRvWeekDays.setAdapter(mDaysAdapter);

        mTextError.setVisibility(View.GONE);
    }
}

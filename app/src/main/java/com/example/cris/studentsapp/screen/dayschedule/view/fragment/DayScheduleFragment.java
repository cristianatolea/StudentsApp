package com.example.cris.studentsapp.screen.dayschedule.view.fragment;

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
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;
import com.example.cris.studentsapp.screen.dayschedule.view.adapter.DayElementAdapter;
import com.example.cris.studentsapp.screen.dayschedule.view.delegate.IDayScheduleViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static com.example.cris.studentsapp.screen.schedule.view.fragment.ScheduleFragment.mWeekDays;
import static com.example.cris.studentsapp.utils.Constants.DAY_POSITION;

public class DayScheduleFragment extends BaseFragment implements
        IDayScheduleViewDelegate,
        View.OnClickListener,
        DayElementAdapter.OnItemDayElementUpdated {

    private ProgressBar mProgressBar;
    private TextView mTextNameDay;

    private List<DayElementEntity> mElementsList;
    private DayElementAdapter mDayElementsAdapter;
    private int mDayPos;

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
        return inflater.inflate(R.layout.fragment_schedule_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mElementsList = new ArrayList<>();

        mDayPos = getArguments().getInt(DAY_POSITION);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_add_element:
                DayElementEntity elementEntity = new DayElementEntity();
                mWeekDays.get(mDayPos).getDayElements().add(elementEntity);
                mDayElementsAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onNameAdded(int position, String name) {
        mWeekDays.get(mDayPos).getDayElements().get(position).setElementName(name);
    }

    @Override
    public void onRoomAdded(int position, String room) {
        mWeekDays.get(mDayPos).getDayElements().get(position).setElementRoom(room);
    }

    @Override
    public void onUpdateTime(int position, Time time) {
        mWeekDays.get(mDayPos).getDayElements().get(position).setTime(time.toString());
    }

    @Override
    public void onTypeSelected(int position, String type) {
        mWeekDays.get(mDayPos).getDayElements().get(position).setElementType(type);
    }

    @Override
    public void onRecurrenceSelected(int position, String recurrence) {
        mWeekDays.get(mDayPos).getDayElements().get(position).setRecurrence(recurrence);
    }

    @Override
    public void onSaveClicked(int position) {
        if (mWeekDays.get(mDayPos).getDayElements().get(position).checkIfEmptyFields()) {
            AlertUtils.alert(getContext(), R.string.alert_title, "empty fields");
        }
    }

    public static DayScheduleFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(DAY_POSITION, position);
        DayScheduleFragment fragment = new DayScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mTextNameDay = view.findViewById(R.id.text_day_name);
        RecyclerView rvElements = view.findViewById(R.id.rv_weekday_elements);
        LinearLayout linearAddElements = view.findViewById(R.id.linear_add_element);

        mDayElementsAdapter = new DayElementAdapter(getContext(), mWeekDays.get(mDayPos).getDayElements(), this);
        rvElements.setLayoutManager(new LinearLayoutManager(getContext()));
        rvElements.setAdapter(mDayElementsAdapter);

        mTextNameDay.setText(mWeekDays.get(mDayPos).getWeekDayName());

        linearAddElements.setOnClickListener(this);
    }
}
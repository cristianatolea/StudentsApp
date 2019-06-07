package com.example.cris.studentsapp.screen.schedule.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;

import java.util.List;

public interface IScheduleViewDelegate extends IBaseViewDelegate {

    void onGetNamedDaysSuccess(List<WeekDayEntity> list);

    void onGetNamedDaysFailed();
}

package com.example.cris.studentsapp.screen.schedule.model.entity;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;

import java.util.List;

public class WeekDayEntity {

    private String mWeekDayName;
    private List<DayElementEntity> mDayElements;

    public WeekDayEntity(String weekDayName, List<DayElementEntity> list) {
        mWeekDayName = weekDayName;
        mDayElements = list;
    }

    public String getWeekDayName() {
        return mWeekDayName;
    }

    public void setWeekDayName(String mWeekDayName) {
        this.mWeekDayName = mWeekDayName;
    }

    public List<DayElementEntity> getDayElements() {
        return mDayElements;
    }

    public void setDayElements(List<DayElementEntity> mDayElements) {
        this.mDayElements = mDayElements;
    }

    public String getNumberOfEvents() {
        String result = "";
        if (mDayElements.size() > 1 || mDayElements.size() < 1) {
            result = result
                    .concat(String.valueOf(mDayElements.size()))
                    .concat(" ")
                    .concat("events");
        } else {
            result = result
                    .concat(String.valueOf(mDayElements.size()))
                    .concat(" ")
                    .concat("event");
        }
        return result;
    }
}

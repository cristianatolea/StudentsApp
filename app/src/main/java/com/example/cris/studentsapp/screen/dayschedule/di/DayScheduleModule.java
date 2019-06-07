package com.example.cris.studentsapp.screen.dayschedule.di;

import com.example.cris.studentsapp.screen.dayschedule.view.delegate.IDayScheduleViewDelegate;
import com.example.cris.studentsapp.screen.dayschedule.view.fragment.DayScheduleFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class DayScheduleModule {

    @Provides
    IDayScheduleViewDelegate provideDayScheduleDelegate(DayScheduleFragment fragment) {
        return fragment;
    }
}

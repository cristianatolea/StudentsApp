package com.example.cris.studentsapp.screen.schedule.di;

import android.content.Context;

import com.example.cris.studentsapp.screen.schedule.presenter.ISchedulePresenter;
import com.example.cris.studentsapp.screen.schedule.presenter.SchedulePresenter;
import com.example.cris.studentsapp.screen.schedule.view.delegate.IScheduleViewDelegate;
import com.example.cris.studentsapp.screen.schedule.view.fragment.ScheduleFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ScheduleModule {

    @Provides
    IScheduleViewDelegate provideScheduleViewDelegate(ScheduleFragment fragment) {
        return fragment;
    }

    @Provides
    ISchedulePresenter provideSchedulePresenter(Context context, IScheduleViewDelegate viewDelegate) {
        return new SchedulePresenter(context, viewDelegate);
    }

}

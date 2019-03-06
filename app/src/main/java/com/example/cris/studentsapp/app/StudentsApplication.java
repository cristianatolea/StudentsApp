package com.example.cris.studentsapp.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.cris.studentsapp.app.di.DaggerStudentsAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class StudentsApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        DaggerStudentsAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}

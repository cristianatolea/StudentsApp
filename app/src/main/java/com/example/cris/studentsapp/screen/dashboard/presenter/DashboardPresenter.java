package com.example.cris.studentsapp.screen.dashboard.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.dashboard.model.IDashboardModel;
import com.example.cris.studentsapp.screen.dashboard.view.delegate.IDashboardViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class DashboardPresenter implements IDashboardPresenter {

    private Context mContext;
    private IDashboardViewDelegate mViewDelegate;
    private IDashboardModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public DashboardPresenter(Context context,
                              IDashboardViewDelegate viewDelegate,
                              IDashboardModel model) {

        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

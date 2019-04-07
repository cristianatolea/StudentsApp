package com.example.cris.studentsapp.screen.notifications.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.notifications.model.INotificationsModel;
import com.example.cris.studentsapp.screen.notifications.view.delegate.INotificationsViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class NotificationsPresenter implements INotificationsPresenter {

    private Context mContext;
    private INotificationsViewDelegate mViewDelegate;
    private INotificationsModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public NotificationsPresenter(Context context,
                                  INotificationsViewDelegate viewDelegate,
                                  INotificationsModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

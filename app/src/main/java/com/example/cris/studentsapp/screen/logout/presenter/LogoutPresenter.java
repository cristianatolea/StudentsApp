package com.example.cris.studentsapp.screen.logout.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.logout.model.ILogoutModel;
import com.example.cris.studentsapp.screen.logout.view.delegate.ILogoutViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class LogoutPresenter implements ILogoutPresenter {

    private Context mContext;
    private ILogoutViewDelegate mViewDelegate;
    private ILogoutModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public LogoutPresenter(Context context,
                           ILogoutViewDelegate viewDelegate,
                           ILogoutModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

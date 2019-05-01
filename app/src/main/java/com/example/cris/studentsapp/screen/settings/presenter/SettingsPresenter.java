package com.example.cris.studentsapp.screen.settings.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.settings.model.ISettingsModel;
import com.example.cris.studentsapp.screen.settings.view.delegate.ISettingsViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsPresenter implements ISettingsPresenter {

    private Context mContext;
    private ISettingsViewDelegate mViewDelegate;
    private ISettingsModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public SettingsPresenter(Context context,
                             ISettingsViewDelegate viewDelegate,
                             ISettingsModel model) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void changePassword() {

    }
}

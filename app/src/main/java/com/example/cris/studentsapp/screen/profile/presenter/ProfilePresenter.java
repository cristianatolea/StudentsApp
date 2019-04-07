package com.example.cris.studentsapp.screen.profile.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.profile.model.IProfileModel;
import com.example.cris.studentsapp.screen.profile.view.delegate.IProfileViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class ProfilePresenter implements IProfilePresenter {

    private Context mContext;
    private IProfileViewDelegate mViewDelegate;
    private IProfileModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public ProfilePresenter(Context context,
                            IProfileViewDelegate viewDelegate,
                            IProfileModel model) {

        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

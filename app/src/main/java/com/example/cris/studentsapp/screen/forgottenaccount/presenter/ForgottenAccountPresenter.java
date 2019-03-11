package com.example.cris.studentsapp.screen.forgottenaccount.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.forgottenaccount.model.IForgottenAccountModel;
import com.example.cris.studentsapp.screen.forgottenaccount.view.delegate.IForgottenAccountViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class ForgottenAccountPresenter implements IForgottenAccountPresenter {

    private Context mContext;
    private IForgottenAccountViewDelegate mViewDelegate;
    private IForgottenAccountModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public ForgottenAccountPresenter(Context context,
                                      IForgottenAccountViewDelegate viewDelegate,
                                      IForgottenAccountModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

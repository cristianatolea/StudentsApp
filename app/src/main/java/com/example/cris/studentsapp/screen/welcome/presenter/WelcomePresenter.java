package com.example.cris.studentsapp.screen.welcome.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.welcome.model.IWelcomeModel;
import com.example.cris.studentsapp.screen.welcome.view.delegate.IWelcomeViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class WelcomePresenter implements IWelcomePresenter {

    private Context mContext;
    private IWelcomeViewDelegate mViewDelegate;
    private IWelcomeModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public WelcomePresenter(Context context,
                            IWelcomeViewDelegate viewDelegate,
                            IWelcomeModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

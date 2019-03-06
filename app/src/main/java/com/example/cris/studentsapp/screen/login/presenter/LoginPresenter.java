package com.example.cris.studentsapp.screen.login.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.login.model.ILoginModel;
import com.example.cris.studentsapp.screen.login.view.delegate.ILoginViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class LoginPresenter implements ILoginPresenter {

    private Context mContext;
    private ILoginViewDelegate mViewDelegate;
    private ILoginModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public LoginPresenter(Context context,
                          ILoginViewDelegate viewDelegate,
                          ILoginModel loginModel) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = loginModel;
        mCompositeDisposable = new CompositeDisposable();
    }
}

package com.example.cris.studentsapp.screen.help.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.help.view.delegate.IHelpViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class HelpPresenter implements IHelpPresenter {

    private Context mContext;
    private IHelpViewDelegate mViewDelegate;
    private CompositeDisposable mcompositeDisposable;

    public HelpPresenter(Context context, IHelpViewDelegate viewDelegate) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mcompositeDisposable = new CompositeDisposable();
    }

}

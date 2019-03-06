package com.example.cris.studentsapp.screen.main.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.main.model.IMainModel;
import com.example.cris.studentsapp.screen.main.view.delegate.IMainViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter implements IMainPresenter {

    private Context mContext;
    private IMainModel mModel;
    private IMainViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public MainPresenter(Context context,
                         IMainViewDelegate mainViewDelegate,
                         IMainModel mainModel) {
        mContext = context;
        mViewDelegate = mainViewDelegate;
        mModel = mainModel;
        mCompositeDisposable = new CompositeDisposable();
    }
}

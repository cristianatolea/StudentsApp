package com.example.cris.studentsapp.screen.adddiscussion.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.adddiscussion.model.IAddDiscussionModel;
import com.example.cris.studentsapp.screen.adddiscussion.view.delegate.IAddDiscussionViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class AddDiscussionPresenter implements IAddDiscussionPresenter {

    private Context mContext;
    private IAddDiscussionModel mModel;
    private IAddDiscussionViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public AddDiscussionPresenter(Context context,
                                  IAddDiscussionModel model,
                                  IAddDiscussionViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }
}

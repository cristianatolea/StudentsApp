package com.example.cris.studentsapp.screen.messages.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.messages.model.IMessagesModel;
import com.example.cris.studentsapp.screen.messages.view.delegate.IMessagesViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class MessagesPresenter implements IMessagesPresenter {

    private Context mContext;
    private IMessagesViewDelegate mViewDelegate;
    private IMessagesModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public MessagesPresenter(Context context,
                             IMessagesViewDelegate viewDelegate,
                             IMessagesModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

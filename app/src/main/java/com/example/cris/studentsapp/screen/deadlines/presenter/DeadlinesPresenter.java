package com.example.cris.studentsapp.screen.deadlines.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.deadlines.model.IDeadlinesModel;
import com.example.cris.studentsapp.screen.deadlines.view.delegate.IDeadlinesViewDelegate;

public class DeadlinesPresenter implements IDeadlinesPresenter {

    private Context mContext;
    private IDeadlinesModel mModel;
    private IDeadlinesViewDelegate mViewDelegate;

    public DeadlinesPresenter(Context context,
                              IDeadlinesModel model,
                              IDeadlinesViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
    }
}

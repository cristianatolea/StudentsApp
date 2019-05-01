package com.example.cris.studentsapp.screen.courses.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.courses.model.ICoursesModel;
import com.example.cris.studentsapp.screen.courses.view.delegate.ICoursesViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class CoursesPresenter implements ICoursesPresenter {

    private Context mContext;
    private ICoursesModel mModel;
    private ICoursesViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public CoursesPresenter(Context context,
                            ICoursesModel model,
                            ICoursesViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }
}

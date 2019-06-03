package com.example.cris.studentsapp.screen.coursedetails.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.coursedetails.model.ICourseDetailsModel;
import com.example.cris.studentsapp.screen.coursedetails.view.delegate.ICourseDetailsViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class CourseDetailsPresenter implements ICourseDetailsPresenter {

    private Context mContext;
    private ICourseDetailsModel mModel;
    private ICourseDetailsViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public CourseDetailsPresenter(Context context,
                                  ICourseDetailsModel model,
                                  ICourseDetailsViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }
}

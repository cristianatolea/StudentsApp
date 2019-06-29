package com.example.cris.studentsapp.screen.studentassignment.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.studentassignment.model.IStudentAssignmentModel;
import com.example.cris.studentsapp.screen.studentassignment.view.delegate.IStudentAssignmentViewDelegate;

import io.reactivex.disposables.CompositeDisposable;

public class StudentAssignmentPresenter implements IStudentAssignmentPresenter {

    private Context mContext;
    private IStudentAssignmentViewDelegate mViewDelegate;
    private IStudentAssignmentModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public StudentAssignmentPresenter(Context context,
                                      IStudentAssignmentViewDelegate viewDelegate,
                                      IStudentAssignmentModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }
}

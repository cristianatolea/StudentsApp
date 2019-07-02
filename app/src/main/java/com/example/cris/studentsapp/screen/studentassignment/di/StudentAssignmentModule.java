package com.example.cris.studentsapp.screen.studentassignment.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.studentassignment.model.IStudentAssignmentModel;
import com.example.cris.studentsapp.screen.studentassignment.model.StudentAssignmentModel;
import com.example.cris.studentsapp.screen.studentassignment.presenter.IStudentAssignmentPresenter;
import com.example.cris.studentsapp.screen.studentassignment.presenter.StudentAssignmentPresenter;
import com.example.cris.studentsapp.screen.studentassignment.view.delegate.IStudentAssignmentViewDelegate;
import com.example.cris.studentsapp.screen.studentassignment.view.fragment.StudentAssignmentFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentAssignmentModule {

    @Provides
    IStudentAssignmentViewDelegate provideStudentAssignmentViewDelegate(StudentAssignmentFragment fragment) {
        return fragment;
    }

    @Provides
    IStudentAssignmentModel provideStudentAssignmentModel(Context context, IApiInterface IApiInterface) {
        return new StudentAssignmentModel(context, IApiInterface);
    }

    @Provides
    IStudentAssignmentPresenter provideStudentAssignmentPresenter(Context context,
                                                                  IStudentAssignmentViewDelegate viewDelegate,
                                                                  IStudentAssignmentModel model) {
        return new StudentAssignmentPresenter(context, viewDelegate, model);
    }
}

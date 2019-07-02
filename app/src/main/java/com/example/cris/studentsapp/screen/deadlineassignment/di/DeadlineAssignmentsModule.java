package com.example.cris.studentsapp.screen.deadlineassignment.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.deadlineassignment.model.DeadlineAssignmentsModel;
import com.example.cris.studentsapp.screen.deadlineassignment.model.IDeadlineAssignmentsModel;
import com.example.cris.studentsapp.screen.deadlineassignment.presenter.DeadlineAssignmentsPresenter;
import com.example.cris.studentsapp.screen.deadlineassignment.presenter.IDeadlineAssignmentsPresenter;
import com.example.cris.studentsapp.screen.deadlineassignment.view.delegate.IDeadlineAssignmentsViewDelegate;
import com.example.cris.studentsapp.screen.deadlineassignment.view.fragment.DeadlineAssignmentsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class DeadlineAssignmentsModule {

    @Provides
    IDeadlineAssignmentsViewDelegate provideDeadlineAssignmentsViewDelegate(DeadlineAssignmentsFragment fragment) {
        return fragment;
    }

    @Provides
    IDeadlineAssignmentsModel provideDeadlineAssignmentsModel(Context context, IApiInterface IApiInterface) {
        return new DeadlineAssignmentsModel(context, IApiInterface);
    }

    @Provides
    IDeadlineAssignmentsPresenter provideDeadlineAssignmentsPresenter(Context context,
                                                                      IDeadlineAssignmentsViewDelegate viewDelegate,
                                                                      IDeadlineAssignmentsModel model) {
        return new DeadlineAssignmentsPresenter(context, viewDelegate, model);
    }
}

package com.example.cris.studentsapp.screen.deadlines.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.deadlines.model.DeadlinesModel;
import com.example.cris.studentsapp.screen.deadlines.model.IDeadlinesModel;
import com.example.cris.studentsapp.screen.deadlines.presenter.DeadlinesPresenter;
import com.example.cris.studentsapp.screen.deadlines.presenter.IDeadlinesPresenter;
import com.example.cris.studentsapp.screen.deadlines.view.delegate.IDeadlinesViewDelegate;
import com.example.cris.studentsapp.screen.deadlines.view.fragment.DeadlinesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class DeadlinesModule {

    @Provides
    IDeadlinesViewDelegate provideDeadlineViewDelegate(DeadlinesFragment fragment) {
        return fragment;
    }

    @Provides
    IDeadlinesModel provideDeadlinesModel(Context context, IApiInterface IApiInterface) {
        return new DeadlinesModel(context, IApiInterface);
    }

    @Provides
    IDeadlinesPresenter provideDeadlinesPresenter(Context context,
                                                  IDeadlinesModel model,
                                                  IDeadlinesViewDelegate viewDelegate) {
        return new DeadlinesPresenter(context, model, viewDelegate);
    }
}

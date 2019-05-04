package com.example.cris.studentsapp.screen.courses.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.courses.model.CoursesModel;
import com.example.cris.studentsapp.screen.courses.model.ICoursesModel;
import com.example.cris.studentsapp.screen.courses.presenter.CoursesPresenter;
import com.example.cris.studentsapp.screen.courses.presenter.ICoursesPresenter;
import com.example.cris.studentsapp.screen.courses.view.delegate.ICoursesViewDelegate;
import com.example.cris.studentsapp.screen.courses.view.fragment.CoursesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class CoursesModule {

    @Provides
    ICoursesViewDelegate provideCoursesViewDelegate(CoursesFragment fragment) {
        return fragment;
    }

    @Provides
    ICoursesModel providesCoursesModel(Context context, ApiInterface apiInterface) {
        return new CoursesModel(context, apiInterface);
    }

    @Provides
    ICoursesPresenter providesCoursesPresenter(Context context,
                                               ICoursesModel model,
                                               ICoursesViewDelegate viewDelegate) {
        return new CoursesPresenter(context, model, viewDelegate);
    }
}

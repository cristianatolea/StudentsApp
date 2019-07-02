package com.example.cris.studentsapp.screen.coursedetails.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.coursedetails.model.CourseDetailsModel;
import com.example.cris.studentsapp.screen.coursedetails.model.ICourseDetailsModel;
import com.example.cris.studentsapp.screen.coursedetails.model.storage.ILocalFileStorage;
import com.example.cris.studentsapp.screen.coursedetails.model.storage.LocalFileStorage;
import com.example.cris.studentsapp.screen.coursedetails.presenter.CourseDetailsPresenter;
import com.example.cris.studentsapp.screen.coursedetails.presenter.ICourseDetailsPresenter;
import com.example.cris.studentsapp.screen.coursedetails.view.delegate.ICourseDetailsViewDelegate;
import com.example.cris.studentsapp.screen.coursedetails.view.fragment.CourseDetailsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class CourseDetailsModule {

    @Provides
    ICourseDetailsViewDelegate provideCourseDetailsViewDelegate(CourseDetailsFragment fragment) {
        return fragment;
    }

    @Provides
    ICourseDetailsModel provideCourseDetailsModel(Context context, IApiInterface IApiInterface) {
        return new CourseDetailsModel(context, IApiInterface);
    }

    @Provides
    ILocalFileStorage provideLocalStorage(Context context) {
        return new LocalFileStorage(context);
    }

    @Provides
    ICourseDetailsPresenter provideCourseDetailsPresenter(Context context,
                                                          ICourseDetailsModel model,
                                                          ICourseDetailsViewDelegate viewDelegate,
                                                          ILocalFileStorage localFileStorage) {
        return new CourseDetailsPresenter(context, model, viewDelegate, localFileStorage);
    }
}

package com.example.cris.studentsapp.screen.forumspercourse.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.courses.model.CoursesModel;
import com.example.cris.studentsapp.screen.courses.model.ICoursesModel;
import com.example.cris.studentsapp.screen.forumspercourse.model.ForumsModel;
import com.example.cris.studentsapp.screen.forumspercourse.model.IForumsModel;
import com.example.cris.studentsapp.screen.forumspercourse.presenter.ForumsPresenter;
import com.example.cris.studentsapp.screen.forumspercourse.presenter.IForumsPresenter;
import com.example.cris.studentsapp.screen.forumspercourse.view.delegate.IForumsViewDelegate;
import com.example.cris.studentsapp.screen.forumspercourse.view.fragment.ForumsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ForumsModule {

    @Provides
    IForumsViewDelegate provideDiscussionsViewDelegate(ForumsFragment fragment) {
        return fragment;
    }

    @Provides
    IForumsModel provideDiscussionsModel(Context context, IApiInterface IApiInterface) {
        return new ForumsModel(context, IApiInterface);
    }

    @Provides
    ICoursesModel provideCoursesModel(Context context, IApiInterface IApiInterface) {
        return new CoursesModel(context, IApiInterface);
    }

    @Provides
    IForumsPresenter provideDiscussionsPresenter(Context context,
                                                 IForumsModel model,
                                                 ICoursesModel coursesModel,
                                                 IForumsViewDelegate viewDelegate) {
        return new ForumsPresenter(context, model, coursesModel, viewDelegate);
    }
}

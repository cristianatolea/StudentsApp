package com.example.cris.studentsapp.screen.adddiscussion.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.adddiscussion.model.AddDiscussionModel;
import com.example.cris.studentsapp.screen.adddiscussion.model.IAddDiscussionModel;
import com.example.cris.studentsapp.screen.adddiscussion.presenter.AddDiscussionPresenter;
import com.example.cris.studentsapp.screen.adddiscussion.presenter.IAddDiscussionPresenter;
import com.example.cris.studentsapp.screen.adddiscussion.view.delegate.IAddDiscussionViewDelegate;
import com.example.cris.studentsapp.screen.adddiscussion.view.fragment.AddDiscussionFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class AddDiscussionModule {

    @Provides
    IAddDiscussionViewDelegate provideAddViewDelegate(AddDiscussionFragment fragment) {
        return fragment;
    }

    @Provides
    IAddDiscussionModel provideAddDiscussionModel(Context context, IApiInterface IApiInterface) {
        return new AddDiscussionModel(context, IApiInterface);
    }

    @Provides
    IAddDiscussionPresenter provideAddDiscussionPresenter(Context context,
                                                          IAddDiscussionModel model,
                                                          IAddDiscussionViewDelegate viewDelegate) {
        return new AddDiscussionPresenter(context, model, viewDelegate);
    }
}

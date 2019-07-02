package com.example.cris.studentsapp.screen.discussionslistperforum.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.DiscussionsPerForumModel;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.IDiscussionsPerForumModel;
import com.example.cris.studentsapp.screen.discussionslistperforum.presenter.DiscussionsPerForumPresenter;
import com.example.cris.studentsapp.screen.discussionslistperforum.presenter.IDiscussionsPerForumPresenter;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.delegate.IDiscussionsPerForumViewDelegate;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.fragment.DiscussionsPerForumFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class DiscussionsPerForumModule {

    @Provides
    IDiscussionsPerForumViewDelegate provideDiscussionsPerForumViewDelegate(DiscussionsPerForumFragment fragment) {
        return fragment;
    }

    @Provides
    IDiscussionsPerForumModel provideDiscussionsPerForumModel(Context context, IApiInterface IApiInterface) {
        return new DiscussionsPerForumModel(context, IApiInterface);
    }

    @Provides
    IDiscussionsPerForumPresenter providesDiscussionsPerForumPresenter(Context context,
                                                                       IDiscussionsPerForumModel model,
                                                                       IDiscussionsPerForumViewDelegate viewDelegate) {
        return new DiscussionsPerForumPresenter(context, model, viewDelegate);
    }
}

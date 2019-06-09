package com.example.cris.studentsapp.screen.postsperdiscussion.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.IPostsPerDiscussionModel;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.PostsPerDiscussionModel;
import com.example.cris.studentsapp.screen.postsperdiscussion.presenter.IPostsPerDiscussionPresenter;
import com.example.cris.studentsapp.screen.postsperdiscussion.presenter.PostsPerDiscussionPresenter;
import com.example.cris.studentsapp.screen.postsperdiscussion.view.delegate.IPostsPerDiscussionViewDelegate;
import com.example.cris.studentsapp.screen.postsperdiscussion.view.fragment.PostsPerDiscussionFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class PostsPerDiscussionModule {

    @Provides
    IPostsPerDiscussionViewDelegate providePostsPerDiscussionViewDelegate(PostsPerDiscussionFragment fragment) {
        return fragment;
    }

    @Provides
    IPostsPerDiscussionModel providePostsPerDiscussionModel(Context context,
                                                            ApiInterface apiInterface) {
        return new PostsPerDiscussionModel(context, apiInterface);
    }

    @Provides
    IPostsPerDiscussionPresenter providePostsPerDiscussionPresenter(Context context,
                                                                    IPostsPerDiscussionViewDelegate viewDelegate,
                                                                    IPostsPerDiscussionModel model) {
        return new PostsPerDiscussionPresenter(context, viewDelegate, model);
    }
}

package com.example.cris.studentsapp.screen.discussionslistperforum.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionsPerForumResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class DiscussionsPerForumModel implements IDiscussionsPerForumModel {

    private Context mContext;
    private ApiInterface mApiInterface;

    public DiscussionsPerForumModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<DiscussionsPerForumResponse> getForumsDiscussions(String forumId) {
        return mApiInterface.getForumsDiscussions(
                LocalSaving.getToken(mContext),
                "mod_forum_get_forum_discussions_paginated",
                forumId);
    }
}

package com.example.cris.studentsapp.screen.discussionslistperforum.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.CanAddDiscussionResponse;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionsPerForumResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class DiscussionsPerForumModel implements IDiscussionsPerForumModel {

    private Context mContext;
    private IApiInterface mIApiInterface;

    public DiscussionsPerForumModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<DiscussionsPerForumResponse> getForumsDiscussions(String forumId) {
        return mIApiInterface.getForumsDiscussions(
                LocalSaving.getToken(mContext),
                "mod_forum_get_forum_discussions_paginated",
                forumId);
    }

    @Override
    public Observable<CanAddDiscussionResponse> canAddDiscussion(String forumId) {
        return mIApiInterface.canAddDiscussion(
                LocalSaving.getToken(mContext),
                "mod_forum_can_add_discussion",
                forumId
        );
    }
}

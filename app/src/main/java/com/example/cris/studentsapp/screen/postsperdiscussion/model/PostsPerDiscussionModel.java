package com.example.cris.studentsapp.screen.postsperdiscussion.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostsResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class PostsPerDiscussionModel implements IPostsPerDiscussionModel {

    private Context mContext;
    private ApiInterface mApiInterface;

    public PostsPerDiscussionModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<PostsResponse> getDiscussionsPosts(String discussionId) {
        return mApiInterface.getDiscussionsPosts(
                LocalSaving.getToken(mContext),
                "mod_forum_get_forum_discussion_posts",
                discussionId);
    }
}

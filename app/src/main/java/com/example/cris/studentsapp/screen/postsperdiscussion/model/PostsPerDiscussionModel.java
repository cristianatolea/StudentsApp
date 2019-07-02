package com.example.cris.studentsapp.screen.postsperdiscussion.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.NewPostResponse;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostsResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class PostsPerDiscussionModel implements IPostsPerDiscussionModel {

    private Context mContext;
    private IApiInterface mIApiInterface;

    public PostsPerDiscussionModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<PostsResponse> getDiscussionsPosts(String discussionId) {
        return mIApiInterface.getDiscussionsPosts(
                LocalSaving.getToken(mContext),
                "mod_forum_get_forum_discussion_posts",
                discussionId);
    }

    @Override
    public Observable<NewPostResponse> addNewPost(String postId,
                                                  String subject,
                                                  String message) {
        return mIApiInterface.addNewPost(
                LocalSaving.getToken(mContext),
                "mod_forum_add_discussion_post",
                postId,
                subject,
                message
        );
    }
}

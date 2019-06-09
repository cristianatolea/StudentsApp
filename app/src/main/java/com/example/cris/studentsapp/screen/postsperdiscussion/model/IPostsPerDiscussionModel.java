package com.example.cris.studentsapp.screen.postsperdiscussion.model;

import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostsResponse;

import io.reactivex.Observable;

public interface IPostsPerDiscussionModel {

    Observable<PostsResponse> getDiscussionsPosts(String discussionId);
}

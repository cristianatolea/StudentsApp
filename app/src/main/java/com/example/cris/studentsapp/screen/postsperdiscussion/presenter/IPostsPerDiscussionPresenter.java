package com.example.cris.studentsapp.screen.postsperdiscussion.presenter;

import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostEntity;

import java.util.List;

public interface IPostsPerDiscussionPresenter {

    void getPosts(String discussionId);

    void addPost(String postId, String subject, String message);
}

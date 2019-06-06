package com.example.cris.studentsapp.screen.discussionslistperforum.model;

import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionsPerForumResponse;

import io.reactivex.Observable;

public interface IDiscussionsPerForumModel {
    Observable<DiscussionsPerForumResponse> getForumsDiscussions(String forumId);
}

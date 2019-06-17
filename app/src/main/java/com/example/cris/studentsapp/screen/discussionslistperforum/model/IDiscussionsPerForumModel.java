package com.example.cris.studentsapp.screen.discussionslistperforum.model;

import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.CanAddDiscussionResponse;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionsPerForumResponse;

import io.reactivex.Observable;

public interface IDiscussionsPerForumModel {
    Observable<DiscussionsPerForumResponse> getForumsDiscussions(String forumId);

    Observable<CanAddDiscussionResponse> canAddDiscussion(String forumId);
}

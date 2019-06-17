package com.example.cris.studentsapp.screen.discussionslistperforum.presenter;

public interface IDiscussionsPerForumPresenter {

    void getForumsDiscussions(String forumId);

    void checkPermissionToAddDiscussion(String forumId);
}

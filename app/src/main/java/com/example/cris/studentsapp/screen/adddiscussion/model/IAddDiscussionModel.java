package com.example.cris.studentsapp.screen.adddiscussion.model;

import com.example.cris.studentsapp.screen.adddiscussion.model.entity.AddNewDiscussionResponse;

import io.reactivex.Observable;

public interface IAddDiscussionModel {

    Observable<AddNewDiscussionResponse> addNewDiscussion(String forumId,
                                                          String subject,
                                                          String message);
}

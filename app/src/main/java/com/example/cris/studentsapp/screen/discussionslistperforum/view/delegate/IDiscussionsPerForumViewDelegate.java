package com.example.cris.studentsapp.screen.discussionslistperforum.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionEntity;

import java.util.List;

public interface IDiscussionsPerForumViewDelegate extends IBaseViewDelegate {

    void onGetDiscussionsSuccess(List<DiscussionEntity> list);

    void onGetPermissionGrantedToAddDiscussion();

    void onGetPermissionDeniedToAddDiscussion();
}

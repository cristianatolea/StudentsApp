package com.example.cris.studentsapp.screen.postsperdiscussion.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostEntity;

import java.util.List;

public interface IPostsPerDiscussionViewDelegate extends IBaseViewDelegate {

    void onGetPostsSuccess(List<PostEntity> list);

    void onPostAddedSuccessfully();
}

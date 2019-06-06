package com.example.cris.studentsapp.screen.discussionslistperforum.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscussionsPerForumResponse {
    @SerializedName("discussions")
    private List<DiscussionEntity> mDiscussionsList;

    public DiscussionsPerForumResponse(List<DiscussionEntity> discussionEntities) {
        mDiscussionsList = discussionEntities;
    }

    public List<DiscussionEntity> getDiscussionsList() {
        return mDiscussionsList;
    }
}

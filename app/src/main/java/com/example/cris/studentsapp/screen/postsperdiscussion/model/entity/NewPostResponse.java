package com.example.cris.studentsapp.screen.postsperdiscussion.model.entity;

import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.WarningEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewPostResponse {

    @SerializedName("postid")
    private String mPostId;
    @SerializedName("warnings")
    private List<WarningEntity> mWarnings;


    public NewPostResponse(String postId, List<WarningEntity> warningEntities) {
        mPostId = postId;
        mWarnings = warningEntities;
    }

    public String getPostId() {
        return mPostId;
    }

    public List<WarningEntity> getWarnings() {
        return mWarnings;
    }
}

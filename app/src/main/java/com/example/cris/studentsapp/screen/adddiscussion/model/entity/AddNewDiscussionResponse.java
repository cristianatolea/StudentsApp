package com.example.cris.studentsapp.screen.adddiscussion.model.entity;

import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.WarningEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddNewDiscussionResponse {
    @SerializedName("discussionid")
    private String mDiscussionId;
    @SerializedName("warnings")
    private List<WarningEntity> mWarnings;

    public AddNewDiscussionResponse(String discussionId, List<WarningEntity> warnings) {
        mDiscussionId = discussionId;
        mWarnings = warnings;
    }

    public String getDiscussionId() {
        return mDiscussionId;
    }

    public List<WarningEntity> getWarnings() {
        return mWarnings;
    }
}

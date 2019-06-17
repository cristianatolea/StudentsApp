package com.example.cris.studentsapp.screen.discussionslistperforum.model.entity;

import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.WarningEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CanAddDiscussionResponse {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("canpindiscussions")
    private String mCanPinDiscussions;
    @SerializedName("cancreateattachment")
    private String mCanCreateAttachement;
    @SerializedName("warnings")
    private List<WarningEntity> mWarnings;

    public CanAddDiscussionResponse(String status,
                                    String canPindiscussion,
                                    String canCreateAttachement,
                                    List<WarningEntity> list) {
        mStatus = status;
        mCanCreateAttachement = canCreateAttachement;
        mCanPinDiscussions = canPindiscussion;
        mWarnings = list;
    }

    public boolean getStatus() {
        return "true".equals(mStatus);
    }

    public boolean canPinDisscussion() {
        return "true".equals(mCanPinDiscussions);
    }

    public boolean canCreateAttachements() {
        return "true".equals(mCanCreateAttachement);
    }

    public List<WarningEntity> getWarnings() {
        return mWarnings;
    }
}

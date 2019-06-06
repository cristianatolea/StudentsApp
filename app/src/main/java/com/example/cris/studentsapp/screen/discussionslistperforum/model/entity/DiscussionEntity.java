package com.example.cris.studentsapp.screen.discussionslistperforum.model.entity;

import com.google.gson.annotations.SerializedName;

public class DiscussionEntity {
    @SerializedName("id")
    private String mDiscussionId;
    @SerializedName("name")
    private String mName;
    @SerializedName("groupid")
    private String mGroupId;
    @SerializedName("subject")
    private String mSubject;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("userfullname")
    private String mUserFullname;

    public DiscussionEntity(String discussionId, String name, String groupId,
                            String subject, String message, String userFullname) {
        mDiscussionId = discussionId;
        mName = name;
        mGroupId = groupId;
        mSubject = subject;
        mMessage = message;
        mUserFullname = userFullname;
    }

    public String getDiscussionId() {
        return mDiscussionId;
    }

    public String getName() {
        return mName;
    }

    public String getGroupId() {
        return mGroupId;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getUserFullname() {
        return mUserFullname;
    }
}

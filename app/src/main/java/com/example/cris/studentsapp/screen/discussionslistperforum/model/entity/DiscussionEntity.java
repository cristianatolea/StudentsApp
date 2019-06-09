package com.example.cris.studentsapp.screen.discussionslistperforum.model.entity;

import com.google.gson.annotations.SerializedName;

public class DiscussionEntity {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("groupid")
    private String mGroupId;
    @SerializedName("discussion")
    private String mDiscussionId;
    @SerializedName("subject")
    private String mSubject;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("userfullname")
    private String mUserFullname;
    @SerializedName("numreplies")
    private String mNumberOfReplies;

    public DiscussionEntity(String id, String name, String groupId, String discussionId,
                            String subject, String message, String userFullname, String numberOfReplies) {
        mId = id;
        mName = name;
        mGroupId = groupId;
        mDiscussionId = discussionId;
        mSubject = subject;
        mMessage = message;
        mUserFullname = userFullname;
        mNumberOfReplies = numberOfReplies;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getGroupId() {
        return mGroupId;
    }

    public String getDiscussionId() {
        return mDiscussionId;
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

    public String getNumberOfReplies() {
        return mNumberOfReplies;
    }
}

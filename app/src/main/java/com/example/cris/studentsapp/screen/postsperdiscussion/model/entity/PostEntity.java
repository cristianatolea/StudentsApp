package com.example.cris.studentsapp.screen.postsperdiscussion.model.entity;

import com.google.gson.annotations.SerializedName;

public class PostEntity {

    @SerializedName("id")
    private String mPostId;
    @SerializedName("discussion")
    private String mDiscussionId;
    @SerializedName("parent")
    private String mParent;
    @SerializedName("userid")
    private String mUserId;
    @SerializedName("created")
    private String mCreatedTime;
    @SerializedName("modified")
    private String mModifiedTime;
    @SerializedName("subject")
    private String mSubject;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("canreplay")
    private String mCanReplay;
    @SerializedName("userfullname")
    private String mUserName;

    public PostEntity(String postId, String discussionId, String parent, String userId,
                      String createdTime, String modifiedTime, String subject, String message,
                      String canReplay, String username) {
        mPostId = postId;
        mDiscussionId = discussionId;
        mParent = parent;
        mUserId = userId;
        mCreatedTime = createdTime;
        mModifiedTime = modifiedTime;
        mSubject = subject;
        mMessage = message;
        mCanReplay = canReplay;
        mUserName = username;
    }

    public String getPostId() {
        return mPostId;
    }

    public String getDiscussionId() {
        return mDiscussionId;
    }

    public String getParent() {
        return mParent;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public String getModifiedTime() {
        return mModifiedTime;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getCanReplay() {
        return mCanReplay;
    }

    public String getUserName() {
        return mUserName;
    }
}

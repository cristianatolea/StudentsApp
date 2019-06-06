package com.example.cris.studentsapp.screen.forumspercourse.model.entity;

import com.google.gson.annotations.SerializedName;

public class ForumEntity {

    @SerializedName("id")
    private String mForumId;
    @SerializedName("course")
    private String mCourseId;
    @SerializedName("type")
    private String mType;
    @SerializedName("name")
    private String mForumName;
    @SerializedName("intro")
    private String mIntro;
    @SerializedName("introformat")
    private String mIntroFormat;
    @SerializedName("assesstimestart")
    private String mAssessTimeStart;
    @SerializedName("assesstimefinish")
    private String mAssessTimeFinish;

    public ForumEntity(String forumId, String courseId, String type, String forumName,
                       String intro, String introformat, String assessTimeStart, String assesTimeFinish) {
        mForumId = forumId;
        mCourseId = courseId;
        mForumName = forumName;
        mType = type;
        mIntro = intro;
        mIntroFormat = introformat;
        mAssessTimeStart = assessTimeStart;
        mAssessTimeFinish = assesTimeFinish;
    }

    public String getForumId() {
        return mForumId;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public String getType() {
        return mType;
    }

    public String getForumName() {
        return mForumName;
    }

    public String getIntro() {
        return mIntro;
    }

    public String getIntroFormat() {
        return mIntroFormat;
    }

    public String getAssessTimeStart() {
        return mAssessTimeStart;
    }

    public String getAssessTimeFinish() {
        return mAssessTimeFinish;
    }
}

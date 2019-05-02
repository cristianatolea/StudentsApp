package com.example.cris.studentsapp.screen.courses.model.entity;

import com.google.gson.annotations.SerializedName;

public class CourseEntity {

    @SerializedName("id")
    private String mId;
    @SerializedName("shortname")
    private String mShortname;
    @SerializedName("fullname")
    private String mFullname;
    @SerializedName("enrolledusercount")
    private String mEnrolledUserCount;
    @SerializedName("idnumber")
    private String mIdNumber;
    @SerializedName("visible")
    private String mVisible;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("summaryformat")
    private String mSummaryFormat;
    @SerializedName("format")
    private String mFormat;
    @SerializedName("showgrades")
    private String mShowGrade;
    @SerializedName("lang")
    private String mLang;
    @SerializedName("enablecompletion")
    private String mEnableCompletion;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("progress")
    private String mProgress;
    @SerializedName("startdate")
    private String mStartDate;
    @SerializedName("enddate")
    private String mEndDate;

    public CourseEntity(String mId, String mShortname, String mFullname, String mEnrolledUserCount,
                        String mIdNumber, String mVisible, String mSummary, String mSummaryFormat,
                        String mFormat, String mShowGrade, String mLang, String mEnableCompletion,
                        String mCategory, String mProgress, String mStartDate, String mEndDate) {
        this.mId = mId;
        this.mShortname = mShortname;
        this.mFullname = mFullname;
        this.mEnrolledUserCount = mEnrolledUserCount;
        this.mIdNumber = mIdNumber;
        this.mVisible = mVisible;
        this.mSummary = mSummary;
        this.mSummaryFormat = mSummaryFormat;
        this.mFormat = mFormat;
        this.mShowGrade = mShowGrade;
        this.mLang = mLang;
        this.mEnableCompletion = mEnableCompletion;
        this.mCategory = mCategory;
        this.mProgress = mProgress;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
    }

    public String getId() {
        return mId;
    }

    public String getShortname() {
        return mShortname;
    }

    public String getFullname() {
        return mFullname;
    }

    public String getEnrolledUserCount() {
        return mEnrolledUserCount;
    }

    public String getIdNumber() {
        return mIdNumber;
    }

    public String getVisible() {
        return mVisible;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getSummaryFormat() {
        return mSummaryFormat;
    }

    public String getFormat() {
        return mFormat;
    }

    public String getShowGrade() {
        return mShowGrade;
    }

    public String getLang() {
        return mLang;
    }

    public String getEnableCompletion() {
        return mEnableCompletion;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getProgress() {
        return mProgress;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public String getEndDate() {
        return mEndDate;
    }
}

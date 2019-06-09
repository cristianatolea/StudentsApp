package com.example.cris.studentsapp.screen.coursedetails.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseDetailsItem {

    @SerializedName("id")
    private String mItemId;
    @SerializedName("name")
    private String mName;
    @SerializedName("visible")
    private String mVisible;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("summaryformat")
    private String mSummaryFormat;
    @SerializedName("section")
    private String mSection;
    @SerializedName("hiddenbynumsections")
    private String mHiddenByNumSections;
    @SerializedName("uservisible")
    private String mUserVisible;
    @SerializedName("modules")
    private List<CourseDetailModule> mModules;

    public CourseDetailsItem(String id, String name, String visible, String summary,
                             String summaryFormat, String section, String hiddenByNumSections,
                             String userVisible, List<CourseDetailModule> modules) {
        mItemId = id;
        mName = name;
        mVisible = visible;
        mSummary = summary;
        mSummaryFormat = summaryFormat;
        mSection = section;
        mHiddenByNumSections = hiddenByNumSections;
        mUserVisible = userVisible;
        mModules = modules;
    }

    public String getId() {
        return mItemId;
    }

    public String getName() {
        return mName;
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

    public String getSection() {
        return mSection;
    }

    public String getHiddenByNumSections() {
        return mHiddenByNumSections;
    }

    public String getUserVisible() {
        return mUserVisible;
    }

    public List<CourseDetailModule> getModules() {
        return mModules;
    }
}

package com.example.cris.studentsapp.screen.coursedetails.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseDetailModule {

    @SerializedName("id")
    private String mModuleId;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("name")
    private String mModuleName;
    @SerializedName("instance")
    private String mInstance;
    @SerializedName("visible")
    private String mVisible;
    @SerializedName("uservisible")
    private String mUserVisible;
    @SerializedName("visibleoncoursepage")
    private String mVisibleOnCoursePage;
    @SerializedName("modicon")
    private String mModuleIcon;
    @SerializedName("modname")
    private String mModShortName;
    @SerializedName("modplural")
    private String mModPlural;
    @SerializedName("indent")
    private String mIndent;
    @SerializedName("contents")
    private List<CourseDetailsContent> mContents;

    public CourseDetailModule(String moduleId, String url, String moduleName, String instance,
                              String visible, String userVisible, String visibleOnCoursePage,
                              String moduleIcon, String modShortName, String modPlural,
                              String indent, List<CourseDetailsContent> contents) {
        mModuleId = moduleId;
        mUrl = url;
        mModuleName = moduleName;
        mInstance = instance;
        mVisible = visible;
        mUserVisible = userVisible;
        mVisibleOnCoursePage = visibleOnCoursePage;
        mModuleIcon = moduleIcon;
        mModShortName = modShortName;
        mModPlural = modPlural;
        mIndent = indent;
        mContents = contents;
    }

    public String getModuleId() {
        return mModuleId;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getModuleName() {
        return mModuleName;
    }

    public String getInstance() {
        return mInstance;
    }

    public String getVisible() {
        return mVisible;
    }

    public String getUserVisible() {
        return mUserVisible;
    }

    public String getVisibleOnCoursePage() {
        return mVisibleOnCoursePage;
    }

    public String getModuleIcon() {
        return mModuleIcon;
    }

    public String getModShortName() {
        return mModShortName;
    }

    public String getModPlural() {
        return mModPlural;
    }

    public String getIndent() {
        return mIndent;
    }

    public List<CourseDetailsContent> getContents() {
        return mContents;
    }
}

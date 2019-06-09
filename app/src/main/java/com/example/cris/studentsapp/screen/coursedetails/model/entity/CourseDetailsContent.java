package com.example.cris.studentsapp.screen.coursedetails.model.entity;

import com.google.gson.annotations.SerializedName;

public class CourseDetailsContent {

    @SerializedName("type")
    private String mContentType;
    @SerializedName("filename")
    private String mFilename;
    @SerializedName("filepath")
    private String mFilePath;
    @SerializedName("filesize")
    private String mFileSize;
    @SerializedName("fileurl")
    private String mFileUrl;
    @SerializedName("timecreated")
    private String mTimeCreated;
    @SerializedName("timemodified")
    private String mTimeModified;
    @SerializedName("sortorder")
    private String mSortOrder;
    @SerializedName("mimetype")
    private String mMimeType;
    @SerializedName("isexternalfile")
    private String mIsExternalFile;
    @SerializedName("UserId")
    private String mOwnerId;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("license")
    private String mLicense;

    public CourseDetailsContent() {
        mContentType = "";
        mFilename = "";
        mFilePath = "";
        mFileSize = "";
        mFileUrl = "";
        mTimeCreated = "";
        mTimeModified = "";
        mSortOrder = "";
        mMimeType = "";
        mIsExternalFile = "";
        mOwnerId = "";
        mAuthor = "";
        mLicense = "";
    }

    public CourseDetailsContent(String contentType, String filename, String filePath, String fileSize,
                                String fileUrl, String timeCreated, String timeModified, String sortOrder,
                                String mimeType, String isExternalFile, String ownerId, String author,
                                String license) {
        mContentType = contentType;
        mFilename = filename;
        mFilePath = filePath;
        mFileSize = fileSize;
        mFileUrl = fileUrl;
        mTimeCreated = timeCreated;
        mTimeModified = timeModified;
        mSortOrder = sortOrder;
        mMimeType = mimeType;
        mIsExternalFile = isExternalFile;
        mOwnerId = ownerId;
        mAuthor = author;
        mLicense = license;
    }

    public String getContentType() {
        return mContentType;
    }

    public String getFilename() {
        return mFilename;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public String getFileSize() {
        return mFileSize;
    }

    public String getFileUrl() {
        return mFileUrl;
    }

    public String getTimeCreated() {
        return mTimeCreated;
    }

    public String getTimeModified() {
        return mTimeModified;
    }

    public String getSortOrder() {
        return mSortOrder;
    }

    public String getMimeType() {
        return mMimeType;
    }

    public String getIsExternalFile() {
        return mIsExternalFile;
    }

    public String getOwnerId() {
        return mOwnerId;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getLicense() {
        return mLicense;
    }
}

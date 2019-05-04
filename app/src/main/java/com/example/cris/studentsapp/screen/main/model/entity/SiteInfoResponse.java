package com.example.cris.studentsapp.screen.main.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SiteInfoResponse {

    @SerializedName("sitename")
    private String mSiteName;
    @SerializedName("username")
    private String mUserName;
    @SerializedName("firstname")
    private String mFirstName;
    @SerializedName("lastname")
    private String mLastName;
    @SerializedName("fullname")
    private String mFullName;
    @SerializedName("lang")
    private String mLanguage;
    @SerializedName("userid")
    private String mUserId;
    @SerializedName("siteurl")
    private String mSiteUrl;
    @SerializedName("userpictureurl")
    private String mUserPictureUrl;
    @SerializedName("functions")
    private List<FunctionEntity> mFunctions;
    @SerializedName("downloadfiles")
    private String mDownloadedFilesCounter;
    @SerializedName("uploadfiles")
    private String mUploadedFilesCounter;
    @SerializedName("release")
    private String mRelease;
    @SerializedName("version")
    private String mVersion;
    @SerializedName("mobilecssurl")
    private String mMobileCssUrl;
    @SerializedName("advancedfeatures")
    private List<AdvancedFeatureEntity> mAdvancedFeatures;
    @SerializedName("usercanmanageownfiles")
    private String mUserCanManageOwnFiles;
    @SerializedName("userquota")
    private String mUserQuota;
    @SerializedName("usermaxuploadfilesize")
    private String mUserMaxUploadFileSize;
    @SerializedName("userhomepage")
    private String mUserHomePage;
    @SerializedName("siteid")
    private String mSiteId;
    @SerializedName("sitecalendartype")
    private String mSiteCalendarType;
    @SerializedName("usercalendartype")
    private String mUserCalendarType;

    public SiteInfoResponse(String siteName, String userName, String firstName, String lastName,
                            String fullName, String language, String userId, String siteUrl,
                            String userPictureUrl, List<FunctionEntity> functions,
                            String downloadedFilesCounter, String uploadedFilesCounter,
                            String release, String version, String mobileCssUrl,
                            List<AdvancedFeatureEntity> advancedFeatures,
                            String userCanManageOwnFiles, String userQuota,
                            String userMaxUploadFileSize, String userHomePage, String siteId,
                            String siteCalendarType, String userCalendarType) {
        mSiteName = siteName;
        mUserName = userName;
        mFirstName = firstName;
        mLastName = lastName;
        mFullName = fullName;
        mLanguage = language;
        mUserId = userId;
        mSiteUrl = siteUrl;
        mUserPictureUrl = userPictureUrl;
        mFunctions = functions;
        mDownloadedFilesCounter = downloadedFilesCounter;
        mUploadedFilesCounter = uploadedFilesCounter;
        mRelease = release;
        mVersion = version;
        mMobileCssUrl = mobileCssUrl;
        mAdvancedFeatures = advancedFeatures;
        mUserCanManageOwnFiles = userCanManageOwnFiles;
        mUserQuota = userQuota;
        mUserMaxUploadFileSize = userMaxUploadFileSize;
        mUserHomePage = userHomePage;
        mSiteId = siteId;
        mSiteCalendarType = siteCalendarType;
        mUserCalendarType = userCalendarType;
    }

    public String getSiteName() {
        return mSiteName;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getSiteUrl() {
        return mSiteUrl;
    }

    public String getUserPictureUrl() {
        return mUserPictureUrl;
    }

    public List<FunctionEntity> getFunctions() {
        return mFunctions;
    }

    public String getDownloadedFilesCounter() {
        return mDownloadedFilesCounter;
    }

    public String getUploadedFilesCounter() {
        return mUploadedFilesCounter;
    }

    public String getRelease() {
        return mRelease;
    }

    public String getVersion() {
        return mVersion;
    }

    public String getMobileCssUrl() {
        return mMobileCssUrl;
    }

    public List<AdvancedFeatureEntity> getAdvancedFeatures() {
        return mAdvancedFeatures;
    }

    public String getUserCanManageOwnFiles() {
        return mUserCanManageOwnFiles;
    }

    public String getUserQuota() {
        return mUserQuota;
    }

    public String getUserMaxUploadFileSize() {
        return mUserMaxUploadFileSize;
    }

    public String getUserHomePage() {
        return mUserHomePage;
    }

    public String getSiteId() {
        return mSiteId;
    }

    public String getSiteCalendarType() {
        return mSiteCalendarType;
    }

    public String getUserCalendarType() {
        return mUserCalendarType;
    }
}

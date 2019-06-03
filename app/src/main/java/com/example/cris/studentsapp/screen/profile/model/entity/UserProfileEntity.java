package com.example.cris.studentsapp.screen.profile.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserProfileEntity {

    @SerializedName("id")
    private String mId;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("fullname")
    private String mFullname;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("department")
    private String mDepartment;
    @SerializedName("idnumber")
    private String mIdNumber;
    @SerializedName("firstaccess")
    private String mFirstAccess;
    @SerializedName("lastaccess")
    private String mLastAccess;
    @SerializedName("auth")
    private String mAuth;
    @SerializedName("suspended")
    private String mSuspended;
    @SerializedName("confirmed")
    private String mConfirmed;
    @SerializedName("city")
    private String mCity;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("profileimageurlsmall")
    private String mProfileImageUrlSmall;
    @SerializedName("profileimageurl")
    private String mProfileImageUrl;
    @SerializedName("customfields")
    private List<ProfileCustomField> mCustomFields;

    public UserProfileEntity(String mId, String mUsername, String mFullname, String mEmail,
                             String mDepartment, String mIdNumber, String mFirstAccess,
                             String mLastAccess, String mAuth, String mSuspended, String mConfirmed,
                             String mCity, String mCountry, String profileImageUrlSmall,
                             String profileImageUrl, List<ProfileCustomField> mCustomFields) {
        this.mId = mId;
        this.mUsername = mUsername;
        this.mFullname = mFullname;
        this.mEmail = mEmail;
        this.mDepartment = mDepartment;
        this.mIdNumber = mIdNumber;
        this.mFirstAccess = mFirstAccess;
        this.mLastAccess = mLastAccess;
        this.mAuth = mAuth;
        this.mSuspended = mSuspended;
        this.mConfirmed = mConfirmed;
        this.mCity = mCity;
        this.mCountry = mCountry;
        mProfileImageUrl = profileImageUrl;
        mProfileImageUrlSmall = profileImageUrlSmall;
        this.mCustomFields = mCustomFields;
    }

    public String getId() {
        return mId;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getFullname() {
        return mFullname;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public String getIdNumber() {
        return mIdNumber;
    }

    public String getFirstAccess() {
        return mFirstAccess;
    }

    public String getLastAccess() {
        return mLastAccess;
    }

    public String getAuth() {
        return mAuth;
    }

    public String getSuspended() {
        return mSuspended;
    }

    public String getConfirmed() {
        return mConfirmed;
    }

    public String getCity() {
        return mCity;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getProfileImageUrlSmall() {
        return mProfileImageUrlSmall;
    }

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public List<ProfileCustomField> getCustomFields() {
        return mCustomFields;
    }
}

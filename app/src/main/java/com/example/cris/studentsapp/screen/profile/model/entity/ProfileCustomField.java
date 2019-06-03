package com.example.cris.studentsapp.screen.profile.model.entity;

import com.google.gson.annotations.SerializedName;

public class ProfileCustomField {

    @SerializedName("type")
    private String mType;
    @SerializedName("value")
    private String mValue;
    @SerializedName("name")
    private String mName;
    @SerializedName("shortname")
    private String mShortname;

    public ProfileCustomField(String mType, String mValue, String mName, String mShortname) {
        this.mType = mType;
        this.mValue = mValue;
        this.mName = mName;
        this.mShortname = mShortname;
    }

    public String getType() {
        return mType;
    }

    public String getValue() {
        return mValue;
    }

    public String getName() {
        return mName;
    }

    public String getShortname() {
        return mShortname;
    }
}

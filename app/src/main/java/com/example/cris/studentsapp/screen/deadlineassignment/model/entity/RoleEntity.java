package com.example.cris.studentsapp.screen.deadlineassignment.model.entity;

import com.google.gson.annotations.SerializedName;

public class RoleEntity {

    @SerializedName("roleid")
    private String mRoleId;
    @SerializedName("name")
    private String mName;
    @SerializedName("shortname")
    private String mShortname;
    @SerializedName("sortorder")
    private String mSortOrder;

    public RoleEntity(String id, String name, String shortname, String sortOrder) {
        mRoleId = id;
        mName = name;
        mShortname = shortname;
        mSortOrder = sortOrder;
    }

    public String getRoleId() {
        return mRoleId;
    }

    public String getName() {
        return mName;
    }

    public String getShortname() {
        return mShortname;
    }

    public String getSortOrder() {
        return mSortOrder;
    }
}

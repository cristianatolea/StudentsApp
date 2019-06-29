package com.example.cris.studentsapp.screen.deadlineassignment.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnrolledUserEntity {

    @SerializedName("id")
    private String mUserId;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("firstname")
    private String mFirstname;
    @SerializedName("lastname")
    private String mLastname;
    @SerializedName("fullname")
    private String mFullname;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("department")
    private String mDepartment;
    @SerializedName("roles")
    private List<RoleEntity> mRoles;

    public EnrolledUserEntity(String userId, String username, String firstname, String lastname,
                              String fullname, String email, String address, String department,
                              List<RoleEntity> roles) {
        mUserId = userId;
        mUsername = username;
        mFirstname = firstname;
        mLastname = lastname;
        mFullname = fullname;
        mEmail = email;
        mAddress = address;
        mDepartment = department;
        mRoles = roles;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public String getFullname() {
        return mFullname;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public List<RoleEntity> getRoles() {
        return mRoles;
    }
}

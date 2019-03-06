package com.example.cris.studentsapp.screen.login.model.entity;

import com.google.gson.annotations.SerializedName;

public class LoginRequestEntity {

    @SerializedName("username")
    private String mUsername;
    @SerializedName("passowrd")
    private String mPassword;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}

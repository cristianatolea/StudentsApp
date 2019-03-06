package com.example.cris.studentsapp.screen.login.model.entity;

import com.google.gson.annotations.SerializedName;

public class LoginResponseEntity {

    @SerializedName("token")
    private String mToken;
    @SerializedName("privatetoken")
    private String mPrivateToken;

    public String getToken() {
        return mToken;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }

    public String getPrivateToken() {
        return mPrivateToken;
    }

    public void setPrivateToken(String mPrivateToken) {
        this.mPrivateToken = mPrivateToken;
    }
}

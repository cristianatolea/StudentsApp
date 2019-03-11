package com.example.cris.studentsapp.apiprovider.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("exception")
    private String mException;
    @SerializedName("errorcode")
    private String mErrorCode;
    @SerializedName("message")
    private String mErrorMessage;

    public String getException() {
        return mException;
    }

    public void setException(String mException) {
        this.mException = mException;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }
}

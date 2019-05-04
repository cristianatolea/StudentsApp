package com.example.cris.studentsapp.screen.main.model.entity;

import com.google.gson.annotations.SerializedName;

public class FunctionEntity {

    @SerializedName("name")
    private String mName;
    @SerializedName("version")
    private String mVersion;

    public FunctionEntity(String name, String version) {
        mName = name;
        mVersion = version;
    }

    public String getName() {
        return mName;
    }

    public String getVersion() {
        return mVersion;
    }
}

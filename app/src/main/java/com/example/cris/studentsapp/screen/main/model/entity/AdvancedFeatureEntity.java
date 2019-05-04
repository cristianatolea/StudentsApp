package com.example.cris.studentsapp.screen.main.model.entity;

import com.google.gson.annotations.SerializedName;

public class AdvancedFeatureEntity {

    @SerializedName("name")
    private String mName;
    @SerializedName("value")
    private String mValue;

    public AdvancedFeatureEntity(String name, String value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public String getValue() {
        return mValue;
    }
}

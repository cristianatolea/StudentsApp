package com.example.cris.studentsapp.screen.deadlines.model.entity;

import com.google.gson.annotations.SerializedName;

public class EventEntity {

    @SerializedName("id")
    private String mEventId;
    @SerializedName("name")
    private String mEventName;
    @SerializedName("description")
    private String mDescription;
    //todo add more info
}

package com.example.cris.studentsapp.screen.deadlines.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsResponse {

    @SerializedName("events")
    private List<EventEntity> mEventsList;

    public EventsResponse(List<EventEntity> eventsList) {
        mEventsList = eventsList;
    }

    public List<EventEntity> getEventsList() {
        return mEventsList;
    }
}

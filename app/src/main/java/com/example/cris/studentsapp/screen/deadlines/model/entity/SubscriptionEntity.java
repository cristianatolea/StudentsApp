package com.example.cris.studentsapp.screen.deadlines.model.entity;

import com.google.gson.annotations.SerializedName;

public class SubscriptionEntity {

    @SerializedName("displayeventsource")
    private String mDisplayEventSource;
    @SerializedName("subscriptionname")
    private String mSubscriptionName;
    @SerializedName("subscriptionurl")
    private String mSubscriptionUrl;

    public SubscriptionEntity(String displayEventSource,
                              String subscriptionName,
                              String subscriptionUrl) {
        mDisplayEventSource = displayEventSource;
        mSubscriptionName = subscriptionName;
        mSubscriptionUrl = subscriptionUrl;
    }

    public String getDisplayEventSource() {
        return mDisplayEventSource;
    }

    public String getSubscriptionName() {
        return mSubscriptionName;
    }

    public String getSubscriptionUrl() {
        return mSubscriptionUrl;
    }
}

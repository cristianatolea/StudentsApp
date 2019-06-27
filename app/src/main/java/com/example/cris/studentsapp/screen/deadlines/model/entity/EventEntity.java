package com.example.cris.studentsapp.screen.deadlines.model.entity;

import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventEntity {

    @SerializedName("id")
    private String mEventId;
    @SerializedName("name")
    private String mEventName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("timestart")
    private String mTimeStart;
    @SerializedName("timeduration")
    private String mTimeDuration;
    @SerializedName("userid")
    private String mUserId;
    @SerializedName("modulename")
    private String mModuleName;
    @SerializedName("course")
    private CourseEntity mCourse;
    @SerializedName("subscription")
    private SubscriptionEntity mSubscriptionEntity;
    @SerializedName("canedit")
    private String mCanEdit;


    public EventEntity(String eventId, String eventName, String description, String timeStart,
                       String timeDuration, String userId, String moduleName, CourseEntity course,
                       SubscriptionEntity subscriptionEntity, String canEdit) {
        mEventId = eventId;
        mEventName = eventName;
        mDescription = description;
        mTimeStart = timeStart;
        mTimeDuration = timeDuration;
        mUserId = userId;
        mModuleName = moduleName;
        mCourse = course;
        mSubscriptionEntity = subscriptionEntity;
        mCanEdit = canEdit;
    }

    public String getEventId() {
        return mEventId;
    }

    public String getEventName() {
        return mEventName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getTimeStart() {
        return mTimeStart;
    }

    public String getTimeDuration() {
        return mTimeDuration;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getModuleName() {
        return mModuleName;
    }

    public CourseEntity getCourse() {
        return mCourse;
    }

    public SubscriptionEntity getSubscriptionEntity() {
        return mSubscriptionEntity;
    }

    public boolean canEdit() {
        return "true".equals(mCanEdit);
    }

    public String getDateStart() {
        Date date = new Date(Long.valueOf(mTimeStart)*1000);
        return new SimpleDateFormat("dd-mm yyyy").format(date);
    }
}

package com.example.cris.studentsapp.screen.postsperdiscussion.model.entity;

import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;
import com.google.gson.annotations.SerializedName;

public class WarningEntity {

    @SerializedName("item")
    private String mItem;
    @SerializedName("itemid")
    private String mItemId;
    @SerializedName("warningcode")
    private String mWarningCode;
    @SerializedName("message")
    private String mMessage;

    public WarningEntity(String item, String itemId, String warningCode, String message) {
        mItem = item;
        mItemId = itemId;
        mWarningCode = warningCode;
        mMessage = message;
    }

    public String getItem() {
        return mItem;
    }

    public String getItemId() {
        return mItemId;
    }

    public String getWarningCode() {
        return mWarningCode;
    }

    public String getMessage() {
        return mMessage;
    }
}

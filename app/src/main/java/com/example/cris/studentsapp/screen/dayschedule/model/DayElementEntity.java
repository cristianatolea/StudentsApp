package com.example.cris.studentsapp.screen.dayschedule.model;

public class DayElementEntity {

    private String mElementName;
    private String mElementType;
    private String mElementRoom;
    private String mTime;
    private String mRecurrence;

    public DayElementEntity() {
        mElementName = "";
        mElementRoom = "";
        mElementType = "";
        mTime = "";
        mRecurrence = "";
    }

    public DayElementEntity(String elementName, String elementType, String elementRoom,
                            String time, String recurrence) {
        mElementName = elementName;
        mElementType = elementType;
        mElementRoom = elementRoom;
        mTime = time;
        mRecurrence = recurrence;
    }

    public String getElementName() {
        return mElementName;
    }

    public void setElementName(String mElementName) {
        this.mElementName = mElementName;
    }

    public String getElementType() {
        return mElementType;
    }

    public void setElementType(String mElementType) {
        this.mElementType = mElementType;
    }

    public String getElementRoom() {
        return mElementRoom;
    }

    public void setElementRoom(String mElementRoom) {
        this.mElementRoom = mElementRoom;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getRecurrence() {
        return mRecurrence;
    }

    public void setRecurrence(String mRecurrence) {
        this.mRecurrence = mRecurrence;
    }

    public boolean checkIfEmptyFields(){
        if (!("".equals(this.mElementName) || "".equals(this.mElementRoom) || "".equals(this.mElementType)
        || "".equals(this.mTime) || "".equals(this.mRecurrence))) {
            return true;
        }
        return false;
    }
}

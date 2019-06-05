package com.example.cris.studentsapp.screen.coursedetails.model.entity;

import java.util.List;

public class CourseDetailsResponse {

    private List<CourseDetailsItem> mItems;

    public CourseDetailsResponse(List<CourseDetailsItem> list) {
        mItems = list;
    }

    public List<CourseDetailsItem> getItems() {
        return mItems;
    }
}

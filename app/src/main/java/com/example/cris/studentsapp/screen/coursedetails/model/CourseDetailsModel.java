package com.example.cris.studentsapp.screen.coursedetails.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class CourseDetailsModel implements ICourseDetailsModel {

    private Context mContext;
    private ApiInterface mApiInterface;

    public CourseDetailsModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }
}

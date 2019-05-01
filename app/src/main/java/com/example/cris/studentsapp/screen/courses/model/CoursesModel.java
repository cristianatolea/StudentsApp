package com.example.cris.studentsapp.screen.courses.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class CoursesModel implements ICoursesModel {

    private ApiInterface mApiInterface;

    public CoursesModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}

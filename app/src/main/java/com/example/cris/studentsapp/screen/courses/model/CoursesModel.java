package com.example.cris.studentsapp.screen.courses.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;

import java.util.List;

import io.reactivex.Observable;

public class CoursesModel implements ICoursesModel {

    private ApiInterface mApiInterface;

    public CoursesModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<List<CourseEntity>> getUserCourses(String userToken,
                                                         String userId) {
        return mApiInterface.getUserCourses(userToken, "core_enrol_get_users_courses", userId);
    }
}

package com.example.cris.studentsapp.screen.courses.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.Observable;

public class CoursesModel implements ICoursesModel {

    private ApiInterface mApiInterface;
    private Context mContext;

    public CoursesModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<List<CourseEntity>> getUserCourses() {
        return mApiInterface.getUserCourses(
                LocalSaving.getToken(mContext),
                "core_enrol_get_users_courses",
                LocalSaving.getUserId(mContext));
    }
}

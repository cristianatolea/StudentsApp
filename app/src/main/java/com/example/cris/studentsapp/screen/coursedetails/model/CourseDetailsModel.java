package com.example.cris.studentsapp.screen.coursedetails.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.Observable;

public class CourseDetailsModel implements ICourseDetailsModel {

    private Context mContext;
    private ApiInterface mApiInterface;

    public CourseDetailsModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<List<CourseDetailsItem>> getCourseDetails(String courseId) {
        return mApiInterface.getCourseDetails(
                LocalSaving.getToken(mContext),
                "core_course_get_contents",
                courseId
        );
    }
}

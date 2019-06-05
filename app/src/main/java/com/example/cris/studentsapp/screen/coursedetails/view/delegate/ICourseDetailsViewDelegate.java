package com.example.cris.studentsapp.screen.coursedetails.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsResponse;

import java.util.List;

public interface ICourseDetailsViewDelegate extends IBaseViewDelegate {

    void onGetCourseDetailsSuccess(List<CourseDetailsItem> courseDetailsResponse);
}

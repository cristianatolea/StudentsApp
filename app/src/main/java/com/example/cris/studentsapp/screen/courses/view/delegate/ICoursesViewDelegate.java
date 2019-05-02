package com.example.cris.studentsapp.screen.courses.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;

import java.util.List;

public interface ICoursesViewDelegate extends IBaseViewDelegate {

    void onGetCoursesSuccess(List<CourseEntity> list);
}

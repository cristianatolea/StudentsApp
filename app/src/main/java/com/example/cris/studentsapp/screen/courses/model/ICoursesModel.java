package com.example.cris.studentsapp.screen.courses.model;

import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;

import java.util.List;

import io.reactivex.Observable;

public interface ICoursesModel {

    Observable<List<CourseEntity>> getUserCourses(String userToken,
                                                  String userId);
}

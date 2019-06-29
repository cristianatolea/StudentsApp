package com.example.cris.studentsapp.screen.deadlineassignment.model;

import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.EnrolledUserEntity;

import java.util.List;

import io.reactivex.Observable;

public interface IDeadlineAssignmentsModel {

    Observable<List<EnrolledUserEntity>> getCourseEnrolledUsers(String courseId);
}

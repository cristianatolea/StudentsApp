package com.example.cris.studentsapp.screen.coursedetails.model;

import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;

import java.util.List;

import io.reactivex.Observable;

public interface ICourseDetailsModel {

    Observable<List<CourseDetailsItem>> getCourseDetails(String courseId);

    Observable<LocalFile> downloadFile(LocalFile file, String fileUrl);
}

package com.example.cris.studentsapp.screen.coursedetails.presenter;

import android.app.Activity;

import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsContent;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;

public interface ICourseDetailsPresenter {

    void getCourseDetails(String courseId);

    void checkPermission(int who, int coursePosition, int position);

    void downloadFile(CourseDetailsContent content, String url);

    void openFile(Activity activity, LocalFile localFile);
}

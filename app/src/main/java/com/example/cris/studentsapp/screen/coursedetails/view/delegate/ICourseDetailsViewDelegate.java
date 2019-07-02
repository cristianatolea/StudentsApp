package com.example.cris.studentsapp.screen.coursedetails.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsResponse;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;

import java.util.List;

public interface ICourseDetailsViewDelegate extends IBaseViewDelegate {

    void onGetCourseDetailsSuccess(List<CourseDetailsItem> courseDetailsResponse);

    void onPermissionAvailable(int who, int coursePosition, int position);

    void onRequestPermissions(int who, int coursePosition, int position);

    void onDownloadSuccess(LocalFile localFile);

}

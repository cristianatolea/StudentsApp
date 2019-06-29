package com.example.cris.studentsapp.screen.deadlineassignment.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.EnrolledUserEntity;

import java.util.List;

public interface IDeadlineAssignmentsViewDelegate extends IBaseViewDelegate {

    void onGetEnrolledStudentsSuccess(List<EnrolledUserEntity> list);

    void OnGetEnrolledFailed();
}

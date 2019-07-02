package com.example.cris.studentsapp.screen.deadlineassignment.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.EnrolledUserEntity;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.Observable;

public class DeadlineAssignmentsModel implements IDeadlineAssignmentsModel {

    private IApiInterface mIApiInterface;
    private Context mContext;

    public DeadlineAssignmentsModel(Context context, IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
        mContext = context;
    }

    @Override
    public Observable<List<EnrolledUserEntity>> getCourseEnrolledUsers(String courseId) {
        return mIApiInterface.getCourseEnrolledUsers(
                LocalSaving.getToken(mContext),
                "core_enrol_get_enrolled_users",
                courseId
        );
    }
}

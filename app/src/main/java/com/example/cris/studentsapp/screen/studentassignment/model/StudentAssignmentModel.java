package com.example.cris.studentsapp.screen.studentassignment.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;

public class StudentAssignmentModel implements IStudentAssignmentModel {

    private Context mContext;
    private IApiInterface mIApiInterface;

    public StudentAssignmentModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }
}

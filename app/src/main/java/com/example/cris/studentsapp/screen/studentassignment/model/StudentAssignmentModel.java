package com.example.cris.studentsapp.screen.studentassignment.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class StudentAssignmentModel implements IStudentAssignmentModel {

    private Context mContext;
    private ApiInterface mApiInterface;

    public StudentAssignmentModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }
}

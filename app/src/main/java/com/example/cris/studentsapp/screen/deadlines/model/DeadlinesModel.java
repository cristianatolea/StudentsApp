package com.example.cris.studentsapp.screen.deadlines.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class DeadlinesModel implements IDeadlinesModel {

    private ApiInterface mApiInterface;

    public DeadlinesModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}

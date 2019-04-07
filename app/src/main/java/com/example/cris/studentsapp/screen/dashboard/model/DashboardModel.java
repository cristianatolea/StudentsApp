package com.example.cris.studentsapp.screen.dashboard.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class DashboardModel implements IDashboardModel{

    private ApiInterface mApiInterface;

    public DashboardModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}

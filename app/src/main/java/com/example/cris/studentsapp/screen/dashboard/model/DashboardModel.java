package com.example.cris.studentsapp.screen.dashboard.model;

import com.example.cris.studentsapp.apiprovider.IApiInterface;

public class DashboardModel implements IDashboardModel{

    private IApiInterface mIApiInterface;

    public DashboardModel(IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
    }
}

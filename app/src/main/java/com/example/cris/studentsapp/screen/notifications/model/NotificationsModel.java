package com.example.cris.studentsapp.screen.notifications.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class NotificationsModel implements INotificationsModel {

    private ApiInterface mApiInterface;

    public NotificationsModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}

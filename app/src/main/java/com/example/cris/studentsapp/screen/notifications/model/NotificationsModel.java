package com.example.cris.studentsapp.screen.notifications.model;

import com.example.cris.studentsapp.apiprovider.IApiInterface;

public class NotificationsModel implements INotificationsModel {

    private IApiInterface mIApiInterface;

    public NotificationsModel(IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
    }
}

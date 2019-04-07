package com.example.cris.studentsapp.screen.messages.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class MessagesModel implements IMessagesModel {

    private ApiInterface mApiInterface;

    public MessagesModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}

package com.example.cris.studentsapp.screen.adddiscussion.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class AddDiscussionModel implements IAddDiscussionModel {

    private ApiInterface mApiInterface;
    private Context mContext;

    public AddDiscussionModel(Context context,
                              ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }
}

package com.example.cris.studentsapp.screen.deadlines.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventsResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class DeadlinesModel implements IDeadlinesModel {

    private Context mContext;
    private ApiInterface mApiInterface;

    public DeadlinesModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<EventsResponse> getUpcomingDeadlines() {
        return mApiInterface.getUpcomingDeadlines(
                LocalSaving.getToken(mContext),
                "core_calendar_get_calendar_upcoming_view");
    }
}

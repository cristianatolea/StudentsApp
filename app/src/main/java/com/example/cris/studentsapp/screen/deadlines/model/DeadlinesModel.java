package com.example.cris.studentsapp.screen.deadlines.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventsResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class DeadlinesModel implements IDeadlinesModel {

    private Context mContext;
    private IApiInterface mIApiInterface;

    public DeadlinesModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<EventsResponse> getUpcomingDeadlines(String fromTime) {
        return mIApiInterface.getUpcomingDeadlines(
                LocalSaving.getToken(mContext),
                "core_calendar_get_action_events_by_timesort",
                fromTime);
    }
}

package com.example.cris.studentsapp.screen.deadlines.model;

import com.example.cris.studentsapp.screen.deadlines.model.entity.EventsResponse;

import io.reactivex.Observable;

public interface IDeadlinesModel {

    Observable<EventsResponse> getUpcomingDeadlines(String fromTime);
}

package com.example.cris.studentsapp.screen.deadlines.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventEntity;

import java.util.List;

public interface IDeadlinesViewDelegate extends IBaseViewDelegate {

    void onGetEventsSuccess(List<EventEntity> list);

    void onGetNoEvents();
}

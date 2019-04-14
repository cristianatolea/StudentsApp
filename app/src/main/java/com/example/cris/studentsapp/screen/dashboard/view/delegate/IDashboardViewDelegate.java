package com.example.cris.studentsapp.screen.dashboard.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.dashboard.model.entity.DashboardItem;

import java.util.List;

public interface IDashboardViewDelegate extends IBaseViewDelegate {

    void updateDashboardScreen(List<DashboardItem> list);
}

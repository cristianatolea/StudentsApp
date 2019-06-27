package com.example.cris.studentsapp.screen.dashboard.presenter;

import android.content.Context;
import android.view.View;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dashboard.model.entity.DashboardItem;
import com.example.cris.studentsapp.screen.dashboard.model.IDashboardModel;
import com.example.cris.studentsapp.screen.dashboard.view.delegate.IDashboardViewDelegate;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class DashboardPresenter implements IDashboardPresenter {

    private Context mContext;
    private IDashboardViewDelegate mViewDelegate;
    private IDashboardModel mModel;
    private CompositeDisposable mCompositeDisposable;
    private List<DashboardItem> mDashboardItems;

    public DashboardPresenter(Context context,
                              IDashboardViewDelegate viewDelegate,
                              IDashboardModel model) {

        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
        mDashboardItems = new ArrayList<>();
    }

    @Override
    public void populateDashboard(View view) {
        mDashboardItems.clear();
        DashboardItem item = new DashboardItem(mContext.getString(R.string.courses),
                R.drawable.ic_courses);
        mDashboardItems.add(item);

        item = new DashboardItem(mContext.getString(R.string.forums),
                R.drawable.ic_discussions);
        mDashboardItems.add(item);

        item = new DashboardItem(mContext.getString(R.string.schedule),
                R.drawable.ic_schedule);
        mDashboardItems.add(item);

        item = new DashboardItem(mContext.getString(R.string.deadlines),
                R.drawable.ic_deadlines);
        mDashboardItems.add(item);
        mViewDelegate.updateDashboardScreen(mDashboardItems);
    }
}

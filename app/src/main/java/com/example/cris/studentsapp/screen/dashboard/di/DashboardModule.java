package com.example.cris.studentsapp.screen.dashboard.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.dashboard.model.DashboardModel;
import com.example.cris.studentsapp.screen.dashboard.model.IDashboardModel;
import com.example.cris.studentsapp.screen.dashboard.presenter.DashboardPresenter;
import com.example.cris.studentsapp.screen.dashboard.presenter.IDashboardPresenter;
import com.example.cris.studentsapp.screen.dashboard.view.delegate.IDashboardViewDelegate;
import com.example.cris.studentsapp.screen.dashboard.view.fragment.DashboardFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardModule {

    @Provides
    IDashboardModel provideDashboardModel(IApiInterface IApiInterface){
        return new DashboardModel(IApiInterface);
    }

    @Provides
    IDashboardViewDelegate provideDashboardViewDelegate(DashboardFragment fragment) {
        return  fragment;
    }

    @Provides
    IDashboardPresenter provideDashboardPresenter(Context context,
                                                  IDashboardViewDelegate viewDelegate,
                                                  IDashboardModel model) {
        return new DashboardPresenter(context, viewDelegate, model);
    }
}

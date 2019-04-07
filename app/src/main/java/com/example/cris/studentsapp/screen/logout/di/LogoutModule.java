package com.example.cris.studentsapp.screen.logout.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.logout.model.ILogoutModel;
import com.example.cris.studentsapp.screen.logout.model.LogoutModel;
import com.example.cris.studentsapp.screen.logout.presenter.ILogoutPresenter;
import com.example.cris.studentsapp.screen.logout.presenter.LogoutPresenter;
import com.example.cris.studentsapp.screen.logout.view.activity.LogoutActivity;
import com.example.cris.studentsapp.screen.logout.view.delegate.ILogoutViewDelegate;

import dagger.Module;
import dagger.Provides;

@Module
public class LogoutModule {

    @Provides
    ILogoutViewDelegate providesLogoutViewDelegate(LogoutActivity activity) {
        return activity;
    }

    @Provides
    ILogoutModel providesLogoutModel(ApiInterface apiInterface) {
        return new LogoutModel(apiInterface);
    }

    @Provides
    ILogoutPresenter providesLogoutPresenter(Context context,
                                             ILogoutViewDelegate viewDelegate,
                                             ILogoutModel model) {
        return new LogoutPresenter(context, viewDelegate, model);
    }
}

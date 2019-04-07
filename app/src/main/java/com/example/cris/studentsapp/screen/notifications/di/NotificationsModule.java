package com.example.cris.studentsapp.screen.notifications.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.notifications.model.INotificationsModel;
import com.example.cris.studentsapp.screen.notifications.model.NotificationsModel;
import com.example.cris.studentsapp.screen.notifications.presenter.INotificationsPresenter;
import com.example.cris.studentsapp.screen.notifications.presenter.NotificationsPresenter;
import com.example.cris.studentsapp.screen.notifications.view.delegate.INotificationsViewDelegate;
import com.example.cris.studentsapp.screen.notifications.view.fragment.NotificationsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationsModule {

    @Provides
    INotificationsModel providesNotificationsModel(ApiInterface apiInterface) {
        return new NotificationsModel(apiInterface);
    }

    @Provides
    INotificationsViewDelegate providesNotificationsViewDelegate(NotificationsFragment fragment) {
        return fragment;
    }

    @Provides
    INotificationsPresenter providesNotificationsPresenter(Context context,
                                                           INotificationsViewDelegate viewDelegate,
                                                           INotificationsModel model) {
        return new NotificationsPresenter(context, viewDelegate, model);
    }
}

package com.example.cris.studentsapp.screen.welcome.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.welcome.model.IWelcomeModel;
import com.example.cris.studentsapp.screen.welcome.model.WelcomeModel;
import com.example.cris.studentsapp.screen.welcome.presenter.IWelcomePresenter;
import com.example.cris.studentsapp.screen.welcome.presenter.WelcomePresenter;
import com.example.cris.studentsapp.screen.welcome.view.activity.WelcomeActivity;
import com.example.cris.studentsapp.screen.welcome.view.delegate.IWelcomeViewDelegate;

import dagger.Module;
import dagger.Provides;

@Module
public class WelcomeModule {

    @Provides
    IWelcomeViewDelegate provideWelcomeViewDelegate(WelcomeActivity activity) {
        return activity;
    }

    @Provides
    IWelcomeModel provideWelcomeModel(IApiInterface IApiInterface) {
        return new WelcomeModel(IApiInterface);
    }

    @Provides
    IWelcomePresenter provideWelcomePresenter(Context context,
                                              IWelcomeViewDelegate viewDelegate,
                                              IWelcomeModel model) {
        return new WelcomePresenter(context, viewDelegate, model);
    }
}

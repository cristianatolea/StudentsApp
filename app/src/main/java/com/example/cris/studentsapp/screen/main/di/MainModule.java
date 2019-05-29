package com.example.cris.studentsapp.screen.main.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.main.model.IMainModel;
import com.example.cris.studentsapp.screen.main.model.MainModel;
import com.example.cris.studentsapp.screen.main.presenter.IMainPresenter;
import com.example.cris.studentsapp.screen.main.presenter.MainPresenter;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.main.view.delegate.IMainViewDelegate;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    IMainModel provideMainModel(Context context, ApiInterface apiInterface) {
        return new MainModel(context, apiInterface);
    }

    @Provides
    IMainViewDelegate provideMainViewDelegate(MainActivity activity) {
        return activity;
    }

    @Provides
    IMainPresenter provideMainPresenter(Context context,
                                        IMainViewDelegate mainViewDelegate,
                                        IMainModel mainModel) {
        return new MainPresenter(context, mainViewDelegate, mainModel);
    }
}

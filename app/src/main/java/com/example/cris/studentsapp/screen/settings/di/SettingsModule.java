package com.example.cris.studentsapp.screen.settings.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.settings.model.ISettingsModel;
import com.example.cris.studentsapp.screen.settings.model.SettingsModel;
import com.example.cris.studentsapp.screen.settings.presenter.ISettingsPresenter;
import com.example.cris.studentsapp.screen.settings.presenter.SettingsPresenter;
import com.example.cris.studentsapp.screen.settings.view.delegate.ISettingsViewDelegate;
import com.example.cris.studentsapp.screen.settings.view.fragment.SettingsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {

    @Provides
    ISettingsModel providesSettingsModel(ApiInterface apiInterface) {
        return new SettingsModel(apiInterface);
    }

    @Provides
    ISettingsViewDelegate providesSettingsViewDelegate(SettingsFragment fragment) {
        return fragment;
    }

    @Provides
    ISettingsPresenter providesSettingsPresenter(Context context,
                                                 ISettingsViewDelegate viewDelegate,
                                                 ISettingsModel model) {
        return new SettingsPresenter(context, viewDelegate, model);
    }
}

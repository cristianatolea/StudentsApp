package com.example.cris.studentsapp.screen.help.di;

import android.content.Context;

import com.example.cris.studentsapp.screen.help.presenter.HelpPresenter;
import com.example.cris.studentsapp.screen.help.presenter.IHelpPresenter;
import com.example.cris.studentsapp.screen.help.view.delegate.IHelpViewDelegate;
import com.example.cris.studentsapp.screen.help.view.fragment.HelpFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class HelpModule {

    @Provides
    IHelpViewDelegate provideHelpViewDelegate(HelpFragment fragment) {
        return fragment;
    }

    @Provides
    IHelpPresenter provideHelpPresenter(Context context, IHelpViewDelegate viewDelegate) {
        return new HelpPresenter(context, viewDelegate);
    }
}

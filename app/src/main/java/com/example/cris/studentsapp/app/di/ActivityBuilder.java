package com.example.cris.studentsapp.app.di;

import com.example.cris.studentsapp.screen.login.di.LoginModule;
import com.example.cris.studentsapp.screen.login.view.activity.LoginActivity;
import com.example.cris.studentsapp.screen.main.di.MainModule;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity buildMainActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity buildLoginActivity();
}

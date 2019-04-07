package com.example.cris.studentsapp.app.di;

import com.example.cris.studentsapp.screen.forgottenaccount.di.ForgottenAccountModule;
import com.example.cris.studentsapp.screen.forgottenaccount.view.activity.ForgottenAccountActivity;
import com.example.cris.studentsapp.screen.login.di.LoginModule;
import com.example.cris.studentsapp.screen.login.view.activity.LoginActivity;
import com.example.cris.studentsapp.screen.logout.di.LogoutModule;
import com.example.cris.studentsapp.screen.logout.view.activity.LogoutActivity;
import com.example.cris.studentsapp.screen.main.di.MainModule;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.welcome.di.WelcomeModule;
import com.example.cris.studentsapp.screen.welcome.view.activity.WelcomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity buildMainActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity buildLoginActivity();

    @ContributesAndroidInjector(modules = {ForgottenAccountModule.class})
    abstract ForgottenAccountActivity buildForgottenAccountActivity();

    @ContributesAndroidInjector(modules = {WelcomeModule.class})
    abstract WelcomeActivity buildWelcomeActivity();

    @ContributesAndroidInjector(modules = {LogoutModule.class})
    abstract LogoutActivity buildLogoutActivity();
}

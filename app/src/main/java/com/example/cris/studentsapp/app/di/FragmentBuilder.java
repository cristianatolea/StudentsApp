package com.example.cris.studentsapp.app.di;

import com.example.cris.studentsapp.screen.dashboard.di.DashboardModule;
import com.example.cris.studentsapp.screen.dashboard.view.fragment.DashboardFragment;
import com.example.cris.studentsapp.screen.messages.di.MessagesModule;
import com.example.cris.studentsapp.screen.messages.view.fragment.MessagesFragment;
import com.example.cris.studentsapp.screen.notifications.di.NotificationsModule;
import com.example.cris.studentsapp.screen.notifications.view.fragment.NotificationsFragment;
import com.example.cris.studentsapp.screen.profile.di.ProfileModule;
import com.example.cris.studentsapp.screen.profile.view.fragment.ProfileFragment;
import com.example.cris.studentsapp.screen.settings.di.SettingsModule;
import com.example.cris.studentsapp.screen.settings.view.fragment.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = {DashboardModule.class})
    abstract DashboardFragment buildDashboardFragment();

    @ContributesAndroidInjector(modules = {MessagesModule.class})
    abstract MessagesFragment buildMessagesFragment();

    @ContributesAndroidInjector(modules = {NotificationsModule.class})
    abstract NotificationsFragment buildNotificationsFragment();

    @ContributesAndroidInjector(modules = {SettingsModule.class})
    abstract SettingsFragment buildSettingsFragment();

    @ContributesAndroidInjector(modules = {ProfileModule.class})
    abstract ProfileFragment buildProfileFragment();
}

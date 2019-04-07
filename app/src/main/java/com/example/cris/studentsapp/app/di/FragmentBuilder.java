package com.example.cris.studentsapp.app.di;

import com.example.cris.studentsapp.screen.dashboard.di.DashboardModule;
import com.example.cris.studentsapp.screen.dashboard.view.fragment.DashboardFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = {DashboardModule.class})
    abstract DashboardFragment buildDashboardFragment();
}

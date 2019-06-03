package com.example.cris.studentsapp.screen.profile.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.profile.model.IProfileModel;
import com.example.cris.studentsapp.screen.profile.model.ProfileModel;
import com.example.cris.studentsapp.screen.profile.presenter.IProfilePresenter;
import com.example.cris.studentsapp.screen.profile.presenter.ProfilePresenter;
import com.example.cris.studentsapp.screen.profile.view.delegate.IProfileViewDelegate;
import com.example.cris.studentsapp.screen.profile.view.fragment.ProfileFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {

    @Provides
    IProfileModel providesProfileModel(Context context, ApiInterface apiInterface) {
        return new ProfileModel(context, apiInterface);
    }

    @Provides
    IProfileViewDelegate providesProfileViewDelegate(ProfileFragment fragment) {
        return fragment;
    }

    @Provides
    IProfilePresenter providesProfilePresenter(Context context,
                                               IProfileViewDelegate viewDelegate,
                                               IProfileModel model) {
        return new ProfilePresenter(context, viewDelegate, model);
    }
}

package com.example.cris.studentsapp.screen.login.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.login.model.ILoginModel;
import com.example.cris.studentsapp.screen.login.model.LoginModel;
import com.example.cris.studentsapp.screen.login.presenter.ILoginPresenter;
import com.example.cris.studentsapp.screen.login.presenter.LoginPresenter;
import com.example.cris.studentsapp.screen.login.view.activity.LoginActivity;
import com.example.cris.studentsapp.screen.login.view.delegate.ILoginViewDelegate;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    ILoginModel provideLoginModel(IApiInterface IApiInterface) {
        return new LoginModel(IApiInterface);
    }

    @Provides
    ILoginViewDelegate provideLoginViewDelegate(LoginActivity loginActivity) {
        return  loginActivity;
    }

    @Provides
    ILoginPresenter provideLoginPresenter(Context context,
                                          ILoginViewDelegate viewDelegate,
                                          ILoginModel loginModel) {
        return new LoginPresenter(context, viewDelegate, loginModel);
    }

}

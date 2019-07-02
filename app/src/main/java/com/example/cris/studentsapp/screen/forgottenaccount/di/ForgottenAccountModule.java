package com.example.cris.studentsapp.screen.forgottenaccount.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.forgottenaccount.model.ForgottenAccountModel;
import com.example.cris.studentsapp.screen.forgottenaccount.model.IForgottenAccountModel;
import com.example.cris.studentsapp.screen.forgottenaccount.presenter.ForgottenAccountPresenter;
import com.example.cris.studentsapp.screen.forgottenaccount.presenter.IForgottenAccountPresenter;
import com.example.cris.studentsapp.screen.forgottenaccount.view.activity.ForgottenAccountActivity;
import com.example.cris.studentsapp.screen.forgottenaccount.view.delegate.IForgottenAccountViewDelegate;

import dagger.Module;
import dagger.Provides;

@Module
public class ForgottenAccountModule {

    @Provides
    IForgottenAccountViewDelegate provideForgottenAccountViewDelegate(ForgottenAccountActivity accountActivity) {
        return accountActivity;
    }

    @Provides
    IForgottenAccountModel provideForgottenAccountModel(IApiInterface IApiInterface) {
        return new ForgottenAccountModel(IApiInterface);
    }

    @Provides
    IForgottenAccountPresenter provideForgottenAccountPresenter(Context context,
                                                                IForgottenAccountViewDelegate viewDelegate,
                                                                IForgottenAccountModel model) {
        return new ForgottenAccountPresenter(context, viewDelegate, model);
    }
}

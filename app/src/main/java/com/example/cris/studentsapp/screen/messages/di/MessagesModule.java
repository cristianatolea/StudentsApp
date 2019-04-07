package com.example.cris.studentsapp.screen.messages.di;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.messages.model.IMessagesModel;
import com.example.cris.studentsapp.screen.messages.model.MessagesModel;
import com.example.cris.studentsapp.screen.messages.presenter.IMessagesPresenter;
import com.example.cris.studentsapp.screen.messages.presenter.MessagesPresenter;
import com.example.cris.studentsapp.screen.messages.view.delegate.IMessagesViewDelegate;
import com.example.cris.studentsapp.screen.messages.view.fragment.MessagesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MessagesModule {

    @Provides
    IMessagesViewDelegate providesMessagesViewDelegate(MessagesFragment fragment) {
        return fragment;
    }

    @Provides
    IMessagesModel providesMessagesModel(ApiInterface apiInterface) {
        return new MessagesModel(apiInterface);
    }

    @Provides
    IMessagesPresenter providesMessagesPresenter(Context context,
                                                 IMessagesViewDelegate viewDelegate,
                                                 IMessagesModel model) {
        return new MessagesPresenter(context, viewDelegate, model);
    }
}

package com.example.cris.studentsapp.app.di;


import android.content.Context;

import com.example.cris.studentsapp.BuildConfig;
import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.apiprovider.StudentsApiProvider;
import com.example.cris.studentsapp.app.StudentsApplication;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class StudentsAppModule {

    @Singleton
    @Provides
    final Context provideApplicationContext(StudentsApplication application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    ApiInterface provideApiWithToken(Context context) {
        return new StudentsApiProvider.Builder(context, BuildConfig.BASE_URL)
                .canLog(BuildConfig.DEBUG)
                .withDateFormat("yyyy-MM-dd HH:mm:ss")
                .hasRx2JavaConverter(true)
                .withTimeout(60, TimeUnit.SECONDS)
                .build()
                .createClientWithToken(ApiInterface.class, context);
    }
}
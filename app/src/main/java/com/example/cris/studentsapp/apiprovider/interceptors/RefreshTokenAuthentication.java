package com.example.cris.studentsapp.apiprovider.interceptors;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class RefreshTokenAuthentication implements Authenticator {

    private Context mContext;

    public RefreshTokenAuthentication(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Request authenticate(Route route, @NonNull Response response) throws IOException {

        //todo
        return response.request().newBuilder()
                .header("Authorization", "Bearer " + "//cevaaaa")
                .build();
    }
}

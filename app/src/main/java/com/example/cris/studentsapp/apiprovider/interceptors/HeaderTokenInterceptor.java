package com.example.cris.studentsapp.apiprovider.interceptors;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.cris.studentsapp.utils.LocalSaving;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Irina-Gabriela Nuta (irina.nuta@roweb.ro) on 26/09/2018.
 * <p>
 * HeaderTokenInterceptor is developed by Roweb Development.
 */

public class HeaderTokenInterceptor implements Interceptor {

    private Context mContext;

    public HeaderTokenInterceptor(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request oldRequest = chain.request();

        String token = LocalSaving.getToken(mContext);

        Request.Builder testBuilder = oldRequest.newBuilder();
        testBuilder.addHeader("Content-Type", "application/json; charset=utf-8");
        testBuilder.addHeader("Accept", "application/json");

        if (!token.isEmpty()) {
            testBuilder.addHeader("Authorization", "Bearer " + token);
        }
        return chain.proceed(testBuilder.build());
    }
}

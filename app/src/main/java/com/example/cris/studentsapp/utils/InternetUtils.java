package com.example.cris.studentsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * Created by Irina on 2/13/2018.
 */

public class InternetUtils {

    /**
     * Check if user is connected to any internet source: wifi/data
     *
     * @param context context to access {@literal CONNECTIVITY_SERVICE}
     * @return return connection status
     */
    public static boolean hasActiveInternetConnection(@NonNull Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager != null &&
                connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}

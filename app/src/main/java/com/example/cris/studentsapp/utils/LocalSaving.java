package com.example.cris.studentsapp.utils;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by Irina on 2/13/2018.
 */

public class LocalSaving {

    private static final String SHARED_PREFERENCES_KEY = "seatbelt-key";
    private static final String LS_FIRST_OPEN = "ls-first-open";
    private static final String LS_TOKEN = "ls-token";
    private static final String LS_USER_ID = "ls-user-id";
    private static final String LS_USER = "ls-user";
    private static final String LS_USERS_LIST = " ls-users-list";
    private static final String LS_WORK_POINT = "ls-work-point";
    private static final String LS_REMEMBER_TOKEN = " ls-remember-token";
    private static final String LS_HAS_SAVED_AUDIT = " ls-has-saved-audit";
    private static final String LS_HAS_ENTERED = "ls-has-entered";


    public static void setLsFirstOpen(Context context, boolean opened) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(LS_FIRST_OPEN, opened)
                .apply();
    }

    public static boolean getLsFirstOpen(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getBoolean(LS_FIRST_OPEN, false);
    }


    public static String getToken(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getString(LS_TOKEN, "");
    }

    public static void setToken(Context context, String token) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_TOKEN, token)
                .apply();
    }

    public static void setUserId(Context context, String userId) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_USER_ID, userId)
                .apply();
    }

    public static String getUserId(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getString(LS_USER_ID, "");
    }

    /* *//*Methods to get/set a list of objects to LS*//*
    public static void saveUsersList(Context context, List<UserProfileResponse> eventList) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_REMEMBER_TOKEN, new Gson().toJson(eventList))
                .apply();
    }

    public static List<UserProfileResponse> getUsersList(Context context) {
        return new Gson().fromJson(
                context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                        .getString(LS_REMEMBER_TOKEN, ""),
                new TypeToken<List<UserProfileResponse>>() {
                }
                        .getType());
    }*/

    public static int getWorkPoint(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getInt(LS_WORK_POINT, 0);
    }

    public static void setWorkPoint(Context context, int workPointId) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putInt(LS_WORK_POINT, workPointId)
                .apply();
    }

    public static void clear(Context context) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

    public static void setRememberMeToken(Context context, String rememberToken) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_REMEMBER_TOKEN, rememberToken)
                .apply();
    }

    public static String getRememberMeToken(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getString(LS_REMEMBER_TOKEN, "");
    }

    public static boolean getHasSavedAudit(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getBoolean(LS_HAS_SAVED_AUDIT, false);
    }

    public static void setHasSavedAudit(Context context, boolean hasSaved) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(LS_HAS_SAVED_AUDIT, hasSaved)
                .apply();
    }

    public static boolean getHasEntered(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getBoolean(LS_HAS_ENTERED, false);
    }

    public static void setHasEntered(Context context, boolean hasEntered) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(LS_HAS_ENTERED, hasEntered)
                .apply();
    }
}

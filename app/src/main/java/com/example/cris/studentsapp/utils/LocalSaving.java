package com.example.cris.studentsapp.utils;

import android.content.Context;

import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Irina on 2/13/2018.
 */

public class LocalSaving {

    private static final String SHARED_PREFERENCES_KEY = "moodle-key";
    private static final String LS_FIRST_OPEN = "ls-first-open";
    private static final String LS_IS_LOGGED_IN = "ls-is-logged-in";
    private static final String LS_TOKEN = "ls-token";
    private static final String LS_USER_ID = "ls-user-id";
    private static final String LS_USER_NAME = "ls-user-name";
    private static final String LS_PRIVATE_TOKEN = " ls-private-token";
    private static final String LS_EVENTS_LIST = "ls-events-list";
    private static final String LS_TYPE_LIST = "ls-type-list";
    private static final String LS_RECURRENCE_LIST = "ls-recurrence-list";


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

    public static void setLsIsLoggedIn(Context context, boolean opened) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(LS_IS_LOGGED_IN, opened)
                .apply();
    }

    public static boolean getLsIsLoggedIn(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getBoolean(LS_IS_LOGGED_IN, false);
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

    public static void clear(Context context) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

    public static void setPrivateToken(Context context, String rememberToken) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_PRIVATE_TOKEN, rememberToken)
                .apply();
    }

    public static String getPrivateToken(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getString(LS_PRIVATE_TOKEN, "");
    }

    public static void setUsername(Context context, String username) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_USER_NAME, username)
                .apply();
    }

    public static String getUsername(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getString(LS_USER_NAME, "");
    }

    public static void setEventsList(Context context, List<WeekDayEntity> eventsList) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_EVENTS_LIST.concat(getUsername(context)), new Gson().toJson(eventsList))
                .apply();
    }

    public static List<WeekDayEntity> getEventsList(Context context) {
        return new Gson().fromJson(
                context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                        .getString(LS_EVENTS_LIST.concat(getUsername(context)), ""),
                new TypeToken<List<WeekDayEntity>>() {

                }
                        .getType());
    }

    public static void setTypeList(Context context, List<String> eventsList) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_TYPE_LIST, new Gson().toJson(eventsList))
                .apply();
    }

    public static List<String> getTypeList(Context context) {
        return new Gson().fromJson(
                context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                        .getString(LS_TYPE_LIST, ""),
                new TypeToken<List<String>>() {

                }
                        .getType());
    }

    public static void setRecurrenceList(Context context, List<String> eventsList) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_RECURRENCE_LIST, new Gson().toJson(eventsList))
                .apply();
    }

    public static List<String> getRecurrenceList(Context context) {
        return new Gson().fromJson(
                context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                        .getString(LS_RECURRENCE_LIST, ""),
                new TypeToken<List<String>>() {

                }
                        .getType());
    }
}

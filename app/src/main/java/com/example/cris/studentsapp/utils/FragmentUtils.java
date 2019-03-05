package com.example.cris.studentsapp.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by catalin.florescu on 1/28/2016.
 * ------------------------------
 */
public class FragmentUtils {

    // method called when need to insert first time a fragment into activity
    public static void addFragment(@NonNull Activity activity,
                                   @IdRes int containerViewId,
                                   @NonNull Fragment fragment) {
        /*((FragmentActivity) activity)
                .getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(containerViewId, fragment, fragment.getClass().getCanonicalName())
                .addToBackStack(fragment.getClass().getCanonicalName())
                .setReorderingAllowed(true)
                .commit();*/
        replaceFragment(activity, containerViewId, fragment);
    }

    /**
     * Create fragment and replace specified frame
     *
     * @param activity
     * @param fragment
     * @param content_frame
     */
    public static void replaceFragment(@NonNull Activity activity,
                                       @IdRes int content_frame,
                                       @NonNull Fragment fragment) {
        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(content_frame, fragment, fragment.getClass().getCanonicalName());
        transaction.addToBackStack(fragment.getClass().getCanonicalName());
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

}

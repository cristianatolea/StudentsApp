package com.example.cris.studentsapp.utils;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.animation.AccelerateDecelerateInterpolator;


public class DrawerToggleAnimatorHelper {

    /**
     * INTEGER values can be used to specify Toggle state if you don't always know values
     */
    private static final int ARROW = 0, HAMBURGER = 1;
    /**
     * Animation DURATION
     */
    private final static int DURATION = 340;
    /**
     * Save state of menu icon: if is arrow or ham
     */
    private static boolean isArrow = false;

    /**
     * Animate DrawerArrowToggle from HAMBURGER to ARROW and backward
     * When is HAMBURGER navigation drawer will open
     * Otherwise, when is ARROW it will pop back to stack fragments until it will be again HAMBURGER
     * Use handler to make animation more faster
     *
     * @param state : make drawer toggle ARROW (0) or HAMBURGER (1)
     */
    private static void animateDrawerToggle(int state,
                                            @NonNull final DrawerLayout drawerLayout,
                                            @NonNull final ActionBarDrawerToggle toggle) {
        switch (state) {
            case ARROW:
                if (!isArrow) {
                    drawerLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            final ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float slideOffset = (Float) valueAnimator.getAnimatedValue();
                                    toggle.onDrawerSlide(drawerLayout, slideOffset);
                                }
                            });
                            anim.setInterpolator(new AccelerateDecelerateInterpolator());
                            anim.setDuration(DURATION);
                            anim.start();
                        }
                    });
                    isArrow = true;
                }
                break;

            case HAMBURGER:
                if (isArrow) {
                    drawerLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            final ValueAnimator anim = ValueAnimator.ofFloat(1, 0);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float slideOffset = (Float) valueAnimator.getAnimatedValue();
                                    toggle.onDrawerSlide(drawerLayout, slideOffset);
                                }
                            });
                            anim.setInterpolator(new AccelerateDecelerateInterpolator());
                            anim.setDuration(DURATION);
                            anim.start();
                        }
                    });
                    isArrow = false;
                }
                break;
        }
    }

    /**
     * Animate drawer toggle into arrow if is hamburger
     */
    public static void animateIconToArrow(@NonNull DrawerLayout drawerLayout,
                                          @NonNull ActionBarDrawerToggle toggle) {
        if (!isArrow()) {
            DrawerToggleAnimatorHelper.animateDrawerToggle(ARROW, drawerLayout, toggle);
        }
    }

    /**
     * Animate drawer toggle into hamburger if is arrow
     */
    public static void animateIconToHamburger(DrawerLayout drawerLayout,
                                              ActionBarDrawerToggle toggle) {
        if (isArrow()) {
            DrawerToggleAnimatorHelper.animateDrawerToggle(HAMBURGER, drawerLayout, toggle);
        }
    }

    /**
     * return state of Toggle
     * arrow or hamburger
     *
     * @return boolean state
     */
    public static boolean isArrow() {
        return isArrow;
    }
}

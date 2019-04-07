package com.example.cris.studentsapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Irina on 2/13/2018.
 */

public class UIHelper {

    /**
     * Show keyboard and move focus to provided view
     *
     * @param activity access INPUT_METHOD_SERVICE
     * @param view     to move focus
     */
    public static void showKeyboard(@NonNull final Activity activity, @NonNull final View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);

                if (inputManager != null) {
                    inputManager.toggleSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.SHOW_FORCED,
                            0);
                    view.requestFocus();
                }
            }
        });
    }

    /**
     * Hide keyboard programmatically
     *
     * @param activity find current view focus
     */
    public static void hideKeyboard(@NonNull final Activity activity, @NonNull final View view) {
        // Check if no view has focus
        // if keyboard is shown, hideViewHolder it
        if (!hideKeyboard(view)) {
            final InputMethodManager inputManager = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            // getApplicationWindowToken
            final View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                currentFocus.post(new Runnable() {
                    @Override
                    public void run() {
                        if (inputManager != null) {
                            boolean result = inputManager.hideSoftInputFromWindow(
                                    currentFocus.getApplicationWindowToken(),
                                    InputMethodManager.HIDE_IMPLICIT_ONLY);

                            if (!result) {
                                boolean secondResult = inputManager.hideSoftInputFromWindow(
                                        currentFocus.getWindowToken(), 0);

                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * For future calls on hideKeyboard(Activity activity) method
     * should be replaced with this method
     *
     * @param view should be an instance of {@link EditText}
     *             eg:
     *             {@link EditText},
     *             {@link android.support.v7.widget.AppCompatEditText},
     *             {@link android.support.v7.widget.AppCompatAutoCompleteTextView}
     * @return state of keyboard (hide or not)
     */
    public static boolean hideKeyboard(@NonNull final View view) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            return true;
        }
        return false;
    }

    /**
     * Move cursor to end of edit text
     *
     * @param view field who needs to move cursor
     */
    public static void moveCursorOnEnd(@NonNull final View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                if (view instanceof EditText) {
                    EditText editText = (EditText) view;
                    editText.requestFocus();
                    editText.setSelection(editText.getText().length());
                }
            }
        });
    }


    /**
     * remove focus when a view that is not an instanceOf EditText is clicked
     * set up touch listener for non-text box views to hide keyboard
     * if view is a layout container, iterate over children and seed recursion.
     *
     * @param view     parent view
     * @param activity which is used for that view
     */
    public static void setupUI(View view, final Activity activity) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(activity, v);
                    if (v.hasFocus()) {
                        v.clearFocus();
                    }
                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}

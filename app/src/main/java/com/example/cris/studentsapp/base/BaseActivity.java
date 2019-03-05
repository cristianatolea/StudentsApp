package com.example.cris.studentsapp.base;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.utils.FragmentUtils;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity implements IBaseNoInternetViewDelegate {
    public void addFragment(Fragment fragment, @IdRes int layoutId) {
        FragmentUtils.replaceFragment(this, layoutId, fragment);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onNoInternetConnection() {
        Toast.makeText(getBaseContext(), getString(R.string.alert_no_internet_connection), Toast.LENGTH_SHORT).show();
    }

    public void setLightStatusBar(View view) {
        int flags = view.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            view.setSystemUiVisibility(flags);
            /*getWindow().setStatusBarColor(Color.WHITE);*/
            getWindow().setNavigationBarColor(Color.WHITE);
        }/* else {
//            getWindow().setStatusBarColor(Color.WHITE);
//            setTranslucentStatusBar(true);
//        }
            //setTranslucentStatusBar(false);*/
    }

    public void setTranslucentStatusBar(boolean isTranslucent) {
        if (isTranslucent)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
}

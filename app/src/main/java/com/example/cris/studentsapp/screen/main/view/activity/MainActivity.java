package com.example.cris.studentsapp.screen.main.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.dashboard.view.fragment.DashboardFragment;
import com.example.cris.studentsapp.screen.logout.view.activity.LogoutActivity;
import com.example.cris.studentsapp.screen.main.model.entity.DrawerItem;
import com.example.cris.studentsapp.screen.main.presenter.IMainPresenter;
import com.example.cris.studentsapp.screen.main.view.adapter.NavDrawerListAdapter;
import com.example.cris.studentsapp.screen.main.view.delegate.IMainViewDelegate;
import com.example.cris.studentsapp.screen.notifications.view.fragment.NotificationsFragment;
import com.example.cris.studentsapp.screen.profile.view.fragment.ProfileFragment;
import com.example.cris.studentsapp.screen.settings.view.fragment.SettingsFragment;
import com.example.cris.studentsapp.utils.Constants;
import com.example.cris.studentsapp.utils.DrawerToggleAnimatorHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.UIHelper.hideKeyboard;

public class MainActivity extends BaseActivity implements
        IMainViewDelegate,
        View.OnClickListener,
        NavDrawerListAdapter.OnMenuItemClickListener {

    private boolean mOpenNotifications = false;
    private Bundle mBundle;
    private Toolbar mToolbar;
    private static TextView mToolbarTitle;
    private DrawerLayout mDrawerLayout;
    private ListView mNavDrawerMainItemList;
    private ListView mNavDrawerBottomList;
    private List<DrawerItem> mTopDrawerItems;
    private List<DrawerItem> mBottomDrawerItems;
    private NavDrawerListAdapter mTopAdapter;
    private NavDrawerListAdapter mBottomAdapter;
    private ImageView mImageButtonDrawer;
    private ProgressBar mProgressBar;

    private boolean doubleBackToExitPressedOnce = false;

    @Inject
    IMainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mOpenNotifications = mBundle.getBoolean(Constants.KEY_OPEN_NOTIFICATIONS_SCREEN);
        }
        setContentView(R.layout.activity_main);
        initView();
        mPresenter.getSiteInfo();
//        if (!LocalSaving.getLsFirstOpen(this) || "".equals(LocalSaving.getToken(MainActivity.this))) {
//            Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//        } else {
//            setContentView(R.layout.activity_main);
//            initView();
//        }

//        if ("".equals(LocalSaving.getToken(MainActivity.this))) {
//            Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//        } else {
//            setContentView(R.layout.activity_main);
//        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_button_drawer:
                mDrawerLayout.closeDrawer(Gravity.START, true);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
                mDrawerLayout.closeDrawer(GravityCompat.START);
            else {
                int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
                if (backStackCount > 1) {
                    super.onBackPressed();
                } else {
                    if (doubleBackToExitPressedOnce) {
                        super.onBackPressed();
                        finish();
                        return;
                    }

                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(MainActivity.this, getString(R.string.back_click), Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDrawerItemClick(int position) {
        mBottomAdapter.onFocus(-1);
        mTopAdapter.onFocus(position);
        mBottomAdapter.notifyDataSetChanged();
        mTopAdapter.notifyDataSetChanged();
        switch (position) {
            case 0:
                if (!(lastFragment() instanceof DashboardFragment)) {
                    addFragment(new DashboardFragment(), R.id.frame_main_content);
                    hideKeyboard(MainActivity.this, mDrawerLayout);
                }
                setToolbarTitle(R.string.dashboard);
                break;
            case 1:
                if (!(lastFragment() instanceof ProfileFragment)) {
                    addFragment(new ProfileFragment(), R.id.frame_main_content);
                    hideKeyboard(MainActivity.this, mDrawerLayout);
                }
                setToolbarTitle(R.string.profile);
                break;
            case 2:
                if (!(lastFragment() instanceof SettingsFragment)) {
                    addFragment(new SettingsFragment(), R.id.frame_main_content);
                    hideKeyboard(MainActivity.this, mDrawerLayout);
                }
                setToolbarTitle(R.string.settings);
                break;
            case 3:
                if (!(lastFragment() instanceof NotificationsFragment)) {
                    addFragment(new NotificationsFragment(), R.id.frame_main_content);
                    hideKeyboard(MainActivity.this, mDrawerLayout);
                }
                setToolbarTitle(R.string.notifications);
                break;
        }
        mDrawerLayout.closeDrawer(Gravity.START, true);
    }

    public void changeFocusOnMenu(int position, boolean top, boolean bottom) {
        if (top) {
            mBottomAdapter.onFocus(-1);
            mTopAdapter.onFocus(position);
        } else if (bottom) {
            mTopAdapter.onFocus(-1);
            mBottomAdapter.onFocus(position);
        } else {
            mTopAdapter.onFocus(-1);
            mBottomAdapter.onFocus(-1);
        }

        mBottomAdapter.notifyDataSetChanged();
        mTopAdapter.notifyDataSetChanged();

    }

    private void populateDrawerItems() {
        mTopDrawerItems = new ArrayList<>();
        mBottomDrawerItems = new ArrayList<>();
        DrawerItem dashboard = new DrawerItem(getString(R.string.dashboard),
                R.drawable.ic_dashboard_blue,
                R.drawable.ic_dashboard);
        dashboard.setOnFocus(true);
        mTopDrawerItems.add(dashboard);
        mTopDrawerItems.add(new DrawerItem(getString(R.string.profile),
                R.drawable.ic_profile_blue,
                R.drawable.ic_profile));
        mTopDrawerItems.add(new DrawerItem(getString(R.string.settings),
                R.drawable.ic_settings_blue,
                R.drawable.ic_settings));
        mTopDrawerItems.add(new DrawerItem(getString(R.string.notifications),
                R.drawable.ic_notifications_blue,
                R.drawable.ic_notifications));
        mBottomDrawerItems.add(new DrawerItem((getString(R.string.logout)),
                R.drawable.ic_logout_blue,
                R.drawable.ic_logout));
    }

    private void populateMenu() {

        populateDrawerItems();

        mTopAdapter = new NavDrawerListAdapter(
                this, mTopDrawerItems,
                this,
                R.layout.item_nav_drawer);
        mNavDrawerMainItemList.setAdapter(mTopAdapter);


        NavDrawerListAdapter.OnMenuItemClickListener bottomListener = new NavDrawerListAdapter.OnMenuItemClickListener() {
            @Override
            public void onDrawerItemClick(int position) {
                mBottomAdapter.onFocus(-1);
                mBottomAdapter.notifyDataSetChanged();
                mTopAdapter.notifyDataSetChanged();

                startActivity(new Intent(MainActivity.this, LogoutActivity.class));
            }
        };
        mBottomAdapter = new NavDrawerListAdapter(this,
                mBottomDrawerItems,
                bottomListener,
                R.layout.item_nav_drawer);
        mNavDrawerBottomList.setAdapter(mBottomAdapter);

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar, 0, 0) {
            public void onDrawerClosed(View view) {
                mImageButtonDrawer.setImageDrawable(getDrawable(R.drawable.ic_nav_menu));
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageButtonDrawer, "alpha", 0, 1);
                objectAnimator.setDuration(1000);
                objectAnimator.setStartDelay(0);
                objectAnimator.start();
                mImageButtonDrawer.setImageResource(R.drawable.ic_close);
                hideKeyboard(MainActivity.this, mDrawerLayout);
            }
        };
        mActionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DrawerToggleAnimatorHelper.isArrow()) {
                    onBackPressed();

                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);

                }
            }
        });
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    private Fragment lastFragment() {
        if (getSupportFragmentManager().getFragments().size() == 0) {
            return new Fragment();
        } else {
            return getSupportFragmentManager()
                    .getFragments()
                    .get(getSupportFragmentManager().getFragments().size() - 1);
        }
    }

    public void setToolbarTitle(int id) {
        mToolbarTitle.setText(getString(id));
    }

    private void initView() {
        AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout);
        LinearLayout linearDrawerButton = findViewById(R.id.linear_button);
        mProgressBar = findViewById(R.id.progress_bar);
        mToolbar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.text_title);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavDrawerMainItemList = findViewById(R.id.list_view_nav);
        mNavDrawerBottomList = findViewById(R.id.list_view_bottom_nav);
        mImageButtonDrawer = findViewById(R.id.image_button_drawer);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        populateMenu();

        ViewCompat.setElevation(appBarLayout, 5);
        linearDrawerButton.bringToFront();

        mImageButtonDrawer.setOnClickListener(this);

        if (!(lastFragment() instanceof DashboardFragment)) {
            addFragment(new DashboardFragment(), R.id.frame_main_content);
            setToolbarTitle(R.string.dashboard);
        }
    }
}

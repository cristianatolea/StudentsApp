package com.example.cris.studentsapp.screen.dashboarditemactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.courses.view.fragment.CoursesFragment;
import com.example.cris.studentsapp.screen.deadlines.view.fragment.DeadlinesFragment;

import static com.example.cris.studentsapp.screen.dashboard.view.fragment.DashboardFragment.BUNDLE_FRAGMENT_NO;

public class DashboardItemActivity extends BaseActivity {

    private TextView mTextToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_item);

        initView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int fragmentNo = bundle.getInt(BUNDLE_FRAGMENT_NO);
            switch (fragmentNo) {
                case 0:
                    addFragment(new CoursesFragment(), R.id.frame_main_content);
                    setToolbarTitle(R.string.courses);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
//                    addFragment(new NearMissFragment(), R.id.main_content);
//                    setToolbarTitle(R.string.near_miss);
                    break;
                case 4:
                    addFragment(new DeadlinesFragment(), R.id.frame_main_content);
                    setToolbarTitle(R.string.deadlines);
                    break;
            }
        }
    }

    public void setToolbarTitle(int title) {
        mTextToolbarTitle.setText(getString(title));
    }

    public void initView() {
        mTextToolbarTitle = findViewById(R.id.text_title);
    }
}

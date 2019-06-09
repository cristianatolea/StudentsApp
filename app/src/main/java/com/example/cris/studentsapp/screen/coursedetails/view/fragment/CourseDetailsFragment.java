package com.example.cris.studentsapp.screen.coursedetails.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.presenter.ICourseDetailsPresenter;
import com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitems.CourseDetailsAdapter;
import com.example.cris.studentsapp.screen.coursedetails.view.delegate.ICourseDetailsViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_ID;
import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;

public class CourseDetailsFragment extends BaseFragment implements
        ICourseDetailsViewDelegate,
        CourseDetailsAdapter.OnItemFileClickListener {

    private TextView mTextName;
    private RecyclerView mRvCourseItems;
    private ProgressBar mProgressBar;

    private CourseDetailsAdapter mCourseAdapter;
    private CourseDetailsItem mGeneralCourseItem;
    private List<CourseDetailsItem> mCourseDetailsItems;
    private String mCourseId = "";
    private String mCourseName = "";

    @Inject
    ICourseDetailsPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCourseDetailsItems = new ArrayList<>();

        mCourseId = getArguments().getString(COURSE_ID);
        mCourseName = getArguments().getString(COURSE_NAME);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.course_details);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, false, false);

        initView(view);

        mPresenter.getCourseDetails(mCourseId);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String errorMessage) {
        AlertUtils.alert(getContext(), R.string.alert_title, errorMessage);
    }

    @Override
    public void onGetCourseDetailsSuccess(List<CourseDetailsItem> courseDetailsResponse) {
        if (courseDetailsResponse.size() > 0) {
            mGeneralCourseItem = courseDetailsResponse.get(0);
            mCourseDetailsItems.clear();
//            courseDetailsResponse.remove(0);
            mCourseDetailsItems.addAll(courseDetailsResponse);
            mCourseAdapter.notifyDataSetChanged();
        } else {
            Log.d("on success", "size <= 0");
        }
    }

    public static CourseDetailsFragment newInstance(String id, String name) {
        Bundle args = new Bundle();
        args.putString(COURSE_ID, id);
        args.putString(COURSE_NAME, name);
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);

        mTextName = view.findViewById(R.id.text_course_name);
        mRvCourseItems = view.findViewById(R.id.rv_course_items);

        mCourseAdapter = new CourseDetailsAdapter(getContext(), mCourseDetailsItems, this);
        mRvCourseItems.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvCourseItems.setAdapter(mCourseAdapter);

        mTextName.setText(mCourseName);
    }

    @Override
    public void onItemFileClick(int position, int coursePosition) {

    }
}

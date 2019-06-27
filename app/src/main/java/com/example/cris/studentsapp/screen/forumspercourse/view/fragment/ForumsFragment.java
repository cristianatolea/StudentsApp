package com.example.cris.studentsapp.screen.forumspercourse.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.fragment.DiscussionsPerForumFragment;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.screen.forumspercourse.presenter.IForumsPresenter;
import com.example.cris.studentsapp.screen.forumspercourse.view.adapter.SimpleDividerItemDecoration;
import com.example.cris.studentsapp.screen.forumspercourse.view.adapter.forums.CourseForumAdapter;
import com.example.cris.studentsapp.screen.forumspercourse.view.adapter.spinner.CourseSpinnerAdapter;
import com.example.cris.studentsapp.screen.forumspercourse.view.delegate.IForumsViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class ForumsFragment extends BaseFragment implements
        IForumsViewDelegate,
        CourseForumAdapter.OnItemDetailsClick {

    private CardView mCardForums;
    private ProgressBar mProgressBar;
    private TextView mTextNoForums;
    private Spinner mCoursesSpinner;

    private CourseSpinnerAdapter mSpinnerAdapter;
    private CourseForumAdapter mForumAdapter;
    private List<CourseEntity> mCoursesList;
    private List<ForumEntity> mForumsList;
    private String mSelectedCourseName = "";
    private int mSelectedPosition;

    @Inject
    IForumsPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCoursesList = new ArrayList<>();
        mForumsList = new ArrayList<>();
        mSelectedPosition = 0;

        initView(view);

        mPresenter.getCoursesList();

        ((MainActivity) getActivity()).setToolbarTitle(R.string.forums);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, false, false);
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
    public void onGetCoursesListSuccess(List<CourseEntity> list) {
        CourseEntity noneEntity = new CourseEntity("-1", "None");
        mCoursesList.clear();
        mCoursesList.add(noneEntity);
        mCoursesList.addAll(list);
        mCoursesList.remove(1);
        mSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetForumsSuccess(List<ForumEntity> list) {
        mForumsList.clear();
        mForumsList.addAll(list);
        mForumAdapter.notifyDataSetChanged();
        if (mForumsList != null) {
            mCardForums.setVisibility(View.VISIBLE);
            mTextNoForums.setVisibility(View.GONE);
        } else {
            mCardForums.setVisibility(View.GONE);
            mTextNoForums.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemDetailsClick(int position) {
        boolean isNewsType = findString(mForumsList.get(position).getForumName());

        DiscussionsPerForumFragment discussionsPerForumFragment =
                DiscussionsPerForumFragment.newInstance(mForumsList.get(position).getForumId(),
                        mForumsList.get(position).getForumName(),
                        mSelectedCourseName,
                        isNewsType);
        ((MainActivity) getActivity())
                .changeFocusOnMenu(0, false, false);
        addFragment(discussionsPerForumFragment, R.id.frame_main_content);
    }

    private boolean findString(String string) {
        String keyword = "ştiri";
        return Arrays.asList(string.split(" ")).contains(keyword);
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);

        mCoursesSpinner = view.findViewById(R.id.spinner_discussions_courses);
        RecyclerView rvCourseForums = view.findViewById(R.id.rv_course_forums);
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mCardForums = view.findViewById(R.id.card_course_forums);
        mTextNoForums = view.findViewById(R.id.text_no_forums);

        mCardForums.setVisibility(View.GONE);

        mSpinnerAdapter = new CourseSpinnerAdapter(getContext(), R.layout.item_course_spinner, mCoursesList);
        mCoursesSpinner.setAdapter(mSpinnerAdapter);
        mCoursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPosition = position;
                if (position != 0) {
                    mSelectedCourseName = mCoursesList.get(position).getFullname();
                    mPresenter.getForumsPerCourse(mCoursesList.get(position).getId());
                } else {
                    mForumsList.clear();
                    mSelectedCourseName = "";
                    mCardForums.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SimpleDividerItemDecoration dividerItemDecoration =
                new SimpleDividerItemDecoration(rvCourseForums.getContext());
        rvCourseForums.addItemDecoration(dividerItemDecoration);
        rvCourseForums.setLayoutManager(new LinearLayoutManager(getContext()));
        mForumAdapter = new CourseForumAdapter(getContext(), mForumsList, this);
        rvCourseForums.setAdapter(mForumAdapter);

        mTextNoForums.setVisibility(View.GONE);
    }
}

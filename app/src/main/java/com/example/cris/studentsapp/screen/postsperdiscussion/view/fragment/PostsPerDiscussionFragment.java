package com.example.cris.studentsapp.screen.postsperdiscussion.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostEntity;
import com.example.cris.studentsapp.screen.postsperdiscussion.presenter.IPostsPerDiscussionPresenter;
import com.example.cris.studentsapp.screen.postsperdiscussion.view.adapter.PostAdapter;
import com.example.cris.studentsapp.screen.postsperdiscussion.view.delegate.IPostsPerDiscussionViewDelegate;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;
import static com.example.cris.studentsapp.utils.Constants.DISCUSSION_ID;
import static com.example.cris.studentsapp.utils.Constants.DISCUSSION_NAME;
import static com.example.cris.studentsapp.utils.Constants.STIRI;

public class PostsPerDiscussionFragment extends BaseFragment implements
        IPostsPerDiscussionViewDelegate,
        View.OnClickListener {

    private ProgressBar mProgressBar;
    private TextView mTextCourseName;
    private TextView mTextDiscussionTitle;
    private TextView mTextNoResults;
    private RecyclerView mRvPosts;
    private LinearLayout mLinearNewPost;
    private EditText mEditNewPost;

    private String mDiscussionId = "";
    private String mDiscussionName = "";
    private String mCourseName = "";
    private String mNewPost = "";
    private boolean mIsNewsType;
    private List<PostEntity> mPostsList;
    private PostAdapter mPostsAdapter;

    @Inject
    IPostsPerDiscussionPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts_discussions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPostsList = new ArrayList<>();

        mDiscussionId = getArguments().getString(DISCUSSION_ID);
        mDiscussionName = getArguments().getString(DISCUSSION_NAME);
        mCourseName = getArguments().getString(COURSE_NAME);
        mIsNewsType = getArguments().getBoolean(STIRI);

        initView(view);

        mPresenter.getPosts(mDiscussionId);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.discussions);
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
    public void onGetPostsSuccess(List<PostEntity> list) {
        mPostsList.clear();
        mPostsList.addAll(list);
        mPostsAdapter.notifyDataSetChanged();
        if (mPostsList != null && !mPostsList.isEmpty()) {
            mRvPosts.setVisibility(View.VISIBLE);
            mTextNoResults.setVisibility(View.GONE);
        } else {
            mRvPosts.setVisibility(View.GONE);
            mTextNoResults.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPostAddedSuccessfully() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_add_post:
                mNewPost = mEditNewPost.getText().toString();
                mEditNewPost.setText("");
                mEditNewPost.setHint(R.string.post_hint);
                hideKeyboard(getActivity());
                mEditNewPost.clearFocus();
                if (!("").equals(mNewPost)) {
//                    mPresenter.addPost(
//                            mPostsList.get(0).getPostId(),
//                            mPostsList.get(0).getSubject(),
//                            mNewPost);
                    mNewPost = "";
                }
                break;
            case R.id.edit_post:
                mEditNewPost.setFocusableInTouchMode(true);
                break;
        }
    }

    public static PostsPerDiscussionFragment newInstance(String discussionId,
                                                         String discussionName,
                                                         String courseName,
                                                         boolean stiri) {
        Bundle args = new Bundle();
        args.putString(DISCUSSION_ID, discussionId);
        args.putString(DISCUSSION_NAME, discussionName);
        args.putString(COURSE_NAME, courseName);
        args.putBoolean(STIRI, stiri);
        PostsPerDiscussionFragment fragment = new PostsPerDiscussionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);

        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mTextCourseName = view.findViewById(R.id.text_course_name);
        mTextDiscussionTitle = view.findViewById(R.id.text_discussion_title);
        mTextNoResults = view.findViewById(R.id.text_no_posts);
        mRvPosts = view.findViewById(R.id.rv_posts);
        mLinearNewPost = view.findViewById(R.id.linear_text_post);
        mEditNewPost = view.findViewById(R.id.edit_post);
        LinearLayout linearAddPost = getActivity().findViewById(R.id.linear_add_post);

        mRvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        mPostsAdapter = new PostAdapter(getContext(), mPostsList);
        mRvPosts.setAdapter(mPostsAdapter);

        mTextCourseName.setText(mCourseName);
        mTextDiscussionTitle.setText(mDiscussionName);
        mTextNoResults.setVisibility(View.GONE);

        if (mIsNewsType) {
            mLinearNewPost.setVisibility(View.GONE);
        } else {
            mLinearNewPost.setVisibility(View.VISIBLE);

            linearAddPost.setOnClickListener(this);

            mEditNewPost.setText("");
            mEditNewPost.setHint(R.string.post_hint);

            mEditNewPost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_SEND) {
                        mNewPost = mEditNewPost.getText().toString();
                        mEditNewPost.setText("");
                        mEditNewPost.setHint(R.string.post_hint);
                        hideKeyboard(Objects.requireNonNull(getActivity()));
                        mEditNewPost.clearFocus();
                        if (!("").equals(mNewPost)) {
//                            mPresenter.addPost(
//                                    mPostsList.get(0).getPostId(),
//                                    mPostsList.get(0).getSubject(),
//                                    mNewPost);
                            mNewPost = "";
                        }
                        return true;
                    }
                    return false;
                }
            });

        }
    }
}

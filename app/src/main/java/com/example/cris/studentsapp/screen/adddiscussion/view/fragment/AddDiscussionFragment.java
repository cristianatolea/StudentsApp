package com.example.cris.studentsapp.screen.adddiscussion.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.adddiscussion.presenter.IAddDiscussionPresenter;
import com.example.cris.studentsapp.screen.adddiscussion.view.delegate.IAddDiscussionViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;
import com.example.cris.studentsapp.utils.FragmentUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;
import static com.example.cris.studentsapp.utils.Constants.FORUM_ID;
import static com.example.cris.studentsapp.utils.Constants.FORUM_NAME;
import static com.example.cris.studentsapp.utils.Constants.STIRI;

public class AddDiscussionFragment extends BaseFragment implements
        IAddDiscussionViewDelegate,
        View.OnClickListener {

    private ProgressBar mProgressBar;
    private TextInputEditText mInputSubject, mInputMessage;

    private String mForumId = "";
    private String mForumName = "";
    private String mCourseName = "";
    private String mSubject = "";
    private String mMessage = "";
    private boolean mIsNewsType = false;

    @Inject
    IAddDiscussionPresenter mPresenter;

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
        return inflater.inflate(R.layout.fragment_add_discussion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mForumId = getArguments().getString(FORUM_ID);
        mForumName = getArguments().getString(FORUM_NAME);
        mCourseName = getArguments().getString(COURSE_NAME);
        mIsNewsType = getArguments().getBoolean(STIRI);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.add_discussion);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, false, false);

        initView(view);

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
    public void onDiscussionAddedSuccessfully() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStackImmediate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send:
//                mInputSubject.clearFocus();
//                mInputMessage.clearFocus();
                if (checkEmptyFields()) {
                    onError(getContext().getString(R.string.empty_fiels));
                } else {
                    //todo uncomment
                    mPresenter.addNewDiscussion(mForumId,
                            mInputSubject.getText().toString(),
                            mInputMessage.getText().toString());
                }
                break;
            case R.id.button_cancel:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStackImmediate();
                break;
        }
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

    public static AddDiscussionFragment newInstance(String forumId,
                                                    String forumName,
                                                    String courseName,
                                                    boolean news) {
        Bundle args = new Bundle();
        args.putString(FORUM_ID, forumId);
        args.putString(FORUM_NAME, forumName);
        args.putString(COURSE_NAME, courseName);
        args.putBoolean(STIRI, news);
        AddDiscussionFragment fragment = new AddDiscussionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private boolean checkEmptyFields() {
        if ("".equals(mInputMessage.getText().toString())
                || "".equals(mInputSubject.getText().toString())) {
            return true;
        }
        return false;
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        Button buttonSend = view.findViewById(R.id.button_send);
        Button buttonCancel = view.findViewById(R.id.button_cancel);
        mInputSubject = view.findViewById(R.id.input_subject);
        mInputMessage = view.findViewById(R.id.input_message);

        lineaAddDiscussion.setVisibility(View.GONE);

        buttonSend.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        mInputMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(getActivity());
                    mInputMessage.clearFocus();
                    return true;
                }
                return false;
            }
        });

//        mInputMessage.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                mMessage = mInputMessage.getText().toString();
//            }
//        });
    }
}

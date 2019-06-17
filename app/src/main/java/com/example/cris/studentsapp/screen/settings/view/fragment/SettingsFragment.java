package com.example.cris.studentsapp.screen.settings.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.settings.view.delegate.ISettingsViewDelegate;
import com.example.cris.studentsapp.utils.AlertUtils;

public class SettingsFragment extends BaseFragment implements
        ISettingsViewDelegate,
        View.OnClickListener {

    private ProgressBar mProgressBar;
    private TextInputEditText mInputCurrentPassword;
    private TextInputEditText mInputNewPassword;
    private TextInputEditText mInputConfirmPassword;
    private CheckBox mCheckBoxLogout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.settings);
        ((MainActivity) getActivity()).changeFocusOnMenu(2, true, false);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_change:
                break;
            case R.id.button_cancel:
                break;
            case R.id.linear_check_logout:
                break;
        }
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);

        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mInputCurrentPassword = view.findViewById(R.id.input_current_password);
        mInputNewPassword = view.findViewById(R.id.input_new_password);
        mInputConfirmPassword = view.findViewById(R.id.input_confirm_password);
        LinearLayout linearCheckbox = view.findViewById(R.id.linear_check_logout);
        mCheckBoxLogout = view.findViewById(R.id.checkbox_logout);
        Button buttonChangePassword = view.findViewById(R.id.button_change);
        Button buttonCancel = view.findViewById(R.id.button_cancel);

        linearCheckbox.setOnClickListener(this);
        buttonChangePassword.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }
}

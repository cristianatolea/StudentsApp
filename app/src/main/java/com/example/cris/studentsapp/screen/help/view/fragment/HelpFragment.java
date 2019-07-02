package com.example.cris.studentsapp.screen.help.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.help.view.delegate.IHelpViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import static com.example.cris.studentsapp.utils.Constants.ACS_FACEBOOK;
import static com.example.cris.studentsapp.utils.Constants.ACS_LINK;
import static com.example.cris.studentsapp.utils.Constants.LSAC_FACEBOOK;
import static com.example.cris.studentsapp.utils.Constants.SCHEDULE_LINK;
import static com.example.cris.studentsapp.utils.Constants.STUDENTI_LINK;
import static com.example.cris.studentsapp.utils.Constants.UPB_FACEBOOK;
import static com.example.cris.studentsapp.utils.Constants.UPB_LINK;

public class HelpFragment extends BaseFragment implements
        IHelpViewDelegate,
        View.OnClickListener {

    private ProgressBar mProgressBar;

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
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.help);
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
            case R.id.constraint_schedules:
                openLink(SCHEDULE_LINK);
                break;
            case R.id.text_upb_link:
                openLink(UPB_LINK);
                break;
            case R.id.text_upb_facebook:
                openLink(UPB_FACEBOOK);
                break;
            case R.id.text_acs_facebook:
                openLink(ACS_FACEBOOK);
                break;
            case R.id.text_acs_link:
                openLink(ACS_LINK);
                break;
            case R.id.text_lsac_facebook:
                openLink(LSAC_FACEBOOK);
                break;
            case R.id.text_studenti_link:
                openLink(STUDENTI_LINK);
                break;
        }

    }

    private void openLink(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);

        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        ConstraintLayout constraintSchedules = view.findViewById(R.id.constraint_schedules);
        TextView textUpbLink = view.findViewById(R.id.text_upb_link);
        TextView textAcsLink = view.findViewById(R.id.text_acs_link);
        TextView textUpbFacebook = view.findViewById(R.id.text_upb_facebook);
        TextView textAcsFacebook = view.findViewById(R.id.text_acs_facebook);
        TextView textLsacFacebook = view.findViewById(R.id.text_lsac_facebook);
        TextView textStudenti = view.findViewById(R.id.text_studenti_link);

        constraintSchedules.setOnClickListener(this);
        textUpbLink.setOnClickListener(this);
        textAcsLink.setOnClickListener(this);
        textUpbFacebook.setOnClickListener(this);
        textAcsFacebook.setOnClickListener(this);
        textLsacFacebook.setOnClickListener(this);
        textStudenti.setOnClickListener(this);
    }
}

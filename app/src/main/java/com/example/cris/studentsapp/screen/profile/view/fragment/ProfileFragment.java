package com.example.cris.studentsapp.screen.profile.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;
import com.example.cris.studentsapp.screen.profile.presenter.IProfilePresenter;
import com.example.cris.studentsapp.screen.profile.view.delegate.IProfileViewDelegate;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment implements
        IProfileViewDelegate {

    private ImageView mUserImage;
    private TextView mTextUserName;
    private TextInputEditText mInputEmail;
    private TextInputEditText mInputCountry;
    private TextInputEditText mInputCity;
    private TextInputEditText mInputYear;
    private TextInputEditText mInputUniversity;
    private TextInputEditText mInputCycleOfStudy;
    private TextInputEditText mInputSpecialization;
    private TextInputEditText mInputYearOfStudy;
    private TextInputEditText mInputGroup;

    @Inject
    IProfilePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.profile);
        ((MainActivity) getActivity()).changeFocusOnMenu(1, true, false);

        mPresenter.getUserInformation();
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
    public void onGetUserInformationSuccess(UserProfileEntity entity) {
        mPresenter.parseUserCustomFields(entity.getCustomFields(), entity);
    }

    @Override
    public void onGetCustomInformation(String univYear, String university, String cycleOfStudy,
                                       String specialization, String yearOfStudy, String group,
                                       UserProfileEntity entity) {
        setUserInfo(entity);
        mInputYear.setText(univYear);
        mInputUniversity.setText(university);
        mInputCycleOfStudy.setText(cycleOfStudy);
        mInputSpecialization.setText(specialization);
        mInputYearOfStudy.setText(yearOfStudy);
        mInputGroup.setText(group);
    }

    private void setUserInfo(UserProfileEntity entity) {
        mTextUserName.setText(entity.getFullname());
        mInputEmail.setText(entity.getEmail());
        mInputCountry.setText(entity.getCountry());
        mInputCity.setText(entity.getCity());
        Glide.with(getContext())
                .load(entity.getProfileImageUrl())
                .error(R.drawable.ic_user)
                .apply(RequestOptions.circleCropTransform())
                .into(mUserImage);
    }

    private void initView(View view) {
        mUserImage = view.findViewById(R.id.image_user_icon);
        mTextUserName = view.findViewById(R.id.text_user_name);
        mInputEmail = view.findViewById(R.id.input_email);
        mInputCountry = view.findViewById(R.id.input_country);
        mInputCity = view.findViewById(R.id.input_city);
        mInputYear = view.findViewById(R.id.input_year);
        mInputUniversity = view.findViewById(R.id.input_university);
        mInputCycleOfStudy = view.findViewById(R.id.input_study);
        mInputSpecialization = view.findViewById(R.id.input_specialization);
        mInputYearOfStudy = view.findViewById(R.id.input_year_of_study);
        mInputGroup = view.findViewById(R.id.input_group);

        //todo disable editing user data
    }
}

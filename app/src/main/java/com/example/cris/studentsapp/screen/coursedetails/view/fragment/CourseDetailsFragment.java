package com.example.cris.studentsapp.screen.coursedetails.view.fragment;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.coursedetails.di.CourseDetailsModule;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailModule;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsContent;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;
import com.example.cris.studentsapp.screen.coursedetails.presenter.ICourseDetailsPresenter;
import com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitems.CourseDetailsAdapter;
import com.example.cris.studentsapp.screen.coursedetails.view.delegate.ICourseDetailsViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;
import com.example.cris.studentsapp.utils.DownloadManager;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.example.cris.studentsapp.utils.Constants.COURSE_ID;
import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;
import static com.example.cris.studentsapp.utils.Constants.DOWNLOAD_FILE_REQUEST;

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
    private int mCoursePositionSelected;
    private int mFilePositionSelected;

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

    @Override
    public void onPermissionAvailable(int who, int coursePosition, int position) {
        final List<CourseDetailsContent> contents = new ArrayList<>();
        for (CourseDetailModule module : mCourseDetailsItems.get(coursePosition).getModules())
            contents.addAll(module.getContents());
        mPresenter.downloadFile(contents.get(position),
                contents.get(position).getFileUrl()
                        .concat("&token=")
                        .concat(LocalSaving.getToken(getContext())));
    }

    @Override
    public void onRequestPermissions(int who, int coursePosition, int position) {
        requestPermissions(
                new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                DOWNLOAD_FILE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            this.onPermissionAvailable(DOWNLOAD_FILE_REQUEST, mCoursePositionSelected, mFilePositionSelected);
        } else {
            Toast.makeText(getContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDownloadSuccess(LocalFile localFile) {
        alertSaveFile(localFile);
    }

    @Override
    public void onItemFileClick(int coursePosition, final int position) {
        mCoursePositionSelected = coursePosition;
        mFilePositionSelected = position;

        mPresenter.checkPermission(DOWNLOAD_FILE_REQUEST, coursePosition, position);
    }

    public static CourseDetailsFragment newInstance(String id, String name) {
        Bundle args = new Bundle();
        args.putString(COURSE_ID, id);
        args.putString(COURSE_NAME, name);
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void alertSaveFile(final LocalFile localFile) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_std_two_buttons);

        TextView txtTitle = dialog.findViewById(R.id.text_alert_title);
        TextView txtContent = dialog.findViewById(R.id.text_alert_content);
        Button btnOk = dialog.findViewById(R.id.button_ok);
        Button btnCancel = dialog.findViewById(R.id.button_no);

        txtTitle.setText(getString(R.string.alert_title));
        txtContent.setText(getString(R.string.download_success));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                    mPresenter.openFile(getActivity(), localFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);

        mTextName = view.findViewById(R.id.text_course_name);
        mRvCourseItems = view.findViewById(R.id.rv_course_items);

        mCourseAdapter = new CourseDetailsAdapter(getContext(), mCourseDetailsItems, this);
        mRvCourseItems.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvCourseItems.setAdapter(mCourseAdapter);

        mTextName.setText(mCourseName);
    }
}

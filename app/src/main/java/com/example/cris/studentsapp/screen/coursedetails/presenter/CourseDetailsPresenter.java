package com.example.cris.studentsapp.screen.coursedetails.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.helper.FileOpenChooser;
import com.example.cris.studentsapp.screen.coursedetails.model.ICourseDetailsModel;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsContent;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsResponse;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;
import com.example.cris.studentsapp.screen.coursedetails.model.storage.ILocalFileStorage;
import com.example.cris.studentsapp.screen.coursedetails.model.storage.LocalFileStorage;
import com.example.cris.studentsapp.screen.coursedetails.view.delegate.ICourseDetailsViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CourseDetailsPresenter implements ICourseDetailsPresenter {

    private Context mContext;
    private ICourseDetailsModel mModel;
    private ICourseDetailsViewDelegate mViewDelegate;
    private ILocalFileStorage mLocalFileStorage;
    private CompositeDisposable mCompositeDisposable;

    public CourseDetailsPresenter(Context context,
                                  ICourseDetailsModel model,
                                  ICourseDetailsViewDelegate viewDelegate,
                                  ILocalFileStorage localFileStorage) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mLocalFileStorage = localFileStorage;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getCourseDetails(String courseId) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getCourseDetails(courseId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CourseDetailsItem>>() {
                                @Override
                                public void accept(List<CourseDetailsItem> courseDetailsResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (courseDetailsResponse != null) {
                                        mViewDelegate.onGetCourseDetailsSuccess(courseDetailsResponse);
                                        Log.d("course details", "accept success maybe");
                                    } else {
                                        mViewDelegate.onError(mContext.getString(R.string.alert_error_occured));
                                        Log.d("course details err1", "error");
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    mViewDelegate.hideProgress();
                                    mViewDelegate.onError(mContext.getString(R.string.alert_error_occured));
                                    Log.d("course details err2", throwable.getMessage());
                                }
                            })
            );
        } else {
            mViewDelegate.onNoInternetConnection();
        }
    }

    @Override
    public void downloadFile(final CourseDetailsContent content, final String url) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    Observable.just(content)
                            .map(new Function<CourseDetailsContent, LocalFile>() {
                                @Override
                                public LocalFile apply(CourseDetailsContent courseContent) throws Exception {
                                    File file = new File(
                                            LocalFileStorage.getStorageDir(),
                                            courseContent.getFilename());

                                    LocalFile localFile = new LocalFile();
                                    localFile.setLocalPath(file.getPath());
//                                    localFile.setFile(file);
                                    localFile.setFileUrl(url);
                                    localFile.setFileName(file.getName());
                                    return localFile;
                                }
                            })
                            .concatMap(new Function<LocalFile, Observable<LocalFile>>() {
                                @Override
                                public Observable<LocalFile> apply(LocalFile localFile) throws Exception {
                                    return mModel.downloadFile(localFile, url);
                                }
                            })
                            .flatMapSingle(new Function<LocalFile, Single<LocalFile>>() {
                                @Override
                                public Single<LocalFile> apply(LocalFile localFile) throws Exception {
                                    return mLocalFileStorage.storeLocalFile(localFile);
                                }
                            })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<LocalFile>() {
                                @Override
                                public void accept(LocalFile localFile) throws Exception {
                                    mViewDelegate.hideProgress();
                                    mViewDelegate.onDownloadSuccess(localFile);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    mViewDelegate.hideProgress();
                                    mViewDelegate.onError(mContext.getString(R.string.alert_error_occured));
                                }
                            })
            );
        } else {
            mViewDelegate.onNoInternetConnection();
        }

    }

    @Override
    public void openFile(Activity activity, LocalFile localFile) {
        FileOpenChooser.openChooser(activity, localFile);
    }

    @Override
    public void checkPermission(int who, int coursePosition, int position) {
        boolean hasPermission =
                ContextCompat.checkSelfPermission(mContext, READ_EXTERNAL_STORAGE) == 0 &&
                        ContextCompat.checkSelfPermission(mContext, WRITE_EXTERNAL_STORAGE) == 0;
        if (hasPermission) {
            mViewDelegate.onPermissionAvailable(who, coursePosition, position);
        } else {
            mViewDelegate.onRequestPermissions(who, coursePosition, position);
        }
    }
}

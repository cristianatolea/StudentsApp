package com.example.cris.studentsapp.screen.coursedetails.model.storage;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class LocalFileStorage implements ILocalFileStorage {
    private final static String DIRECTORY_NAME = "StudentsApp";
    private final static long ONE_MEGABYTE = 1024 * 1024;
    public final static long MAX_FILE_SIZE = 2 * ONE_MEGABYTE;

    private Context mContext;

    public LocalFileStorage(Context context) {
        mContext = context;
    }

    /**
     * Checks if external storage is available to at least read, only for sdcard
     *//*
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
    */
    public static File getStorageDir() {
        File file = new File(Environment.getExternalStorageDirectory(), DIRECTORY_NAME);
        file.mkdirs();
        return file;
    }


    @Override
    public Single<LocalFile> storeLocalFile(final LocalFile localFile) {
        return checkStorageWrite()
                .flatMap(new Function<String, SingleSource<? extends LocalFile>>() {
                    @Override
                    public SingleSource<? extends LocalFile> apply(String filepath) throws Exception {
                        File output = new File(filepath, localFile.getFileName());
                        if (!output.getParentFile().exists()) {
                            boolean directoriesCreated = output.mkdirs();
                            if (!directoriesCreated) {
                                return Single.error(new Throwable(mContext.getString(R.string.storage_error_not_available)));
                            }
                        }
                        if (!output.exists()) {
                            boolean cnf = output.createNewFile();
                            if (!cnf) {
                                return Single.error(new Throwable(mContext.getString(R.string.storage_error_not_available)));
                            }
                        }
                        FileOutputStream out = new FileOutputStream(output);
                        if (localFile.getBytes() == null) {
                            return Single.error(new Throwable(mContext.getString(R.string.storage_file_error)));
                        } else {
                            out.write(localFile.getBytes());
                            out.close();

                            return Single.just(localFile);
                        }
                    }
                });
    }

    @Override
    public Single<LocalFile> getLocalFile(final LocalFile localFile) {
        return checkStorageRead()
                .flatMap(new Function<String, SingleSource<? extends LocalFile>>() {
                    @Override
                    public SingleSource<? extends LocalFile> apply(String filepath) throws Exception {
                        File output = new File(filepath, localFile.getFullName());
                        localFile.setFile(output);
                        return Single.just(localFile);
                    }
                });
    }

    private Single<String> checkStorageWrite() {
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                File directory = getStorageDir();
               /* if (isExternalStorageWritable()) {
                    emitter.onError(new Throwable(mContext.getString(R.string.storage_error_not_available)));
                } else*/
                if (freeMemory(directory.getPath()) < 10 * ONE_MEGABYTE) {
                    emitter.onError(new Throwable(mContext.getString(R.string.storage_error_low_memory)));
                } else {
                    emitter.onSuccess(directory.getPath());
                }
            }
        });
    }

    private Observable<String> checkStorageWrites() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                File directory = getStorageDir();
               /* if (isExternalStorageWritable()) {
                    emitter.onError(new Throwable(mContext.getString(R.string.storage_error_not_available)));
                } else*/
                if (freeMemory(directory.getPath()) < 10 * ONE_MEGABYTE) {
                    emitter.onError(new Throwable(mContext.getString(R.string.storage_error_low_memory)));
                } else {
                    emitter.onNext(directory.getPath());
                }
            }
        });
    }

    private long freeMemory(String path) {
        StatFs statFs = new StatFs(path);
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    /*
     */

    private Single<String> checkStorageRead() {
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                File directory = getStorageDir();
                /*if (directory == null || !isExternalStorageReadable()) {
                    emitter.onError(new Throwable(mContext.getString(R.string.storage_error_not_available)));
                    return;
                }*/
                emitter.onSuccess(directory.getPath());
            }
        });
    }
}


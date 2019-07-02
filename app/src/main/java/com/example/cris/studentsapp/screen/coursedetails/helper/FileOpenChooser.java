package com.example.cris.studentsapp.screen.coursedetails.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;

import java.io.File;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class FileOpenChooser {
    public static final int REQUEST_CODE_FILE_PREVIEW = 118;

    public static void openChooser(Activity activity, LocalFile model) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(activity,
                "com.example.cris.studentsapp.screen.coursedetails.helper.MessageFileProvider",
                new File(model.getLocalPath()));

        activity.grantUriPermission(activity.getApplicationContext().getPackageName(),
                uri,
                FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setData(uri);
        intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
        Intent chooser = Intent.createChooser(intent,
                activity.getString(R.string.info_chooser_open_file) + " " + model.getFileName());
        activity.startActivityForResult(chooser, REQUEST_CODE_FILE_PREVIEW);
    }
}

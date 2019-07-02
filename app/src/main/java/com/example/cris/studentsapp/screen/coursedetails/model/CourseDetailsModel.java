package com.example.cris.studentsapp.screen.coursedetails.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

public class CourseDetailsModel implements ICourseDetailsModel {

    private Context mContext;
    private IApiInterface mIApiInterface;

    public CourseDetailsModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<List<CourseDetailsItem>> getCourseDetails(String courseId) {
        return mIApiInterface.getCourseDetails(
                LocalSaving.getToken(mContext),
                "core_course_get_contents",
                courseId
        );
    }

    @Override
    public Observable<LocalFile> downloadFile(final LocalFile file, String fileUrl) {
        return mIApiInterface.downloadFile(fileUrl)
                .map(new Function<ResponseBody, LocalFile>() {
                    @Override
                    public LocalFile apply(ResponseBody body) throws Exception {
                        byte[] fileReader = new byte[4096];
                        InputStream inputStream = body.byteStream();
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        int nRead;
                        while ((nRead = inputStream.read(fileReader, 0, fileReader.length)) != -1) {
                            buffer.write(fileReader, 0, nRead);
                        }
                        buffer.flush();
                        file.setBytes(buffer.toByteArray());
                        inputStream.close();
                        buffer.close();
                        return file;
                    }
                });
    }
}

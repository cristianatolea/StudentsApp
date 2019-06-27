package com.example.cris.studentsapp.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsContent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.cris.studentsapp.utils.Constants.CACHE_PATH;

public class DownloadManager extends AsyncTask<String, String, String> {
    private String mDownloadlink, mFileDestination, mFileName;
    public static final int ON_INIT = 100, ON_ERROR = 102, ON_PROGRASS = 103, ON_COMPLETED = 104, STATUS_DOWNLOADED = 1500, STATUS_NOT_YET = 1501;
    private onUpdateListener onUpdateListener;
    private String downloadedPath = "";
    private long downloaded = 0;
    private File file;
    private String returnData = null;
    private File cacheDownloadFile;
    private CourseDetailsContent mCourseFile;

    public DownloadManager(CourseDetailsContent courseDetailsContent, String fileDestinationPath ) {
        mDownloadlink = courseDetailsContent.getFileUrl();
        mFileDestination = fileDestinationPath;
        mFileName = courseDetailsContent.getFilename();

        file = new File(mFileDestination, mFileName);
        cacheDownloadFile = new File(CACHE_PATH + mFileName);
        if (cacheDownloadFile.isFile())
            downloaded = cacheDownloadFile.length();
        else
            downloaded = 0;
        Log.d("FILE_DOWNLOAD_TAG_p", downloaded + " <- " + cacheDownloadFile.getAbsolutePath());
        fireOnUpdate(ON_INIT, "init ...");

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            File dir = new File(mFileDestination);
            File chacheDir = new File(CACHE_PATH);
            if (!chacheDir.isDirectory())
                chacheDir.mkdirs();
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }

            if (file.exists()) {
                Log.d("FILE_DOWNLOAD_TAG", "File exist return complete");
                return "COMPLETED";//file exist
            }
            if (!cacheDownloadFile.exists()) {
                cacheDownloadFile.createNewFile();
            }
            Log.d("FILE_DOWNLOAD_TAG", "LINK " + mDownloadlink);
            URL url = new URL(mDownloadlink);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (downloaded > 0)
                urlConnection.setRequestProperty("Range", "byte=" + downloaded);
            urlConnection.connect();
            int status = urlConnection.getResponseCode();
            InputStream inputStream = urlConnection.getInputStream();
            int totalSize = urlConnection.getContentLength();
            if (totalSize <= downloaded) {
                returnData = "COMPLETED";
                //publishProgress("File checked "+Tools.getFileName(file.getAbsolutePath()));
                return returnData;
            }
            this.downloadedPath = cacheDownloadFile.getAbsolutePath();
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            FileOutputStream fileOutput = new FileOutputStream(cacheDownloadFile);
            long d = 0;
            long starttime = System.currentTimeMillis();
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloaded += bufferLength;
                d += bufferLength;
                //String l=" "+Tools.getFileName(file.getAbsolutePath())+" ( "+Tools.convertMemory(downloaded)+" / "+Tools.convertMemory(totalSize)+" )";
                //String l = "  " + Tools.convertMemory(downloaded) + " / " + Tools.convertMemory(totalSize) + " ( " + getDownloadSpeed(starttime, d) + " )";
                //publishProgress(l);
                if (downloaded >= totalSize) {
                    break;
                }
            }
            Log.d("FILE_DOWNLOAD_TAG", "DWONLOADED TO " + downloadedPath + " (" + cacheDownloadFile.length() + ")");
            fileOutput.close();
//            if (Tools.fileCopy(file, cacheDownloadFile)) {
//                Log.d("FILE_DOWNLOAD_TAG", "file Copied, delete cache");
//                cacheDownloadFile.delete();
//            }
            returnData = "COMPLETED";
        } catch (MalformedURLException e) {
            returnData = null;
            e.printStackTrace();
            publishProgress(e.toString());
            Log.d("###################", e + "");

        } catch (IOException e) {
            returnData = null;
            e.printStackTrace();
            publishProgress(e.toString());
        }

        return returnData;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        fireOnUpdate(ON_PROGRASS, values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            fireOnUpdate(ON_COMPLETED, downloadedPath);
        } else {
            fireOnUpdate(ON_ERROR, "Download failed");
        }
    }

    public interface onUpdateListener {
        void onUpdate(int code, String message);
    }

    public void setOnUpdateListener(onUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    private void fireOnUpdate(int code, String message) {
        if (onUpdateListener != null)
            onUpdateListener.onUpdate(code, message);
    }

    private String getDownloadSpeed(long starttime, float totalDownloaded) {
        long elapsedTime = System.currentTimeMillis() - starttime;
        //byte :
        float speed = 1000f * totalDownloaded / elapsedTime;
        return convert(speed);
    }

    private String convert(float value) {
        long kb = 1024, mb = kb * 1024, gb = mb * 1024;

        if (value < kb) {
            String speed = (value + "");
            speed = speed.substring(0, speed.indexOf('.') + 2);
            return speed + " B/s";
        } else if (value < mb) {
            value = value / kb;
            String speed = (value + "");
            speed = speed.substring(0, speed.indexOf('.'));
            return (speed) + " KB/s";
        } else if (value < gb) {
            value = (value / mb);
            String speed = (value + "");
            speed = speed.substring(0, speed.indexOf('.'));
            return speed + " MB/s";
        }
        return "";
    }
}

package com.example.cris.studentsapp.screen.coursedetails.model.entity;

import java.io.File;

public class LocalFile {
    private File mFile;
    private String mLocalPath;
    private String mFileName;
    private String mFileUrl;
    private byte[] mBytes;

    public LocalFile() {
        mFile = null;
        mFileName = "";
        mLocalPath = "";
        mFileUrl = "";
        mBytes = null;
    }

    public File getFile() {
        return mFile;
    }

    public void setFile(File file) {
        mFile = file;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }


    public byte[] getBytes() {
        return mBytes;
    }

    public void setBytes(byte[] bytes) {
        mBytes = bytes;
    }

    public String getFullName() {
        return mFileName;
    }

    public String getLocalPath() {
        return mLocalPath;
    }

    public void setLocalPath(String localPath) {
        mLocalPath = localPath;
    }

    public String getFileUrl() {
        return mFileUrl;
    }

    public void setFileUrl(String fileUrl) {
        mFileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "LocalFile{" +
                "mFile=" + mFile +
                ", mLocalPath='" + mLocalPath + '\'' +
                ", mFileName='" + mFileName + '\'' +
                '}';
    }
}

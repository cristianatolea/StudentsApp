package com.example.cris.studentsapp.screen.coursedetails.model.storage;

import com.example.cris.studentsapp.screen.coursedetails.model.entity.LocalFile;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ILocalFileStorage {
    Single<LocalFile> storeLocalFile(LocalFile localFile);

    Single<LocalFile> getLocalFile(LocalFile localFile);
}

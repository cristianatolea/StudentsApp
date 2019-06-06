package com.example.cris.studentsapp.screen.forumspercourse.model;

import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;

import java.util.List;

import io.reactivex.Observable;

public interface IForumsModel {

    Observable<List<ForumEntity>> getForumsPerCourse(String courseId);
}

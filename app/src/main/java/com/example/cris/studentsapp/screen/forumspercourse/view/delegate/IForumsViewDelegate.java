package com.example.cris.studentsapp.screen.forumspercourse.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;

import java.util.List;

public interface IForumsViewDelegate extends IBaseViewDelegate {

    void onGetCoursesListSuccess(List<CourseEntity> list);

    void onGetForumsSuccess(List<ForumEntity> list);
}

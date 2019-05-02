package com.example.cris.studentsapp.screen.courses.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesViewHolder> implements
        CoursesViewHolder.OnCourseClickListener {

    private Context mContext;
    private List<CourseEntity> mCourses;
    private OnCourseItemClick mOnCourseItemClick;

    public CoursesAdapter(Context context,
                          List<CourseEntity> list,
                          OnCourseItemClick onCourseItemClick) {
        mContext = context;
        mCourses = list;
        mOnCourseItemClick = onCourseItemClick;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_course, viewGroup, false);
        return new CoursesViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder coursesViewHolder, int i) {
        CourseEntity entity = mCourses.get(i);
        coursesViewHolder.bindData(entity);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    @Override
    public void onCourseClick(int position) {
        mOnCourseItemClick.onCourseItemClick(position);
    }

    public interface OnCourseItemClick {
        void onCourseItemClick(int position);
    }
}

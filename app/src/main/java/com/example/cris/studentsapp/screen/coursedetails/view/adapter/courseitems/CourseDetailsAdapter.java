package com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;

import java.util.List;

public class CourseDetailsAdapter extends RecyclerView.Adapter<CourseDetailsViewHolder> implements CourseDetailsViewHolder.OnFileClickListener {

    private Context mContext;
    private List<CourseDetailsItem> mCourseItems;
    private OnItemFileClickListener mOnItemFileClickListener;

    public CourseDetailsAdapter(Context context, List<CourseDetailsItem> courseItems, OnItemFileClickListener onItemFileClickListener) {
        mContext = context;
        mCourseItems = courseItems;
        mOnItemFileClickListener = onItemFileClickListener;
    }

    @NonNull
    @Override
    public CourseDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_course_details, viewGroup, false);
        return new CourseDetailsViewHolder(view, mContext, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDetailsViewHolder courseDetailsViewHolder, int i) {
        CourseDetailsItem item = mCourseItems.get(i);
        courseDetailsViewHolder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return mCourseItems.size();
    }

    @Override
    public void onFileClick(int coursePosition, int position) {
        mOnItemFileClickListener.onItemFileClick(coursePosition, position);
    }

    public interface OnItemFileClickListener {
        void onItemFileClick(int coursePosition, int position);
    }
}

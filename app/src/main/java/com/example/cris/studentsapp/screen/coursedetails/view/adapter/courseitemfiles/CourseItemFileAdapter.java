package com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitemfiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.di.CourseDetailsModule;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailModule;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsContent;

import java.util.ArrayList;
import java.util.List;

public class CourseItemFileAdapter extends RecyclerView.Adapter<CourseItemFileViewHolder> implements
        CourseItemFileViewHolder.OnFileClickListener {

    private Context mContext;
    private List<CourseDetailModule> mModules;
    private List<CourseDetailsContent> mContents;
    private OnItemFileClickListener mOnItemFileClickListener;

    public CourseItemFileAdapter(Context context,
                                 List<CourseDetailModule> contents,
                                 OnItemFileClickListener onItemFileClickListener) {
        mContext = context;
        mModules = contents;
        mContents = getAllContents();
        mOnItemFileClickListener = onItemFileClickListener;
    }

    @NonNull
    @Override
    public CourseItemFileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_course_details_file, viewGroup, false);
        return new CourseItemFileViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseItemFileViewHolder courseItemFilesViewHolder, int i) {
        CourseDetailsContent content = mContents.get(i);
        courseItemFilesViewHolder.bindData(content);
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    @Override
    public void onFileClick(int position) {
        mOnItemFileClickListener.onItemFileClick(position);
    }

    public interface OnItemFileClickListener {
        void onItemFileClick(int position);
    }

    public List<CourseDetailsContent> getAllContents() {
        mContents = new ArrayList<>();
        for (CourseDetailModule module : mModules) {
            mContents.addAll(module.getContents());
        }
        return mContents;
    }
}

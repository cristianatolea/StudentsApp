package com.example.cris.studentsapp.screen.forumspercourse.view.adapter.forums;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;

import java.util.List;

public class CourseForumAdapter extends RecyclerView.Adapter<CourseForumViewHolder> implements
        CourseForumViewHolder.OnDetailsClickListener {

    private Context mContext;
    private List<ForumEntity> mForumList;
    private OnItemDetailsClick mOnItemDetailsClick;

    public CourseForumAdapter(Context context,
                              List<ForumEntity> forumList,
                              OnItemDetailsClick onItemDetailsClick) {
        mContext = context;
        mForumList = forumList;
        mOnItemDetailsClick = onItemDetailsClick;
    }

    @NonNull
    @Override
    public CourseForumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_course_forum, viewGroup, false);
        return new CourseForumViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseForumViewHolder courseForumViewHolder, int i) {
        ForumEntity forumEntity = mForumList.get(i);
        courseForumViewHolder.bindData(forumEntity);
    }

    @Override
    public int getItemCount() {
        return mForumList.size();
    }

    @Override
    public void onDetailsClick(int position) {
        mOnItemDetailsClick.onItemDetailsClick(position);
    }

    public interface OnItemDetailsClick {
        void onItemDetailsClick(int position);
    }
}

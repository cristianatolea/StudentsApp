package com.example.cris.studentsapp.screen.deadlineassignment.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.EnrolledUserEntity;

import java.util.List;

public class DeadlineEnrolledAdapter extends RecyclerView.Adapter<DeadlineEnrolledViewHolder> implements
        DeadlineEnrolledViewHolder.OnStudentClickListener {

    private Context mContext;
    private List<EnrolledUserEntity> mEnrolledUsers;
    private OnStudentItemClickListener mOnStudentItemClickListener;

    public DeadlineEnrolledAdapter(Context context,
                                   List<EnrolledUserEntity> list,
                                   OnStudentItemClickListener onStudentItemClickListener) {
        mContext = context;
        mEnrolledUsers = list;
        mOnStudentItemClickListener = onStudentItemClickListener;
    }

    @NonNull
    @Override
    public DeadlineEnrolledViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_enrolled, viewGroup, false);
        return new DeadlineEnrolledViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DeadlineEnrolledViewHolder deadlineEnrolledViewHolder, int i) {
        EnrolledUserEntity entity = mEnrolledUsers.get(i);
        deadlineEnrolledViewHolder.bindData(entity);
    }

    @Override
    public int getItemCount() {
        return mEnrolledUsers.size();
    }

    @Override
    public void onStudentClick(int position) {
        mOnStudentItemClickListener.onStudentClick(position);
    }

    public interface OnStudentItemClickListener{
        void onStudentClick(int position);
    }
}

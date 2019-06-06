package com.example.cris.studentsapp.screen.discussionslistperforum.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionEntity;

import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionViewHolder> implements
        DiscussionViewHolder.OnDetailsClickListener {

    private Context mContext;
    private List<DiscussionEntity> mDiscussionsList;
    private OnItemDetailsClick mOnItemDetailsClick;

    public DiscussionAdapter(Context context,
                             List<DiscussionEntity> list,
                             OnItemDetailsClick onItemDetailsClick) {
        mContext = context;
        mDiscussionsList = list;
        mOnItemDetailsClick = onItemDetailsClick;
    }

    @NonNull
    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_forum_discussion, viewGroup, false);
        return new DiscussionViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder discussionViewHolder, int i) {
        DiscussionEntity entity = mDiscussionsList.get(i);
        discussionViewHolder.bindData(entity);
    }

    @Override
    public int getItemCount() {
        return mDiscussionsList.size();
    }

    @Override
    public void onDetailsClick(int position) {
        mOnItemDetailsClick.onItemDetailsClick(position);
    }

    public interface OnItemDetailsClick {
        void onItemDetailsClick(int position);
    }
}

package com.example.cris.studentsapp.screen.postsperdiscussion.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostEntity;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context mContext;
    private List<PostEntity> mPostsList;

    public PostAdapter(Context context, List<PostEntity> list) {
        mContext = context;
        mPostsList = list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_post_discussion, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        PostEntity entity = mPostsList.get(i);
        postViewHolder.bindData(entity);
    }

    @Override
    public int getItemCount() {
        return mPostsList.size();
    }
}

package com.example.cris.studentsapp.screen.postsperdiscussion.view.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostEntity;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextPostSubject, mTextUsername, mTextMessage;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextPostSubject = itemView.findViewById(R.id.text_post_subject);
        mTextUsername = itemView.findViewById(R.id.text_username);
        mTextMessage = itemView.findViewById(R.id.text_message);
    }

    void bindData(PostEntity entity) {
        mTextPostSubject.setText(entity.getSubject());
        mTextUsername.setText(entity.getUserName());

        if (Build.VERSION.SDK_INT >= 24) {
            mTextMessage.setText(Html.fromHtml(entity.getMessage(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTextMessage.setText(Html.fromHtml(entity.getMessage()));
        }
    }
}

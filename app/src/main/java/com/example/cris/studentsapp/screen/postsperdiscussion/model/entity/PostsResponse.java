package com.example.cris.studentsapp.screen.postsperdiscussion.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostsResponse {

    @SerializedName("posts")
    private List<PostEntity> mPosts;

    public PostsResponse(List<PostEntity> list) {
        mPosts = list;
    }

    public List<PostEntity> getPosts() {
        return mPosts;
    }
}

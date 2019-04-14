package com.example.cris.studentsapp.screen.dashboard.model.entity;

public class DashboardItem {
    private String mTitle;
    private int mIconResource;

    public DashboardItem(String title, int iconResource) {
        mTitle = title;
        mIconResource = iconResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getIconResource() {
        return mIconResource;
    }

    public void setIconResource(int mIconResource) {
        this.mIconResource = mIconResource;
    }
}

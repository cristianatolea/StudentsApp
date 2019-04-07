package com.example.cris.studentsapp.screen.main.model.entity;

public class DrawerItem {

    private boolean mOnFocus;
    private String mTitle;
    private int mIconOnFocused;
    private int mIconOutOfFocused;

    public DrawerItem(String title, int iconFocus, int iconOutOfFocused) {
        mOnFocus = false;
        mTitle = title;
        mIconOnFocused = iconFocus;
        mIconOutOfFocused = iconOutOfFocused;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getIconOnFocused() {
        return mIconOnFocused;
    }

    public void setIconOnFocused(int mIconOnFocused) {
        this.mIconOnFocused = mIconOnFocused;
    }

    public int getIconOutOfFocused() {
        return mIconOutOfFocused;
    }

    public void setIconOutOfFocused(int mIconOutOfFocused) {
        this.mIconOutOfFocused = mIconOutOfFocused;
    }

    public boolean isOnFocus() {
        return mOnFocus;
    }

    public void setOnFocus(boolean mOnFocus) {
        this.mOnFocus = mOnFocus;
    }
}
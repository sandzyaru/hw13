package com.example.hw13.ui.notifications;

import androidx.fragment.app.Fragment;

public class Tabs extends Fragment {
    public Tabs (Fragment fragments,int icon){
        this.fragments=fragments;
        this.icon=icon;
    }
    private Fragment fragments;
    private int icon;

    public Fragment getFragments() {
        return fragments;
    }

    public void setFragments(Fragment fragments) {
        this.fragments = fragments;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}

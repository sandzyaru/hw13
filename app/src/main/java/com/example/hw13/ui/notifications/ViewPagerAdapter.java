package com.example.hw13.ui.notifications;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Tabs> fragments;

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }
    public void setFragments (ArrayList<Tabs> fragments){
        this.fragments=new ArrayList<>();
        this.fragments.addAll(fragments);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position).getFragments();
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}

package com.example.hw13.ui.notifications;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw13.OnclickListener;
import com.example.hw13.R;
import com.example.hw13.databinding.FragmentGridBinding;

import java.util.ArrayList;

public class GridFragment extends Fragment implements OnclickListener {
    private FragmentGridBinding binding;
    private ArrayList<ImageView> imageViews;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentGridBinding.inflate(LayoutInflater.from(getContext()),container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadData();
        MainPhotoAdapter adapter=new MainPhotoAdapter(imageViews,this);
        binding.recyclerGrid.setAdapter(adapter);
    }
    private void LoadData() {
        imageViews=new ArrayList<>();
        imageViews.add(new ImageView(R.drawable.ic_group1));
        imageViews.add(new ImageView(R.drawable.ic_group2));
        imageViews.add(new ImageView(R.drawable.ic_group3));
        imageViews.add(new ImageView(R.drawable.ic_group4));
        imageViews.add(new ImageView(R.drawable.ic_group5));
        imageViews.add(new ImageView(R.drawable.ic_group7));
        imageViews.add(new ImageView(R.drawable.ic_group8));
        imageViews.add(new ImageView(R.drawable.ic_group9));
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }

    @Override
    public void onClick() {

    }
}
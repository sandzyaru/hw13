package com.example.hw13.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.hw13.MainActivity;
import com.example.hw13.Prefs;
import com.example.hw13.R;
import com.example.hw13.databinding.FragmentProfileBinding;
import com.example.hw13.ui.notifications.GridFragment;
import com.example.hw13.ui.notifications.Tabs;
import com.example.hw13.ui.notifications.TagsFragment;
import com.example.hw13.ui.notifications.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ViewPagerAdapter adapter;
    private ArrayList<Tabs> fragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(requireContext()), container, false);
        return binding.getRoot();

    }


    /* ActivityResultCallback представляет собой единый интерфейс метода с onActivityResult(),
     методом, который принимает объект типа вывода, определенного в ActivityResultContract:*/
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    Prefs prefs = new Prefs(requireContext());
                    prefs.saveImage(uri);
                    prefs.saveName(binding.etName.getText().toString());
                }

            });

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragments=new ArrayList<>();
        fragments.add(new Tabs(new GridFragment(),R.drawable.ic_menu));
        fragments.add(new Tabs(new TagsFragment(),R.drawable.ic_grid));
        adapter=new ViewPagerAdapter(this);
        adapter.setFragments(fragments);
        binding.viewPager.setAdapter(adapter);
        Prefs prefs = new Prefs(requireContext());
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setIcon(fragments.get(position).getIcon());

            }
        }).attach();
        Glide.with(ProfileFragment.this).load(prefs.getImage()).into(binding.imageView);


        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (prefs.getName() != null && binding.etName.getText() != null) {
                    prefs.saveName(binding.etName.getText().toString());

                }
            }
        });
        binding.etName.setText(prefs.getName());

        Log.d("Image Log:", prefs.getImage());
        Log.d("Name Log:", prefs.getName());
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

    }


}
package com.example.hw13.ui.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.hw13.App;
import com.example.hw13.R;

import com.example.hw13.databinding.FragmentNewsBinding;
import com.example.hw13.models.News;

import java.util.List;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private News news;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void save() {
        String text = binding.editText.getText().toString();

        Bundle bundle = new Bundle();
        if (text.isEmpty()) {
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(5)
                    .playOn(binding.editText);
            Toast.makeText(requireContext(), "type task!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (news == null) {
            news = new News(text, System.currentTimeMillis());
        }else {
            news.setTitle(text);
        }
        bundle.putSerializable("text", news);
        App.getDataBase().newsDao().insert(news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        news = (News) requireArguments().getSerializable("updateTask");

        if (news != null) binding.editText.setText(news.getTitle());

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();

            }
        });
    }
}
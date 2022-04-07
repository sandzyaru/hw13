package com.example.hw13.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hw13.databinding.FragmentDashboardBinding;
import com.example.hw13.models.News;
import com.example.hw13.ui.news.NewsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private NewsAdapter newsAdapter;
    private FragmentDashboardBinding binding;
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private News news;
    private List<News> newsList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getToFireStore();
    }

    private void getToFireStore() {
        db.collection("news")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            newsList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                News news = document.toObject(News.class);
                                newsList.add(news);
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                            setAdapter();
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setAdapter() {
        newsAdapter = new NewsAdapter();
        binding.recyclerView.setAdapter(newsAdapter);
        newsAdapter.addList(newsList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
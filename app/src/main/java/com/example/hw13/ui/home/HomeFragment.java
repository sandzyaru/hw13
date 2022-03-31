package com.example.hw13.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.hw13.App;
import com.example.hw13.OnclickListener;
import com.example.hw13.R;
import com.example.hw13.databinding.FragmentHomeBinding;
import com.example.hw13.models.News;
import com.example.hw13.ui.news.NewsAdapter;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter newsAdapter;
    private boolean isChanged = false;
    private int position;
    private List<News> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsAdapter = new NewsAdapter();
        setHasOptionsMenu(true);
        App.getDataBase().newsDao().sortAll();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void open(News news) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putSerializable("updateTask", news);
        navController.navigate(R.id.newsFragment, bundle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChanged = false;
                open(null);
            }
        });
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("text");
                //Log.i("Home","text = "+news);
                if (isChanged) newsAdapter.updateItem(news, position);
                else newsAdapter.addItem(news);
            }
        });
        binding.recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnClickListener(new OnclickListener() {
            @Override
            public void onItemClick(int position) {
                News news = newsAdapter.getItem(position);
                isChanged = true;
                open(news);
                HomeFragment.this.position = position;
            }

            @Override
            public void onItemLongClick(int position) {

            }

            @Override
            public void onClick() {

            }
        });
        /*requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });*/
        list = App.getDataBase().newsDao().sortAll();
        binding.searchRecycle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list = App.getDataBase().newsDao().getSearch(editable.toString());
                newsAdapter.addList(list);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sorting) {
            newsAdapter.setList(App.getDataBase().newsDao().sort());
            binding.recyclerView.setAdapter(newsAdapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}